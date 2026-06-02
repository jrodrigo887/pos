package com.agenda.adapters.dtos;

import com.agenda.core.domain.Status;
import com.agenda.core.domain.TipoContato;

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
}
