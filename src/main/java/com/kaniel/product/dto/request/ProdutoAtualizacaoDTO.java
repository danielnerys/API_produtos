package com.kaniel.product.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoAtualizacaoDTO(
        String nome,
        @Positive BigDecimal preco,
        @PositiveOrZero Integer quantidadeEstoque,
        UUID categoriaId
) {
}