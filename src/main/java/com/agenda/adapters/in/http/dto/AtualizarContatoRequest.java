package com.agenda.adapters.in.http.dto;

import com.agenda.core.enums.Status;
import com.agenda.core.enums.TipoContato;

import jakarta.validation.constraints.*;

public record AtualizarContatoRequest(

        @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres.") String nome,

        String telefone,

        @Email(message = "Informe um e-mail válido.") String email,

        String endereco,

        @Min(value = 0, message = "Idade inválida.") @Max(value = 150, message = "Idade inválida.") Integer idade,

        TipoContato tipo,

        Status status

) {
    public AtualizarContatoRequest {
        nome = (nome != null && !nome.isBlank()) ? nome.strip() : null;
        telefone = (telefone != null && !telefone.isBlank()) ? telefone.strip() : null;
        email = (email != null && !email.isBlank()) ? email.strip() : null;
        endereco = (endereco != null && !endereco.isBlank()) ? endereco.strip() : null;
    }
}
