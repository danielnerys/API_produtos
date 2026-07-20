package com.kaniel.product.mapper;

import com.kaniel.product.dto.request.ProdutoRequestDTO;
import com.kaniel.product.dto.response.CategoriaResumoDTO;
import com.kaniel.product.dto.response.ProdutoResponseDTO;
import com.kaniel.product.exception.CategoriaNaoEncontradaException;
import com.kaniel.product.model.Categoria;
import com.kaniel.product.model.Produto;
import com.kaniel.product.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProdutoMapper {
    private final CategoriaRepository categoriaRepository;

    public ProdutoResponseDTO toResponse(Produto produto) {
        CategoriaResumoDTO categoriaResumoDTO = new CategoriaResumoDTO(produto.getCategoria().getId(), produto.getCategoria().getNome());

        return new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco(), produto.getQuantidadeEstoque(),
                categoriaResumoDTO);
    }

    public Produto toEntity(ProdutoRequestDTO dto){
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque());
        produto.setCategoria(categoriaRepository.findById(dto.categoriaId()).orElseThrow(() -> new CategoriaNaoEncontradaException(dto.categoriaId())));

        return produto;

    }
}

