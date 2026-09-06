package com.eduardo.ControleEstoque.infra.mapper;

import com.eduardo.ControleEstoque.DTO.CategoriaDTO;
import com.eduardo.ControleEstoque.DTO.ProdutoDTO;
import com.eduardo.ControleEstoque.Model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    private final CategoriaMapper categoriaMapper;

    public ProdutoMapper(CategoriaMapper categoriaMapper) {
        this.categoriaMapper = categoriaMapper;
    }

    public ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade(),
                categoriaMapper.toDTO(produto.getCategoria())
                );
    }

}
