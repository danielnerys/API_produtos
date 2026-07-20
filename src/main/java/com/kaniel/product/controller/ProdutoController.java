package com.kaniel.product.controller;

import com.kaniel.product.dto.request.ProdutoRequestDTO;
import com.kaniel.product.dto.response.ProdutoResponseDTO;
import com.kaniel.product.mapper.ProdutoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kaniel.product.service.ProdutoService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody @Valid ProdutoRequestDTO request) {
        return new ResponseEntity<>(produtoMapper.toResponse(produtoService.cadastrarProduto(produtoMapper.toEntity(request))), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorNome(@RequestParam String nome){
        List<ProdutoResponseDTO> produtos = produtoService.buscarProdutoPorNome(nome).stream().map(produtoMapper::toResponse).toList();

        return ResponseEntity.ok(produtos);



    }





}
