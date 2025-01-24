package com.forum.forumhub.repository;

import com.forum.forumhub.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

//Entidade e o tipo da chave primária como parâmetros no generics do JpaRepository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    UserDetails findByLogin(String login); //Método que faz consulta de usuário no banco de dados na tabela usuarios
}
