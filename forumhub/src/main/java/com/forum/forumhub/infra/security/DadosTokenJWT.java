package com.forum.forumhub.infra.security;

//'record' é uma maneira concisa de criar classes imutáveis, usadas principalmente para transportar dados (Data Transfer Objects - DTOs)
public record DadosTokenJWT(String token) {
}
