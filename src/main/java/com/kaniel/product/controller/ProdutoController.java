package com.kaniel.product.controller;

import com.kaniel.product.dto.request.ProdutoAtualizacaoDTO;
import com.kaniel.product.dto.request.ProdutoRequestDTO;
import com.kaniel.product.dto.request.ProdutoVendaDTO;
import com.kaniel.product.dto.response.ProdutoResponseDTO;
import com.kaniel.product.mapper.ProdutoMapper;
import org.springframework.data.domain.Page;
import com.kaniel.product.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kaniel.product.service.ProdutoService;

import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;
    private final ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody @Valid ProdutoRequestDTO request) {
        return new ResponseEntity<>(produtoMapper.toResponse(produtoService.cadastrarProduto(produtoMapper.toEntity(request))), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorNome(@RequestParam String nome) {
        List<ProdutoResponseDTO> produtos = produtoService.buscarProdutoPorNome(nome).stream().map(produtoMapper::toResponse).toList();

        return ResponseEntity.ok(produtos);

    }


    @GetMapping("/todos")
    public ResponseEntity<Page<ProdutoResponseDTO>> listarTodos(Pageable pageable) {
        Page<ProdutoResponseDTO> produtosPaginados = produtoRepository.findAll(pageable).map(produtoMapper::toResponse);

        return ResponseEntity.ok(produtosPaginados);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable UUID id, @RequestBody @Valid ProdutoAtualizacaoDTO dto){
        return new ResponseEntity<>(produtoMapper.toResponse(produtoService.atualizarProduto(id, dto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID id){
       produtoService.deletarProduto(id);
       return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{id}/vender")
    public ResponseEntity<ProdutoResponseDTO> venderProduto(@PathVariable UUID id, @RequestBody @Valid ProdutoVendaDTO dto){
        return new ResponseEntity<>(produtoMapper.toResponse(produtoService.venderProduto(id, dto)), HttpStatus.OK );
    }








}
