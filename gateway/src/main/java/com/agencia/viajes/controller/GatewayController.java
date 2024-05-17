package com.agencia.viajes.controller;

import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/api/test")
public class GatewayController {

    @GetMapping("/anonymous")
    public Mono<String> getAnonymous() {
        System.out.println("--------------------------------------------ENTRO AL ENDPOINT /anonymous------------------------------------------------------");
        return Mono.just("Hola anónimo!");
    }

    @GetMapping("/admin")
    public Mono<String> getAdmin(Principal principal) {
        System.out.println("--------------------------------------------ENTRO AL ENDPOINT /admin------------------------------------------------------");
        System.out.println(principal);
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        String username = (String) token.getTokenAttributes().get("name");
        String email = (String) token.getTokenAttributes().get("email");

        return Mono.just("Hola admin! " + username + " " + email);
    }

    @GetMapping("/user")
    public Mono<String> getUser(Principal principal) {
        System.out.println("--------------------------------------------ENTRO AL ENDPOINT /user------------------------------------------------------");
        System.out.println(principal);
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        String username = (String) token.getTokenAttributes().get("name");
        String email = (String) token.getTokenAttributes().get("email");

        return Mono.just("Hola usuario! " + username + " " + email);
    }
}

