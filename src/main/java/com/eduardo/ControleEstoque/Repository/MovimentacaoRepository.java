package com.eduardo.ControleEstoque.Repository;

import com.eduardo.ControleEstoque.DTO.MovimentacaoDTO;
import com.eduardo.ControleEstoque.Model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    List<Movimentacao> findByProduto_Id(Long produtoId);

}
