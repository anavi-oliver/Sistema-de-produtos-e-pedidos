package com.loja.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime data;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

public LocalDateTime getData() {
    return data;
}
public Long getId() {
    return id;
}
public Produto getProduto() {
    return produto;
}
public Integer getQuantidade() {
    return quantidade;
}

public void setData(LocalDateTime data) {
    this.data = data;
}
public void setId(Long id) {
    this.id = id;
}
public void setProduto(Produto produto) {
    this.produto = produto;
}
public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
}

}
