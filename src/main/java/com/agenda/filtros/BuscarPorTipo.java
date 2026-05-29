package com.agenda.filtros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agenda.domain.Contato;
import com.agenda.domain.TipoContato;
import com.agenda.repository.ContatoRepository;

@Component("Tipo")
public class BuscarPorTipo implements IPesquisarContatoStrategy {
    @Autowired
    ContatoRepository repos;

    @Override
    public List<Contato> executar(String value) {
        var tipo = TipoContato.valueOf(value);

        return repos.findByTipo(tipo);
    }
}
