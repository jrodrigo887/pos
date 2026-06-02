package com.agenda.adapters.repository.converters;

import com.agenda.core.domain.Contato;
import com.agenda.infra.contato.ContatoEntity;

public class ContatoEntityConverter {
    public static ContatoEntity toEntity(Contato domain) {
        return new ContatoEntity(
                domain.getId(),
                domain.getNome(),
                domain.getTelefone(),
                domain.getEmail(),
                domain.getEndereco(),
                domain.getIdade(),
                domain.getTipo(),
                domain.getDataCad(),
                domain.isAtivo());
    }

    public static Contato toDomain(ContatoEntity entity) {
        return new Contato(entity.getId(),
                entity.getNome(),
                entity.getTelefone(),
                entity.getEmail(),
                entity.getEndereco(),
                entity.getIdade(),
                entity.getTipo(),
                entity.getDataCad(),
                entity.isAtivo());
    }
}
