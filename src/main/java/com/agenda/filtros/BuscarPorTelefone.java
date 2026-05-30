package com.agenda.filtros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agenda.domain.Contato;
import com.agenda.repository.ContatoRepository;

@Component("telefone")
public class BuscarPorTelefone implements IPesquisarContatoStrategy {

    @Autowired
    ContatoRepository repos;

    @Override
    public List<Contato> executar(String value) {
        return repos.findByTelefone(value.toLowerCase());
    }
}
