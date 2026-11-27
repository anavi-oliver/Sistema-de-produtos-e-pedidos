package com.loja.api.controller;

import com.loja.api.dto.PedidoDTO;
import com.loja.api.model.Pedido;
import com.loja.api.service.PedidoService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService service;
    public PedidoController(PedidoService service) { this.service = service; }

    @GetMapping
    public List<Pedido> listar() { 
        return service.listar(); 
    }

    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable Long id) { return service.buscar(id); 
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@Valid @RequestBody PedidoDTO req) {
        Pedido salvo = service.criar(req);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(salvo.getId()).toUri();
        return ResponseEntity.created(uri).body(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
