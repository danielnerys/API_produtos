package com.kaniel.product.service;


import com.kaniel.product.exception.CategoriaJaExistenteException;
import com.kaniel.product.model.Categoria;
import com.kaniel.product.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public Categoria cadastrarCategoria(Categoria categoria){
        if(categoriaRepository.existsByNome(categoria.getNome())){
            throw new CategoriaJaExistenteException(categoria.getNome());
        }
        return categoriaRepository.save(categoria);

    }

}
