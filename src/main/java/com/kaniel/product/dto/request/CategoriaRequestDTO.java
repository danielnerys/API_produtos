package com.kaniel.product.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequestDTO(
        @NotBlank String nome
) {
}
