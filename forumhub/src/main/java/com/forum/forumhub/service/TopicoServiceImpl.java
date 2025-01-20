package com.forum.forumhub.service;

import com.forum.forumhub.entity.Topico;
import com.forum.forumhub.repository.TopicoRepository;
import com.forum.forumhub.dto.TopicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoServiceImpl implements TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public Topico save(TopicoDTO topicoDTO) {
        Topico topico = new Topico();
        topico.setTitulo(topicoDTO.getTitulo());
        topico.setMensagem(topicoDTO.getMensagem());
        topico.setAutor(topicoDTO.getAutor());
        topico.setCurso(topicoDTO.getCurso());
        topico.setStatus(topicoDTO.getStatus());
        topico.setDataCriacao(topicoDTO.getDataCriacao());
        return topicoRepository.save(topico);
    }

    @Override
    public Optional<Topico> findById(Long id) {
        return topicoRepository.findById(id);
    }

    @Override
    public List<Topico> findAll() {
        return topicoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        topicoRepository.deleteById(id);
    }

    public Optional<Topico> update(Long id, TopicoDTO topicoDTO) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.setTitulo(topicoDTO.getTitulo());
            topico.setMensagem(topicoDTO.getMensagem());
            topico.setAutor(topicoDTO.getAutor());
            topico.setCurso(topicoDTO.getCurso());
            topico.setStatus(topicoDTO.getStatus());
            // A data de criação não deve ser alterada, então não a atualizamos
            return Optional.of(topicoRepository.save(topico));
        } else {
            return Optional.empty();
        }
    }
}
