package com.forum.forumhub.service;

import com.forum.forumhub.entity.Topico;
import com.forum.forumhub.dto.TopicoDTO;

import java.util.List;
import java.util.Optional;

public interface TopicoService {
    Topico save(TopicoDTO topicoDTO);
    Optional<Topico> findById(Long id);
    List<Topico> findAll();
    void delete(Long id);
    Optional<Topico> update(Long id, TopicoDTO topicoDTO);
}
