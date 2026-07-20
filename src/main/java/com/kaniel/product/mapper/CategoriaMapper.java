package com.kaniel.product.mapper;

import com.kaniel.product.dto.request.CategoriaRequestDTO;
import com.kaniel.product.dto.response.CategoriaResumoDTO;
import com.kaniel.product.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequestDTO dto){
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());

        return categoria;
    }


    public CategoriaResumoDTO toResponse(Categoria categoria){
        return new CategoriaResumoDTO(categoria.getId(), categoria.getNome());
    }



}
