package com.agenda.filtros;
import com.agenda.domain.Contato;
import com.agenda.repository.ContatoRepository;

import java.util.List;

public class BuscarPorEmail implements IPesquisarContatoStrategy {

    @Override
    public List<Contato> executar(ContatoRepository repository, String value) {
        return repository.findByEmail(value.toLowerCase());
    }
}
