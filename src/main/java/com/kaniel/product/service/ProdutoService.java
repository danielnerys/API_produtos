package com.kaniel.product.service;

import com.kaniel.product.exception.ProdutoJaExistenteException;
import lombok.RequiredArgsConstructor;
import com.kaniel.product.model.Produto;
import org.springframework.stereotype.Service;
import com.kaniel.product.repository.ProdutoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;


    public Produto cadastrarProduto(Produto produto) {
        if (produtoRepository.existsByNome(produto.getNome().trim())) {
            throw new ProdutoJaExistenteException(produto.getNome());
        }

        return produtoRepository.save(produto);
    }

    public List<Produto> buscarProdutoPorNome(String nome){
        return produtoRepository.findByNomeContaining(nome);
    }

}


