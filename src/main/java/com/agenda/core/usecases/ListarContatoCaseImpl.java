package com.agenda.core.usecases;

import java.util.List;

import com.agenda.core.domain.Contato;
import com.agenda.core.ports.in.ListarContatoCase;
import com.agenda.core.ports.out.ContatoRepositoryPort;

public class ListarContatoCaseImpl implements ListarContatoCase {

    private final ContatoRepositoryPort repositoryPort;

    public ListarContatoCaseImpl(ContatoRepositoryPort repository) {
        this.repositoryPort = repository;
    }

    @Override
    public List<Contato> execute() {
        return repositoryPort.getAllContatos().stream().toList();
    }
}
