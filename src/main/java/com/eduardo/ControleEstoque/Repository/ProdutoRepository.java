package com.eduardo.ControleEstoque.Repository;

import com.eduardo.ControleEstoque.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
