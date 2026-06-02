package com.agenda.core.usecases.contatos;

import com.agenda.core.domain.Contato;
import com.agenda.core.exceptions.RegraDeNegocioException;
import com.agenda.core.ports.ContatoRepositoryService;

public class CriarContatoCase implements CriarContatoUsecaseInterface {

    private final ContatoRepositoryService repository;

    public CriarContatoCase(ContatoRepositoryService repo) {
        repository = repo;
    }

    @Override
    public Contato execute(Contato contato) {
        var isEmpty = repository.existsContatoEmail(contato.getEmail());

        if (isEmpty) {
            throw new RegraDeNegocioException("Email já cadastrado.");
        }

        var result = repository.saveContato(contato);

        return result;
    }

}
