package com.eduardo.ControleEstoque.Controller;

import com.eduardo.ControleEstoque.DTO.CategoriaDTO;
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
    public ResponseEntity<CategoriaDTO> cadastrarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO novaCategoria = categoriaService.cadastrarCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(novaCategoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        List<CategoriaDTO> listaCategorias = categoriaService.listarCategorias();
        return ResponseEntity.ok()
                .body(listaCategorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> listarCategoriaPorId(@PathVariable Long id){
        CategoriaDTO categoriaPorId = categoriaService.listarCategoriaPorId(id);
        return ResponseEntity.ok()
                .body(categoriaPorId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable Long id,
                                                        @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaAtualizada = categoriaService.atualizarCategoria(id, categoriaDTO);
        return ResponseEntity.ok()
                .body(categoriaAtualizada);
    }
}
