package com.forum.forumhub.infra.security;
import com.forum.forumhub.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//Component, pois é um componente genérico, para que o Spring carregue a classe automaticamente
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    //Precisa chamar a próxima lógica para o filtro funcionar no fluxo, pois ele subentende que teria um próximo filtro e termina a requisição
    //Os filtros tem ordem, então precisa controlar a ordem de execução dos filtros
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //System.out.println("FILTRO CHAMADO!!"); //Dá para verificar que ele termina aqui se não chamar o próximo filtro

        //Cabeçalho authorization (para enviar o token) -> AUTH -> BEARER TOKEN -> COLOCAR TOKEN
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            var subject = tokenService.getSubject((tokenJWT));

            //Lógica da autenticação forçada para considerar que o usuário está logado
            var usuario = repository.findByLogin(subject);
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication((authentication));
        }

        filterChain.doFilter(request, response); //Necessário para os próximos filtros na aplicação
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}