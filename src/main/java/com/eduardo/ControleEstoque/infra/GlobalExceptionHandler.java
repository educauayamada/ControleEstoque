package com.eduardo.ControleEstoque.infra;

import com.eduardo.ControleEstoque.DTO.ErroResponse;
import com.eduardo.ControleEstoque.Exception.CategoriaNotFoundException;
import com.eduardo.ControleEstoque.Exception.EstoqueInsuficienteException;
import com.eduardo.ControleEstoque.Exception.ProdutoNotFoundException;
import com.eduardo.ControleEstoque.Exception.QuantidadeInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(CategoriaNotFoundException.class)
    private ResponseEntity<ErroResponse> categoriaNotFoundException(CategoriaNotFoundException exception){

        return criarErro(HttpStatus.NOT_FOUND, exception.getMessage());

    }

    @ExceptionHandler(ProdutoNotFoundException.class)
    private ResponseEntity<ErroResponse> produtoNotFoundException(ProdutoNotFoundException exception) {

        return criarErro(HttpStatus.NOT_FOUND, exception.getMessage());

    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    private ResponseEntity<ErroResponse> estoqueInsuficienteException(EstoqueInsuficienteException exception) {

        return criarErro(HttpStatus.BAD_REQUEST, exception.getMessage());

    }

    @ExceptionHandler(QuantidadeInvalidaException.class)
    private ResponseEntity<ErroResponse> quantidadeInvalidaException(QuantidadeInvalidaException exception){

        return criarErro(HttpStatus.BAD_REQUEST, exception.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErroResponse> handleValidationException(MethodArgumentNotValidException exception){

        List<String> mensagens = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> f.getDefaultMessage())
                .toList();

        return criarErro(HttpStatus.BAD_REQUEST, mensagens);

    }

    private ResponseEntity<ErroResponse> criarErro(HttpStatus status, String mensagem) {

        ErroResponse erroResponse = new ErroResponse(
                status.value(),
                List.of(mensagem)
        );

        return ResponseEntity.status(status)
                .body(erroResponse);

    }

    private ResponseEntity<ErroResponse> criarErro(HttpStatus status, List<String> mensagens) {

        ErroResponse erroResponse = new ErroResponse(
                status.value(),
                mensagens
        );

        return ResponseEntity.status(status)
                .body(erroResponse);

    }
}
