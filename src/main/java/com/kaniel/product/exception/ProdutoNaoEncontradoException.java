package com.kaniel.product.exception;

import java.util.UUID;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(UUID uuid) {
        super("Produto não encontrado para o id " + uuid);
    }
}
