package com.agenda.filtros;
import java.util.List;

import com.agenda.domain.Contato;
import com.agenda.domain.TipoContato;
import com.agenda.repository.ContatoRepository;

public class BuscarPorTipo implements IPesquisarContatoStrategy {

    @Override
    public List<Contato> executar(ContatoRepository repository, String value) {
        var tipo = TipoContato.valueOf(value);

        return repository.findByTipo(tipo);
    }
}
