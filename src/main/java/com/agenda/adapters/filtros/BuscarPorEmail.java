package com.agenda.adapters.filtros;

import com.agenda.core.domain.Contato;
import com.agenda.core.ports.ContatoRepositoryService;
import com.agenda.core.ports.IPesquisarContatoStrategy;

import java.util.List;

import org.springframework.stereotype.Component;

@Component("email")
public class BuscarPorEmail implements IPesquisarContatoStrategy {
    private ContatoRepositoryService repos;

    public BuscarPorEmail(ContatoRepositoryService repository) {
        repos = repository;
    }

    @Override
    public List<Contato> executar(String value) {
        return repos.findByEmaiList(value);
    }
}
