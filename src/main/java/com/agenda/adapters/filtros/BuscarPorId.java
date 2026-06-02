package com.agenda.adapters.filtros;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agenda.adapters.repository.ContatoRepository;
import com.agenda.adapters.repository.converters.ContatoEntityConverter;
import com.agenda.core.domain.Contato;
import com.agenda.core.ports.IPesquisarContatoStrategy;

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

        achados.add(ContatoEntityConverter.toDomain(contato.get()));

        return achados;
    }

}
