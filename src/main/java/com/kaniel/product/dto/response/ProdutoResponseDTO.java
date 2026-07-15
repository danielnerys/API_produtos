package com.kaniel.product.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoResponseDTO(
        UUID id,
        String nome,
        BigDecimal preco,
        Integer quantidadeEstoque
) {
}
