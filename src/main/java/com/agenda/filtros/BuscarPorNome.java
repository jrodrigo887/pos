package com.agenda.filtros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agenda.domain.Contato;
import com.agenda.repository.ContatoRepository;

@Component("Nome")
public class BuscarPorNome implements IPesquisarContatoStrategy {
    @Autowired
    ContatoRepository repos;

    @Override
    public List<Contato> executar(String value) {
        return repos.findByNome(value.toLowerCase());
    }
}
