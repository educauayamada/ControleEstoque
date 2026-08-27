package com.eduardo.ControleEstoque.Controller;

import com.eduardo.ControleEstoque.Model.Categoria;
import com.eduardo.ControleEstoque.Service.CategoriaService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrarCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.cadastrarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(novaCategoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> listaCategorias = categoriaService.listarCategorias();
        return ResponseEntity.ok()
                .body(listaCategorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> listarCategoriaPorId(@PathVariable Long id){
       Categoria categoriaPorId = categoriaService.listarCategoriaPorId(id);
        return ResponseEntity.ok()
                .body(categoriaPorId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id,
                                                        @RequestBody Categoria categoria) {
        Categoria categoriaAtualizada = categoriaService.atualizarCategoria(id, categoria);
        return ResponseEntity.ok()
                .body(categoriaAtualizada);
    }
}
