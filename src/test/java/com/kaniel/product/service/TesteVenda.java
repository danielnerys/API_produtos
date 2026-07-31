package com.kaniel.product.service;

import com.kaniel.product.dto.request.ProdutoVendaDTO;
import com.kaniel.product.exception.EstoqueInsuficienteException;
import com.kaniel.product.model.Produto;
import com.kaniel.product.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TesteVenda {
    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    @DisplayName("Deve reduzir a quantidade de estoque do produto selecionado por ID")
    void deveReduzirEstoqueDoProduto(){
        //arrange
        Produto produtoTeste = new Produto();
        produtoTeste.setId(UUID.randomUUID());
        produtoTeste.setQuantidadeEstoque(100);

        ProdutoVendaDTO dto = new ProdutoVendaDTO(50);


        when(produtoRepository.findById(produtoTeste.getId())).thenReturn(Optional.of(produtoTeste));

        //ACT
        produtoService.venderProduto(produtoTeste.getId(), dto);


        //ASSERT
        assertEquals(50, produtoTeste.getQuantidadeEstoque());

        verify(produtoRepository, times(1)).save(produtoTeste);



    }

    @Test
    @DisplayName("Deve lançar excecao quando o estoque não for suficiente")
    void deveLancarExcecaoQuandoEstoqueForInsuficiente(){
        //arrange
        Produto produtoTeste = new Produto();
        produtoTeste.setId(UUID.randomUUID());
        produtoTeste.setQuantidadeEstoque(100);

        ProdutoVendaDTO dto = new ProdutoVendaDTO(101);

        when(produtoRepository.findById(produtoTeste.getId())).thenReturn(Optional.of(produtoTeste));


        //act e assert juntos para capturar excecao
        EstoqueInsuficienteException exception = assertThrows(
                EstoqueInsuficienteException.class,
                () -> produtoService.venderProduto(produtoTeste.getId(), dto)
        );

        verify(produtoRepository, never()).save(any(Produto.class));
    }
}
