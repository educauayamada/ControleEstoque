package com.eduardo.ControleEstoque.Exception;

public class CategoriaNotFoundException extends RuntimeException {

    public CategoriaNotFoundException (String mensagem) {
        super(mensagem);
    }
}
