package com.eduardo.ControleEstoque.DTO;

import java.math.BigDecimal;

public record ProdutoDTO(

        Long id,
        String nome,
        BigDecimal preco,
        int quantidade,
        CategoriaDTO categoria

) {
}
