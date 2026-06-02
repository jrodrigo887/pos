package com.agenda.adapters.filtros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agenda.core.domain.Contato;
import com.agenda.core.ports.ContatoRepositoryService;
import com.agenda.core.ports.IPesquisarContatoStrategy;

@Component("nome")
public class BuscarPorNome implements IPesquisarContatoStrategy {
    @Autowired
    ContatoRepositoryService repos;

    @Override
    public List<Contato> executar(String value) {
        return repos.findByNome(value.toLowerCase());
    }
}
