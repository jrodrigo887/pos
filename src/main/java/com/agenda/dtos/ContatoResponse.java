package com.agenda.dtos;

import com.agenda.domain.Contato;
import com.agenda.domain.Status;
import com.agenda.domain.TipoContato;
import java.time.LocalDateTime;

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
        boolean ativo
) {
    public static ContatoResponse from(Contato contato) {
        return new ContatoResponse(
                contato.getId(),
                contato.getNome(),
                contato.getTelefone(),
                contato.getEmail(),
                contato.getEndereco(),
                contato.getIdade(),
                contato.getTipo(),
                contato.getStatus(),
                contato.getDataCad(),
                contato.isAtivo()
        );
    }
}
