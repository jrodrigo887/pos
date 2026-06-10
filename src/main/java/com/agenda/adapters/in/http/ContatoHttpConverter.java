package com.agenda.adapters.in.http;

import com.agenda.adapters.in.http.dto.AtualizarContatoRequest;
import com.agenda.adapters.in.http.dto.ContatoRequest;
import com.agenda.adapters.in.http.dto.ContatoResponse;
import com.agenda.core.domain.Contato;

public class ContatoHttpConverter {

    public static Contato toDomain(ContatoRequest entity) {
        Contato contato = new Contato();

        contato.setNome(entity.nome());
        contato.setEmail(entity.email());
        contato.setIdade(entity.idade());
        contato.setEndereco(entity.endereco());
        contato.setTelefone(entity.telefone());
        contato.setAtivo(true);

        return contato;
    }

    public static ContatoResponse toResponse(Contato domain) {
        return new ContatoResponse(
                domain.getId(),
                domain.getNome(),
                domain.getTelefone(),
                domain.getEmail(),
                domain.getEndereco(),
                domain.getIdade(),
                domain.getTipo(),
                domain.getStatus(),
                domain.getDataCad(),
                domain.isAtivo());
    }

    public static Contato applyUpdate(AtualizarContatoRequest request) {
        Contato cto = new Contato();

        if (request.nome() != null)
            cto.setNome(request.nome());
        if (request.telefone() != null)
            cto.setTelefone(request.telefone());
        if (request.email() != null)
            cto.setEmail(request.email());
        if (request.endereco() != null)
            cto.setEndereco(request.endereco());
        if (request.idade() != null)
            cto.setIdade(request.idade());
        if (request.tipo() != null)
            cto.setTipo(request.tipo());
        if (request.status() != null)
            cto.setStatus(request.status());

        return cto;
    }
}
