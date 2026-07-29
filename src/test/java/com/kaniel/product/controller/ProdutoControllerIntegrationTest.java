package com.kaniel.product.controller;

import com.kaniel.product.dto.request.ProdutoRequestDTO;

import com.kaniel.product.model.Categoria;
import com.kaniel.product.repository.CategoriaRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CategoriaRepository categoriaRepository;

    private Categoria categoriaTeste;


    @BeforeEach
    void save() {
        categoriaTeste = new Categoria();
        categoriaTeste.setNome("teste");
        categoriaTeste = categoriaRepository.save(categoriaTeste);
    }

    @Test
    void testeCriarProduto() throws Exception {
        ProdutoRequestDTO produtoRequestDTO = new ProdutoRequestDTO("TESTE", BigDecimal.valueOf(99.9), 4, categoriaTeste.getId());

        String resultado = objectMapper.writeValueAsString(produtoRequestDTO);

        mockMvc.perform(post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(resultado)
        ).andExpect(status().isCreated());


    }

    @Test
    void testeCriarProdutoComCategoriaInexistent() throws Exception{
        ProdutoRequestDTO produtoRequestDTO = new ProdutoRequestDTO("Teste2", BigDecimal.valueOf(99.22), 4, UUID.randomUUID());

        String resultado = objectMapper.writeValueAsString(produtoRequestDTO);

        mockMvc.perform(post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(resultado)).andExpect(status().isNotFound());


    }


}
