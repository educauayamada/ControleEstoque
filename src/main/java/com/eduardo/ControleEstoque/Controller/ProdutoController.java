package com.eduardo.ControleEstoque.Controller;

import com.eduardo.ControleEstoque.DTO.ProdutoDTO;
import com.eduardo.ControleEstoque.DTO.ProdutoUpdateDTO;
import com.eduardo.ControleEstoque.Service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrarProduto (@RequestBody ProdutoDTO produto) {

        ProdutoDTO novoProduto = produtoService.cadastrarProduto(produto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(novoProduto);

    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {

        List<ProdutoDTO> produtoList = produtoService.listarProdutos();

        return ResponseEntity.ok()
                .body(produtoList);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> listarProdutoPorId(@PathVariable Long id) {

        ProdutoDTO produtoId = produtoService.listarProdutoPorId(id);

        return ResponseEntity.ok()
                .body(produtoId);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable Long id) {

        produtoService.deletarProdutoPorId(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProdutoPorId(@PathVariable Long id,
                                                            @RequestBody ProdutoUpdateDTO produto) {
        ProdutoDTO produtoAtualizado = produtoService.atualizarProdutoPorId(id, produto);

       return ResponseEntity.ok()
               .body(produtoAtualizado);
    }

}
