package com.eduardo.ControleEstoque.Service;

import com.eduardo.ControleEstoque.DTO.CategoriaDTO;
import com.eduardo.ControleEstoque.Exception.CategoriaNotFoundException;
import com.eduardo.ControleEstoque.Model.Categoria;
import com.eduardo.ControleEstoque.Repository.CategoriaRepository;
import com.eduardo.ControleEstoque.infra.mapper.CategoriaMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public CategoriaDTO cadastrarCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();

        categoria.setNome(categoriaDTO.nome());
        Categoria categoriaSalva = categoriaRepository.save(categoria);

        return categoriaMapper.toDTO(categoriaSalva);
     }

    public List<CategoriaDTO> listarCategorias(){
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDTO)
                .toList();
    }

    public CategoriaDTO listarCategoriaPorId(Long id) {

        return categoriaRepository.findById(id)
                .map(categoriaMapper::toDTO)
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

            return categoriaMapper.toDTO(categoriaAtualizada);

    }
}
