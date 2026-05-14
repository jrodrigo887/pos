package com.agenda.filtros;

import java.util.ArrayList;
import java.util.List;

import com.agenda.domain.Contato;
import com.agenda.domain.TipoContato;

public class BuscarPorTipo implements IPesquisarContatoStrategy {

    @Override
    public List<Contato> executar(List<Contato> contatos, String value) {
        List<Contato> achados = new ArrayList<>();

        for (Contato contato : contatos) {
            if (contato != null
                && contato.getTipo().equals(TipoContato.valueOf(value)))
                achados.add(contato);
        }
        return achados;
    }

}
