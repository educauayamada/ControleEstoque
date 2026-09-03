package com.eduardo.ControleEstoque.Service;

import com.eduardo.ControleEstoque.DTO.CategoriaDTO;
import com.eduardo.ControleEstoque.DTO.ProdutoDTO;
import com.eduardo.ControleEstoque.DTO.ProdutoUpdateDTO;
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

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO) {
        Categoria categoria = categoriaRepository.findById(produtoDTO.categoria().id())
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada."));

        Produto produto = new Produto();

        produto.setNome(produtoDTO.nome());
        produto.setPreco(produtoDTO.preco());
        produto.setQuantidade(produtoDTO.quantidade());
        produto.setCategoria(categoria);


        Produto produtoSalvo = produtoRepository.save(produto);

        return new ProdutoDTO(
                produtoSalvo.getId(),
                produtoSalvo.getNome(),
                produtoSalvo.getPreco(),
                produtoSalvo.getQuantidade(),
                new CategoriaDTO(
                        produtoSalvo.getCategoria().getId(),
                        produtoSalvo.getCategoria().getNome()
                )
        );
    }

    public List<ProdutoDTO> listarProdutos() {
        return produtoRepository.findAll()
                .stream()
                .map(p -> new ProdutoDTO(
                        p.getId(),
                        p.getNome(),
                        p.getPreco(),
                        p.getQuantidade(),
                        new CategoriaDTO(
                                p.getCategoria().getId(),
                                p.getCategoria().getNome()
                        )
                ))
                .toList();
    }

    public ProdutoDTO listarProdutoPorId(Long id) {

        return produtoRepository.findById(id)
                .map(p -> new ProdutoDTO(
                        p.getId(),
                        p.getNome(),
                        p.getPreco(),
                        p.getQuantidade(),
                        new CategoriaDTO(
                                p.getCategoria().getId(),
                                p.getCategoria().getNome()
                        )
                ))
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado."));

    }

    public void deletarProdutoPorId(Long id) {

        Produto produtoEntity = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado."));

        produtoRepository.delete(produtoEntity);

    }

    public ProdutoDTO atualizarProdutoPorId(Long id, ProdutoUpdateDTO produto) {

        Produto produtoEntity = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado."));

        produtoEntity.setNome(produto.nome());
        produtoEntity.setPreco(produto.preco());

        Categoria categoria = categoriaRepository.findById(produto.categoria().id())
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada"));

        produtoEntity.setCategoria(categoria);
        Produto produtoAtualizado = produtoRepository.save(produtoEntity);

        return new ProdutoDTO(
                produtoAtualizado.getId(),
                produtoAtualizado.getNome(),
                produtoAtualizado.getPreco(),
                produtoAtualizado.getQuantidade(),
                new CategoriaDTO(
                        produtoAtualizado.getCategoria().getId(),
                        produtoAtualizado.getCategoria().getNome()
                )
        );

    }

    public List<ProdutoDTO> listarProdutosEstoqueBaixo(int limite){

        return produtoRepository.findByQuantidadeLessThanEqual(limite)
                .stream()
                .map(p -> new ProdutoDTO(
                        p.getId(),
                        p.getNome(),
                        p.getPreco(),
                        p.getQuantidade(),
                        new CategoriaDTO(
                                p.getCategoria().getId(),
                                p.getCategoria().getNome()
                        )
                ))
                .toList();

    }

    public List<ProdutoDTO> listarProdutosPorNome(String nome){

        return produtoRepository.findByNomeContaining(nome)
                .stream()
                .map(p -> new ProdutoDTO(
                        p.getId(),
                        p.getNome(),
                        p.getPreco(),
                        p.getQuantidade(),
                        new CategoriaDTO(
                                p.getCategoria().getId(),
                                p.getCategoria().getNome()
                        )
                ))
                .toList();

    }

    public List<ProdutoDTO> listarProdutos(String nome){

        if(nome == null) {

            return listarProdutos();

        }

        return listarProdutosPorNome(nome);

    }

}
