package com.kaniel.product.exception;

public class CategoriaJaExistenteException extends RuntimeException {
    public CategoriaJaExistenteException(String nome) {
        super("Já existe uma categoria com o nome: "+ nome);
    }
}
