package com.eduardo.ControleEstoque.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaDTO(

        Long id,

        @Size(max = 100)
        @NotBlank(message = "O nome da categoria não pode estar em branco")
        String nome

) {
}
