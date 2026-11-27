package com.loja.api.repository;

import com.loja.api.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    boolean existsByProdutoId(Long produtoId);
}