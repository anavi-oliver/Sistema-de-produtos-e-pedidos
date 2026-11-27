package com.loja.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "o nome é obrigatório")
    @Size(max = 100, message = "o nome deve ter no máximo 100 caracteres")
    private String nome;

    @Size(max = 500, message = "a descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "o preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "o preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "o estoque é obrigatório")
    @Min(value = 0, message = "o estoque deve ser maior ou igual a zero")
    private Integer estoque;

public String getDescricao() {
    return descricao;
}
public Integer getEstoque() {
    return estoque;
}
public Long getId() {
    return id;
}
public String getNome() {
    return nome;
}
public BigDecimal getPreco() {
    return preco;
}

public void setDescricao(String descricao) {
    this.descricao = descricao;
}
public void setEstoque(Integer estoque) {
    this.estoque = estoque;
}
public void setId(Long id) {
    this.id = id;
}
public void setNome(String nome) {
    this.nome = nome;
}
public void setPreco(BigDecimal preco) {
    this.preco = preco;
}

}