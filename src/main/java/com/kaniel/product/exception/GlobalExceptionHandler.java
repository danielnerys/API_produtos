package com.kaniel.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProdutoJaExistenteException.class)
    public ResponseEntity<Map<String, String>> tratarProdutoJaExistente(ProdutoJaExistenteException exception) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> tratarCamposVazios(MethodArgumentNotValidException exception){
        Map<String, String> erros = new HashMap<>();

        for(FieldError erro : exception.getBindingResult().getFieldErrors()){
            erros.put(erro.getField(), erro.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }
    @ExceptionHandler(CategoriaJaExistenteException.class)
    public ResponseEntity<Map<String, String>> tratarCategoriaJaExistente(CategoriaJaExistenteException exception){
        Map<String, String> erro = new HashMap<>();

        erro.put("mensagem", exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }
}
