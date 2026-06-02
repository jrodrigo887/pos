package com.agenda.adapters.dtos;

import java.time.LocalDateTime;

import com.agenda.core.domain.Status;
import com.agenda.core.domain.TipoContato;

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
