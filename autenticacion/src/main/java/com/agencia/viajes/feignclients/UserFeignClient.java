package com.agencia.viajes.feignclients;

import com.agencia.viajes.dto.RealmRoleDTO;
import com.agencia.viajes.dto.UserDTO;
import com.agencia.viajes.dto.UserSensibleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "UserKeycloak", url = "${keycloack.admin-url}")
public interface UserFeignClient {

    @PostMapping(path = "/users")
    ResponseEntity<String> sendCreateUserRequest(@RequestHeader("Authorization") String token, UserDTO requestBody);

    @GetMapping("/users?username={userName}")
    ResponseEntity<List<UserSensibleDTO>> searchUserByUsername(@RequestHeader("Authorization") String token, @PathVariable("userName") String userName);

    @PostMapping(path = "/users/{userId}/role-mappings/realm")
    ResponseEntity<String> sendAssingRolUserRequest(
            @RequestHeader("Authorization") String token,
            @PathVariable("userId") String userId,
            RealmRoleDTO[] roleDTO
    );
}
