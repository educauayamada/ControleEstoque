package com.eduardo.ControleEstoque.DTO;

import com.eduardo.ControleEstoque.Model.TipoMovimentacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovimentacaoCreateDTO(

        @NotNull(message = "O ID do produto é obrigatório")
        Long produtoId,

        @Positive(message = "A quantidade deve ser maior que zero")
        int quantidade,

        @NotNull(message = "O tipo de movimentação é obrigatório")
        TipoMovimentacao tipoMovimentacao

) {
}
