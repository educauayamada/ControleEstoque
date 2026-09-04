package com.eduardo.ControleEstoque.DTO;

import java.util.List;

public record ErroResponse(

        int status,
        List<String> mensagens

) {
}
