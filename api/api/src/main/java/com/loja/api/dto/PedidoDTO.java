package com.loja.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PedidoDTO (
    @NotNull(message = "o produto é obrigatório") Long produtoId,
    @NotNull(message = "a quantidade é obrigatória")
    @Min(value = 1, message = "a quantidade deve ser maior que zero") Integer quantidade
) {}

