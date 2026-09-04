package com.eduardo.ControleEstoque.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProdutoDTO(

        Long id,

        @NotBlank(message = "Nome não pode estar em branco")
        String nome,

        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal preco,

        @PositiveOrZero(message = "A quantidade não pode ser negativa")
        int quantidade,

        @NotNull(message = "Produto precisa de uma categoria.")
        CategoriaDTO categoria

) {
}
