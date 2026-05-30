package com.agenda.filtros;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agenda.domain.Contato;
import com.agenda.repository.ContatoRepository;

@Component("id")
public class BuscarPorId implements IPesquisarContatoStrategy {

    @Autowired
    ContatoRepository repos;

    @Override
    public List<Contato> executar(String value) {
        List<Contato> achados = new ArrayList<>();
        Long id = Long.parseLong(value);

        var contato = repos.findById(id);

        if (!contato.isPresent())
            return achados;

        achados.add(contato.get());

        return achados;
    }

}
