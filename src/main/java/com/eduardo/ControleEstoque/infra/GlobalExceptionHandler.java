package com.eduardo.ControleEstoque.infra;

import com.eduardo.ControleEstoque.Exception.CategoriaNotFoundException;
import com.eduardo.ControleEstoque.Exception.EstoqueInsuficienteException;
import com.eduardo.ControleEstoque.Exception.ProdutoNotFoundException;
import com.eduardo.ControleEstoque.Exception.QuantidadeInvalidaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CategoriaNotFoundException.class)
    private ResponseEntity<String> categoriaNotFoundException(CategoriaNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ProdutoNotFoundException.class)
    private ResponseEntity<String> produtoNotFoundException(ProdutoNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    private ResponseEntity<String> estoqueInsuficienteException(EstoqueInsuficienteException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(QuantidadeInvalidaException.class)
    private ResponseEntity<String> quantidadeInvalidaException(QuantidadeInvalidaException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}
