package com.kaniel.product.service;
import com.kaniel.product.exception.ProdutoJaExistenteException;
import lombok.RequiredArgsConstructor;
import com.kaniel.product.model.Produto;
import org.springframework.stereotype.Service;
import com.kaniel.product.repository.ProdutoRepository;

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

}

//    public ProdutoResponseDTO toResponse(Produto produto){
//        return new ProdutoResponseDTO(
//                produto.getId(),
//                produto.getNome(),
//                produto.getPreco(),
//                produto.getQuantidadeEstoque()
//        );
//    }

