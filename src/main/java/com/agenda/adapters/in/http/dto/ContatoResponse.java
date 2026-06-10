package com.agenda.adapters.in.http.dto;

import java.time.LocalDateTime;

import com.agenda.core.enums.Status;
import com.agenda.core.enums.TipoContato;

public record ContatoResponse(
                Long id,
                String nome,
                String telefone,
                String email,
                String endereco,
                Integer idade,
                TipoContato tipo,
                Status status,
                LocalDateTime dataCad,
                boolean ativo) {
}
