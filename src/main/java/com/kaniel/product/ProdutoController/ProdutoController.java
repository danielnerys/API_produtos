package com.kaniel.product.ProdutoController;

import com.kaniel.product.dto.request.ProdutoRequestDTO;
import com.kaniel.product.dto.response.ProdutoResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.kaniel.product.model.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.kaniel.product.service.ProdutoService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@RequestBody @Valid ProdutoRequestDTO request) {
        Produto novoProduto = new Produto();
        novoProduto.setNome(request.nome());
        novoProduto.setPreco(request.preco());
        novoProduto.setQuantidadeEstoque(request.quantidadeEstoque());
        return new ResponseEntity<>(toResponse(produtoService.cadastrarProduto(novoProduto)), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorNome(@RequestParam String nome){
        List<ProdutoResponseDTO> produtos = produtoService.buscarProdutoPorNome(nome).stream().map(this::toResponse).toList();

        return ResponseEntity.ok(produtos);



    }


    private ProdutoResponseDTO toResponse(Produto produto) {
        return new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidadeEstoque());
    }


}
