package com.kaniel.product.controller;

import com.kaniel.product.dto.request.CategoriaRequestDTO;

import com.kaniel.product.dto.response.CategoriaResumoDTO;
import com.kaniel.product.mapper.CategoriaMapper;

import com.kaniel.product.repository.CategoriaRepository;
import com.kaniel.product.service.CategoriaService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<Page<CategoriaResumoDTO>> listarTodasCategorias(Pageable pageable){
        Page<CategoriaResumoDTO> todasCategorias = categoriaRepository.findAll(pageable).map(categoriaMapper::toResponse);

        return ResponseEntity.ok(todasCategorias);
    }

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
