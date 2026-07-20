package com.kaniel.product.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 255, nullable = false)
    @NotBlank
    private String nome;

    @Column(name = "preco", nullable = false, precision = 10, scale = 2)
    @Positive
    @NotNull
    private BigDecimal preco;


    @Column(name = "quantidade_estoque", nullable = false)
    private int quantidadeEstoque;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
}
