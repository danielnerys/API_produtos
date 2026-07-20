package com.kaniel.product.mapper;

import com.kaniel.product.dto.request.ProdutoRequestDTO;
import com.kaniel.product.dto.response.ProdutoResponseDTO;
import com.kaniel.product.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public ProdutoResponseDTO toResponse(Produto produto) {
        return new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidadeEstoque());
    }

    public Produto toEntity(ProdutoRequestDTO dto){
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque());

        return produto;

    }
}

