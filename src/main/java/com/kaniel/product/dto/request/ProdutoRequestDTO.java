package com.kaniel.product.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoRequestDTO(
        @NotBlank String nome,
        @NotNull @Positive BigDecimal preco,
        @NotNull @PositiveOrZero Integer quantidadeEstoque,
        @NotNull UUID categoriaId
) {
}
