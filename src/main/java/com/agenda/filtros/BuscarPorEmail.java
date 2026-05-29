package com.agenda.filtros;

import com.agenda.domain.Contato;
import com.agenda.repository.ContatoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("email")
public class BuscarPorEmail implements IPesquisarContatoStrategy {
    @Autowired
    ContatoRepository repos;

    @Override
    public List<Contato> executar(String value) {
        return repos.findByEmail(value.toLowerCase());
    }
}
