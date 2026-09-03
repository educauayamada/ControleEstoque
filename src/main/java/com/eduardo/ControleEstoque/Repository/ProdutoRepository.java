package com.eduardo.ControleEstoque.Repository;

import com.eduardo.ControleEstoque.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByQuantidadeLessThanEqual (int limite);

    List<Produto> findByNomeContaining (String nome);

}
