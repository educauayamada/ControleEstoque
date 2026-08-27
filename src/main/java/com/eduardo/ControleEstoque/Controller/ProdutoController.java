package com.eduardo.ControleEstoque.Controller;

import com.eduardo.ControleEstoque.Model.Produto;
import com.eduardo.ControleEstoque.Service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto (@RequestBody Produto produto) {

        Long idCategoria = produto.getCategoria().getId();

        Produto novoProduto = produtoService.cadastrarProduto(produto, idCategoria);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(novoProduto);

    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {

        List<Produto> produtoList = produtoService.listarProdutos();

        return ResponseEntity.ok()
                .body(produtoList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> listarProdutoPorId(@PathVariable Long id) {

        Produto produtoId = produtoService.listarProdutoPorId(id);

        return ResponseEntity.ok()
                .body(produtoId);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable Long id) {

        produtoService.deletarProdutoPorId(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProdutoPorId( @PathVariable Long id,
                                                          @RequestBody Produto produto) {
       Produto produtoAtualizado = produtoService.atualizarProdutoPorId(id, produto);

       return ResponseEntity.ok()
               .body(produtoAtualizado);
    }

}
