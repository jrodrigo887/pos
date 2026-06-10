package com.agenda.core.usecases;

import com.agenda.core.domain.Contato;
import com.agenda.core.exceptions.RegraDeNegocioException;
import com.agenda.core.ports.in.CriarContatoCase;
import com.agenda.core.ports.out.ContatoRepositoryPort;

public class CriarContatoCaseImpl implements CriarContatoCase {

    private final ContatoRepositoryPort repository;

    public CriarContatoCaseImpl(ContatoRepositoryPort repo) {
        repository = repo;
    }

    @Override
    public Contato execute(Contato contato) {
        validarEmailUnico(contato.getEmail());
        return repository.saveContato(contato);
    }

    private void validarEmailUnico(String email) {
        if (repository.existsContatoEmail(email)) {
            throw new RegraDeNegocioException("Email já cadastrado.");
        }
    }

}
