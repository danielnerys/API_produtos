package com.kaniel.product.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record ProdutoVendaDTO(
        @NotNull @Positive Integer quantidade
) {
}
