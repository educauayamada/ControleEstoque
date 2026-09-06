package com.eduardo.ControleEstoque.infra.mapper;

import com.eduardo.ControleEstoque.DTO.CategoriaDTO;
import com.eduardo.ControleEstoque.Model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaDTO toDTO (Categoria categoria) {

        return new CategoriaDTO(
                categoria.getId(),
                categoria.getNome()
        );

    }

}
