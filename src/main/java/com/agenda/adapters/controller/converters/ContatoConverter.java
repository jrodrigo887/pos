package com.agenda.adapters.controller.converters;

import com.agenda.adapters.dtos.ContatoRequest;
import com.agenda.adapters.dtos.ContatoResponse;
import com.agenda.core.domain.Contato;

public class ContatoConverter {

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

}
