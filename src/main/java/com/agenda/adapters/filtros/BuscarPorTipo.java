package com.agenda.adapters.filtros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agenda.adapters.repository.ContatoRepository;
import com.agenda.core.domain.Contato;
import com.agenda.core.domain.TipoContato;
import com.agenda.core.ports.ContatoRepositoryService;
import com.agenda.core.ports.IPesquisarContatoStrategy;

@Component("tipo")
public class BuscarPorTipo implements IPesquisarContatoStrategy {
    @Autowired
    ContatoRepositoryService repos;

    @Override
    public List<Contato> executar(String value) {
        var tipo = TipoContato.valueOf(value);

        return repos.findByTipo(tipo);
    }
}
