package com.eduardo.ControleEstoque.DTO;

import com.eduardo.ControleEstoque.Model.TipoMovimentacao;

import java.time.LocalDateTime;

public record MovimentacaoDTO(

        Long id,
        Long produtoId,
        int quantidade,
        TipoMovimentacao tipoMovimentacao,
        LocalDateTime dataHora

) {
}
