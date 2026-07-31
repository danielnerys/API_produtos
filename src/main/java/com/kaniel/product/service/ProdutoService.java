package com.kaniel.product.service;

import com.kaniel.product.dto.request.ProdutoAtualizacaoDTO;
import com.kaniel.product.dto.request.ProdutoVendaDTO;
import com.kaniel.product.exception.CategoriaNaoEncontradaException;
import com.kaniel.product.exception.EstoqueInsuficienteException;
import com.kaniel.product.exception.ProdutoJaExistenteException;
import com.kaniel.product.exception.ProdutoNaoEncontradoException;
import com.kaniel.product.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.kaniel.product.model.Produto;
import org.springframework.stereotype.Service;
import com.kaniel.product.repository.ProdutoRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;


    public Produto cadastrarProduto(Produto produto) {
        if (produtoRepository.existsByNome(produto.getNome().trim())) {
            throw new ProdutoJaExistenteException(produto.getNome());
        }

        return produtoRepository.save(produto);
    }

    public List<Produto> buscarProdutoPorNome(String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }

    public Produto atualizarProduto(UUID uuid, ProdutoAtualizacaoDTO dto) {
        Produto produtoASerAtualizado = produtoRepository.findById(uuid).orElseThrow(() -> new ProdutoNaoEncontradoException(uuid));

        if (dto.nome() != null) {
            produtoASerAtualizado.setNome(dto.nome());
        }
        if (dto.preco() != null) {
            produtoASerAtualizado.setPreco(dto.preco());
        }
        if (dto.quantidadeEstoque() != null) {
            produtoASerAtualizado.setQuantidadeEstoque(dto.quantidadeEstoque());
        }
        if (dto.categoriaId() != null) {
            produtoASerAtualizado.setCategoria(categoriaRepository.findById(dto.categoriaId()).orElseThrow(() -> new CategoriaNaoEncontradaException(dto.categoriaId())));
        }

        return produtoRepository.save(produtoASerAtualizado);

    }

    public void deletarProduto(UUID id){
        Produto produtoASerDeletado = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        produtoRepository.delete(produtoASerDeletado);
    }

    @Transactional
    public Produto venderProduto(UUID id, ProdutoVendaDTO dto){
        Produto produtoASerVendido = produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        if(dto.quantidade() > produtoASerVendido.getQuantidadeEstoque()){
            throw new EstoqueInsuficienteException(id, produtoASerVendido.getQuantidadeEstoque());
        }
        produtoASerVendido.setQuantidadeEstoque(produtoASerVendido.getQuantidadeEstoque()-dto.quantidade());

        return produtoRepository.save(produtoASerVendido);



    }


}


