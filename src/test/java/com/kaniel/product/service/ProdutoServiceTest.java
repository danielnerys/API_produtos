package com.kaniel.product.service;

import com.kaniel.product.exception.ProdutoJaExistenteException;
import com.kaniel.product.model.Produto;
import com.kaniel.product.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {
    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;
    @Test
    void deveCadastrarComSucessoQuandoNomeNaoExiste() {
        // arrange: cria o produto para teste
        Produto produto = new Produto();
        produto.setNome("Tesoura");

        // arrange 2 : configurar comportamento do mock

        when(produtoRepository.existsByNome("Tesoura")).thenReturn(false);
        when(produtoRepository.save(produto)).thenReturn(produto);

        //3 act: chamar o metodo que esta sendo testado de verdade
        Produto resultado = produtoService.cadastrarProduto(produto);


        // 4 assert: verificar se o resultado é o esperado

        assertEquals("Tesoura", resultado.getNome());
        verify(produtoRepository, times(1)).save(produto);


    }

    @Test
    void NaoDeveCadastrarComSucessoQuandoNomeJaExiste(){
        Produto produto = new Produto();
        produto.setNome("Tesoura");

        when(produtoRepository.existsByNome("Tesoura")).thenReturn(true);


        assertThrows(ProdutoJaExistenteException.class, () -> produtoService.cadastrarProduto(produto));
        verify(produtoRepository, never()).save(any());

    }

    @Test
    void buscarProdutoPorNome() {
        // arrange 1: cria o produto para teste
        Produto produto1 = new Produto();
        produto1.setNome("Faca fina");
        Produto produto2 = new Produto();
        produto2.setNome("Faca media");


        List<Produto> resultado = List.of(produto1, produto2);

        // arrange 2: configurar comportamento do mock
        when(produtoRepository.findByNomeContaining("Faca")).thenReturn(resultado);

        // arrange 3 : cria o produto para teste
        resultado = produtoService.buscarProdutoPorNome("Faca");


        // 4 assert: verificar se o resultado é o esperado
        verify(produtoRepository, times(1)).findByNomeContaining("Faca");
        assertEquals(2, resultado.size());
    }
}