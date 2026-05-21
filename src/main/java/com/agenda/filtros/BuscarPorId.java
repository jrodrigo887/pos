package com.agenda.filtros;

import java.util.ArrayList;

import java.util.List;

import com.agenda.domain.Contato;
import com.agenda.repository.ContatoRepository;

public class BuscarPorId implements IPesquisarContatoStrategy {

    @Override
    public List<Contato> executar(ContatoRepository repository, String value) {
        List<Contato> achados = new ArrayList<>();
        Long id = Long.parseLong(value);

        var contato = repository.findById(id);

        if (!contato.isPresent())
            return achados;

        achados.add(contato.get());

        return achados;
    }

}
