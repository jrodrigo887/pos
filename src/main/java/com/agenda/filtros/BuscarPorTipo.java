package com.agenda.filtros;

import java.util.ArrayList;
import java.util.List;

import com.agenda.Contato;

public class BuscarPorTipo implements IPesquisarContatoStrategy {

    @Override
    public List<Contato> executar(List<Contato> contatos, String value) {
        List<Contato> achados = new ArrayList<>();

        for (Contato contato : contatos) {
            if (contato != null
                && contato.getTipo().toLowerCase().equals(value.toLowerCase()))
                achados.add(contato);
        }
        return achados;
    }

}
