package com.eduardo.ControleEstoque.Service;

import com.eduardo.ControleEstoque.Exception.CategoriaNotFoundException;
import com.eduardo.ControleEstoque.Exception.ProdutoNotFoundException;
import com.eduardo.ControleEstoque.Model.Categoria;
import com.eduardo.ControleEstoque.Model.Produto;
import com.eduardo.ControleEstoque.Repository.CategoriaRepository;
import com.eduardo.ControleEstoque.Repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    ProdutoRepository produtoRepository;
    CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto cadastrarProduto(Produto produto, Long idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrado."));

        produto.setCategoria(categoria);

        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto listarProdutoPorId(Long id) {

        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado."));

    }

    public void deletarProdutoPorId(Long id) {

        Produto produtoEntity = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado."));

        produtoRepository.delete(produtoEntity);

    }

    public Produto atualizarProdutoPorId(Long id, Produto produto) {

        Produto produtoEntity = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado."));

        produtoEntity.setNome(produto.getNome());
        produtoEntity.setPreco(produto.getPreco());

        return produtoRepository.save(produtoEntity);
    }

}
