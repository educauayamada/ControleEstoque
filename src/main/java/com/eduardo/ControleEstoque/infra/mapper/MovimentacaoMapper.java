package com.eduardo.ControleEstoque.infra.mapper;

import com.eduardo.ControleEstoque.DTO.MovimentacaoDTO;
import com.eduardo.ControleEstoque.Model.Movimentacao;
import org.springframework.stereotype.Component;

@Component
public class MovimentacaoMapper {

    public MovimentacaoDTO toDTO(Movimentacao movimentacao) {

        return new MovimentacaoDTO(
                movimentacao.getId(),
                movimentacao.getProduto().getId(),
                movimentacao.getQuantidade(),
                movimentacao.getTipoMovimentacao(),
                movimentacao.getDataHora()
        );

    }

}
