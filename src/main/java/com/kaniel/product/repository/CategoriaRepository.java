package com.kaniel.product.repository;

import com.kaniel.product.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository  extends JpaRepository<Categoria, UUID> {

    boolean existsByNome(String nome);

}
