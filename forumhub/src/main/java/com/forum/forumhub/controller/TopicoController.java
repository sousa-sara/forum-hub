package com.forum.forumhub.controller;

import com.forum.forumhub.dto.TopicoDTO;
import com.forum.forumhub.entity.Topico;
import com.forum.forumhub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<Topico> createTopico(@RequestBody @Valid TopicoDTO topicoDTO) {
        Topico topico = topicoService.save(topicoDTO);
        return new ResponseEntity<>(topico, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> getTopicoById(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.findById(id);
        return topico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Topico>> getAllTopicos() {
        List<Topico> topicos = topicoService.findAll();
        return ResponseEntity.ok(topicos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
        topicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> updateTopico(@PathVariable Long id, @RequestBody @Valid TopicoDTO topicoDTO) {
        Optional<Topico> updatedTopico = topicoService.update(id, topicoDTO);
        return updatedTopico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
