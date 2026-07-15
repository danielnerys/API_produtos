package com.kaniel.product.repository;

import com.kaniel.product.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    List<Produto> findByNomeContaining(String nome);

    boolean existsByNome(String nome);

}
