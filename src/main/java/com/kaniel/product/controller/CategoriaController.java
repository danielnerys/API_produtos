package com.kaniel.product.controller;

import com.kaniel.product.dto.request.CategoriaRequestDTO;

import com.kaniel.product.dto.response.CategoriaResumoDTO;
import com.kaniel.product.mapper.CategoriaMapper;

import com.kaniel.product.service.CategoriaService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;
    private final CategoriaMapper categoriaMapper;

    @PostMapping
    public ResponseEntity<CategoriaResumoDTO> cadastrar(@RequestBody @Valid CategoriaRequestDTO request) {
        return new ResponseEntity<>(categoriaMapper.toResponse(categoriaService.cadastrarCategoria(categoriaMapper.toEntity(request))), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<CategoriaResumoDTO>> listarTodos(){
        List<CategoriaResumoDTO> categoriasCadastradas = categoriaService.listarTodos().stream().map(categoriaMapper::toResponse).toList();

        return new ResponseEntity<>(categoriasCadastradas, HttpStatus.OK);
    }


}
