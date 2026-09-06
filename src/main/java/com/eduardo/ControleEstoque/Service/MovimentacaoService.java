package com.eduardo.ControleEstoque.Service;

import com.eduardo.ControleEstoque.DTO.MovimentacaoCreateDTO;
import com.eduardo.ControleEstoque.DTO.MovimentacaoDTO;
import com.eduardo.ControleEstoque.Exception.EstoqueInsuficienteException;
import com.eduardo.ControleEstoque.Exception.ProdutoNotFoundException;
import com.eduardo.ControleEstoque.Model.Movimentacao;
import com.eduardo.ControleEstoque.Model.Produto;
import com.eduardo.ControleEstoque.Model.TipoMovimentacao;
import com.eduardo.ControleEstoque.Repository.MovimentacaoRepository;
import com.eduardo.ControleEstoque.Repository.ProdutoRepository;
import com.eduardo.ControleEstoque.infra.mapper.MovimentacaoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MovimentacaoService {

    private final ProdutoRepository produtoRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final MovimentacaoMapper movimentacaoMapper;

    public MovimentacaoService(ProdutoRepository produtoRepository, MovimentacaoRepository movimentacaoRepository, MovimentacaoMapper movimentacaoMapper) {
        this.produtoRepository = produtoRepository;
        this.movimentacaoRepository = movimentacaoRepository;
        this.movimentacaoMapper = movimentacaoMapper;
    }

    @Transactional
    public MovimentacaoDTO registrarMovimentacao(MovimentacaoCreateDTO movimentacaoCreateDTO) {

        Produto produto = produtoRepository.findById(movimentacaoCreateDTO.produtoId())
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado."));

        if (movimentacaoCreateDTO.tipoMovimentacao() == TipoMovimentacao.ENTRADA) {
            produto.setQuantidade(
                    produto.getQuantidade() + movimentacaoCreateDTO.quantidade());

        } else if (movimentacaoCreateDTO.tipoMovimentacao() == TipoMovimentacao.SAIDA) {

            if (produto.getQuantidade() < movimentacaoCreateDTO.quantidade()) {
                throw new EstoqueInsuficienteException("Estoque Insuficiente.");
            }

            produto.setQuantidade(
                        produto.getQuantidade() - movimentacaoCreateDTO.quantidade()
                );
        }

        produtoRepository.save(produto);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setProduto(produto);
        movimentacao.setQuantidade(movimentacaoCreateDTO.quantidade());
        movimentacao.setTipoMovimentacao(movimentacaoCreateDTO.tipoMovimentacao());
        movimentacao.setDataHora(LocalDateTime.now());

        movimentacaoRepository.save(movimentacao);

        return movimentacaoMapper.toDTO(movimentacao);
    }

    public List<MovimentacaoDTO> listarMovimentacoes() {

        return movimentacaoRepository.findAll()
                .stream()
                .map(movimentacaoMapper::toDTO)
                .toList();
    }

    public List<MovimentacaoDTO> listarMovimentacaoPorProduto(Long produtoId) {

        produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNotFoundException("Produto não encontrado."));

        return movimentacaoRepository.findByProduto_IdOrderByDataHoraDesc(produtoId)
                .stream()
                .map(movimentacaoMapper::toDTO)
                .toList();
    }
}
