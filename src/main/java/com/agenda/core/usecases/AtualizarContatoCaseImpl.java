package com.agenda.core.usecases;

import com.agenda.core.domain.Contato;
import com.agenda.core.exceptions.ContatoNaoEncontradoException;
import com.agenda.core.ports.in.AtualizarContatoCase;
import com.agenda.core.ports.out.ContatoRepositoryPort;

public class AtualizarContatoCaseImpl implements AtualizarContatoCase {

    private final ContatoRepositoryPort repository;

    public AtualizarContatoCaseImpl(ContatoRepositoryPort repo) {
        this.repository = repo;
    }

    @Override
    public Contato execute(Long id, Contato contato) {
        Contato cto = findById(id);

        if (contato.getNome() != null)
            cto.setNome(contato.getNome());
        if (contato.getTelefone() != null)
            cto.setTelefone(contato.getTelefone());
        if (contato.getEmail() != null)
            cto.setEmail(contato.getEmail());
        if (contato.getEndereco() != null)
            cto.setEndereco(contato.getEndereco());
        if (contato.getIdade() != null)
            cto.setIdade(contato.getIdade());
        if (contato.getTipo() != null)
            cto.setTipo(contato.getTipo());
        if (contato.getStatus() != null)
            cto.setStatus(contato.getStatus());

        return repository.atualizarContato(cto);
    }

    private Contato findById(Long id) {
        return repository.contatoPorId(id)
                .orElseThrow(() -> new ContatoNaoEncontradoException("Contato não encontrado."));
    }

}
