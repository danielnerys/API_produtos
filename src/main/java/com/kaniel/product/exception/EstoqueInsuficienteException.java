package com.kaniel.product.exception;

import java.util.UUID;

public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(UUID id, int estoque) {
        super("O produto com id: "+ id + " não possui estoque suficiente, seu estoque atual é de: "+ estoque);
    }
}
