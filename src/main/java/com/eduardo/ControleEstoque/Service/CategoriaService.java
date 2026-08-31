package com.eduardo.ControleEstoque.Service;

import com.eduardo.ControleEstoque.DTO.CategoriaDTO;
import com.eduardo.ControleEstoque.Exception.CategoriaNotFoundException;
import com.eduardo.ControleEstoque.Model.Categoria;
import com.eduardo.ControleEstoque.Repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaDTO cadastrarCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();

        categoria.setNome(categoriaDTO.nome());
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        return new CategoriaDTO (
                categoriaSalva.getId(),
                categoriaSalva.getNome()
        );


     }

    public List<CategoriaDTO> listarCategorias(){
        return categoriaRepository.findAll()
                .stream()
                .map(c -> new CategoriaDTO(
                        c.getId(),
                        c.getNome()
                ))
                .toList();
    }

    public CategoriaDTO listarCategoriaPorId(Long id) {

        return categoriaRepository.findById(id)
                .map(c -> new CategoriaDTO(
                        c.getId(),
                        c.getNome()
                ))
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada"));

    }

    public void deletarCategoria (Long id) {
        Categoria categoriaEntity = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada."));
        categoriaRepository.delete(categoriaEntity);
    }

    public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO categoriaDTO) {
            Categoria categoriaEntity = categoriaRepository.findById(id)
                    .orElseThrow(() -> new CategoriaNotFoundException("Categoria não encontrada."));

            categoriaEntity.setNome(categoriaDTO.nome());
            Categoria categoriaAtualizada = categoriaRepository.save(categoriaEntity);

            return new CategoriaDTO(
                    categoriaAtualizada.getId(),
                    categoriaAtualizada.getNome()
            );

    }
}
