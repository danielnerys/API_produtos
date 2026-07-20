package com.kaniel.product.exception;

import java.util.UUID;

public class CategoriaNaoEncontradaException extends RuntimeException {
    public CategoriaNaoEncontradaException(UUID id) {
        super("categoria não encontrada com o ID: "+ id);
    }
}
