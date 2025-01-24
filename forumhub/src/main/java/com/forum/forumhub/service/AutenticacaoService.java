package com.forum.forumhub.service;

import com.forum.forumhub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //Explicita que é um serviço de autenticação para o Spring Security com a interface implementada abaixo
public class AutenticacaoService implements UserDetailsService {

    @Autowired //Injeção de dependência
    private UsuarioRepository repository;

    //Métodos que o Spring chama automaticamente no formulário de login do usuário do projeto
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLogin(username);
    }
}
