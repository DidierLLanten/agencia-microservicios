package com.agencia.viajes.service;

import com.agencia.viajes.dto.*;
import com.agencia.viajes.feignclients.TokenFeignClient;
import com.agencia.viajes.feignclients.UserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginServicio {
    private final TokenFeignClient tokenFeignClient;
    private final UserFeignClient userFeignClient;

    private final String clientID = "springboot-keycloak-client";

    public TokenDTO login(LoginDTO loginDTO) {

        String usernameAdmin = loginDTO.username();
        String passwordAdmin = loginDTO.password();
        String grantType = "password";

        String requestBody = String.format("client_id=%s&username=%s&password=%s&grant_type=%s", clientID, usernameAdmin, passwordAdmin, grantType);

        ResponseEntity<TokenResponseDTO> responseEntity = tokenFeignClient.sendRequest(requestBody);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            TokenResponseDTO response = responseEntity.getBody();
            return new TokenDTO(response.access_token(), response.refresh_token());
        }

        throw new RuntimeException("Error en la petición " + responseEntity.getStatusCode());
    }

    public TokenDTO refresh(String refreshToken) {

        String grantType = "refresh_token";

        String requestBody = String.format("client_id=%s&refresh_token=%s&grant_type=%s", clientID, refreshToken, grantType);

        ResponseEntity<TokenResponseDTO> responseEntity = tokenFeignClient.sendRequest(requestBody);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            TokenResponseDTO response = responseEntity.getBody();
            return new TokenDTO(response.access_token(), response.refresh_token());
        }

        throw new RuntimeException("Error en la petición " + responseEntity.getStatusCode());
    }

    public Boolean createUser(UserDTO userDTO, String token) {
        return createUser(userDTO, "app_user", token);
    }

    public Boolean createUser(UserDTO userDTO, String rol, String token) {

        ResponseEntity<String> responseEntity = userFeignClient.sendCreateUserRequest(token, userDTO);
        UserSensibleDTO userFound = searchUser(token, userDTO.username());
        Boolean roleAssigned = assignRole(token, userFound.id(), rol);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED && roleAssigned) {
            return true;
        }
        throw new RuntimeException("Error en la petición " + responseEntity.getStatusCode());
    }

    public UserSensibleDTO searchUser(String token, String userName) {
        ResponseEntity<List<UserSensibleDTO>> responseEntity = userFeignClient.searchUserByUsername(token, userName);

        if (responseEntity.getStatusCode() == HttpStatus.OK ) {
            return Objects.requireNonNull(responseEntity.getBody()).get(0);
        }

        throw new RuntimeException("Error en la petición buscar usuario" + responseEntity.getStatusCode());
    }

    public Boolean assignRole(String token, String userId, String role) {
        RealmRoleDTO[] roles = getRealmRoleDTOS(role);

        ResponseEntity<String> responseEntity = userFeignClient.sendAssingRolUserRequest(token, userId, roles);
        if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
            return true;
        }
        throw new RuntimeException("Error en la petición asignar Role" + responseEntity.getStatusCode());
    }

    private static RealmRoleDTO[] getRealmRoleDTOS(String role) {
        RealmRoleDTO[] roles = new RealmRoleDTO[0];
        if ("app_user".equals(role)) {
            roles = new RealmRoleDTO[]{
                    new RealmRoleDTO("43409e27-adc9-44dd-8b39-9b29b0c6e620", "app_user"),
            };
        } else if ("app_collaborator".equals(role)) {
            roles = new RealmRoleDTO[]{
                    new RealmRoleDTO("94f9fbd4-beab-47e7-bcac-113d23c6c73e", "app_collaborator")
            };
        } else if ("app_admin".equals(role)) {
            roles = new RealmRoleDTO[]{
                    new RealmRoleDTO("8484710b-5b9c-4d66-abe9-8fa3f950ebce", "app_admin")
            };
        }
        return roles;
    }
}
