package com.agenda.filtros;
import java.util.List;

import com.agenda.domain.Contato;
import com.agenda.repository.ContatoRepository;

public class BuscarPorNome implements IPesquisarContatoStrategy {
    @Override
    public List<Contato> executar(ContatoRepository repository, String value) {
        return repository.findByNome(value.toLowerCase());
    }
}
