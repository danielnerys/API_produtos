package com.kaniel.product.exception;

public class ProdutoJaExistenteException extends RuntimeException {
    public ProdutoJaExistenteException(String nome) {
        super("Produto já cadastrado com o nome: "+ nome);
    }
}
