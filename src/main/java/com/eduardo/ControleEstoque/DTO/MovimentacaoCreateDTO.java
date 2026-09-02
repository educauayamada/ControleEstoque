package com.eduardo.ControleEstoque.DTO;

import com.eduardo.ControleEstoque.Model.TipoMovimentacao;

public record MovimentacaoCreateDTO(

        Long produtoId,
        int quantidade,
        TipoMovimentacao tipoMovimentacao

) {
}
