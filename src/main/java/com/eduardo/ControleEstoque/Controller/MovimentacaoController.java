package com.eduardo.ControleEstoque.Controller;

import com.eduardo.ControleEstoque.DTO.MovimentacaoCreateDTO;
import com.eduardo.ControleEstoque.DTO.MovimentacaoDTO;
import com.eduardo.ControleEstoque.Service.MovimentacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @PostMapping
    public ResponseEntity<MovimentacaoDTO> registrarMovimentacao(@RequestBody MovimentacaoCreateDTO movimentacaoCreateDTO) {

        MovimentacaoDTO movimentacao = movimentacaoService.registrarMovimentacao(movimentacaoCreateDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movimentacao);
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoDTO>>listarMovimentacoes(){

        List<MovimentacaoDTO> listaMovimentacoes = movimentacaoService.listarMovimentacoes();

        return ResponseEntity.ok()
                .body(listaMovimentacoes);
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<MovimentacaoDTO>>listarMovimentacaoPorProduto(@PathVariable Long produtoId){

        List<MovimentacaoDTO> listaMovimentacaoPorProduto = movimentacaoService.listarMovimentacaoPorProduto(produtoId);

        return ResponseEntity.ok()
                .body(listaMovimentacaoPorProduto);

    }

}
