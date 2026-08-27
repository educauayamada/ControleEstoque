package com.eduardo.ControleEstoque.Service;

import com.eduardo.ControleEstoque.Exception.CategoriaNotFoundException;
import com.eduardo.ControleEstoque.Model.Categoria;
import com.eduardo.ControleEstoque.Repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria cadastrarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }

    public Categoria listarCategoriaPorId(Long id) {

        return categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada."));

    }

    public void deletarCategoria (Long id) {
        Categoria categoriaEntity = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada."));
        categoriaRepository.delete(categoriaEntity);
    }

    public Categoria atualizarCategoria(Long id, Categoria categoria) {
        Categoria categoriaEntity = categoriaRepository.findById(id)
            .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada."));
        categoriaEntity.setNome(categoria.getNome());
        return categoriaRepository.save(categoriaEntity);
    }
}
