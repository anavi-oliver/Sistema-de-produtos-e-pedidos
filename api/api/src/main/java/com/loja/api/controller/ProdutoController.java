package com.loja.api.controller;

import com.loja.api.model.Produto;
import com.loja.api.service.ProdutoService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService service;
    public ProdutoController(ProdutoService service) { this.service = service; }

    @GetMapping
    public List<Produto> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto) {
        Produto salvo = service.criar(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(salvo.getId()).toUri();
        return ResponseEntity.created(uri).body(salvo);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
        return service.atualizar(id, produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
