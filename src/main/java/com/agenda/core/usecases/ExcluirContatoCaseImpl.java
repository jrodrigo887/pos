package com.agenda.core.usecases;

import com.agenda.core.ports.in.ExcluirContatoCase;
import com.agenda.core.ports.out.ContatoRepositoryPort;

public class ExcluirContatoCaseImpl implements ExcluirContatoCase {

    private final ContatoRepositoryPort repository;

    public ExcluirContatoCaseImpl(ContatoRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Long id) {
        this.repository.exlcuirContato(id);
    }

}
