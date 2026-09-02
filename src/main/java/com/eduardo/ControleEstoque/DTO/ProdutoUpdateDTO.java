package com.eduardo.ControleEstoque.DTO;

import java.math.BigDecimal;

public record ProdutoUpdateDTO(

        Long id,
        String nome,
        BigDecimal preco,
        CategoriaDTO categoria

) {
}
