package com.agenda.filtros;

import java.util.ArrayList;

import java.util.List;

import com.agenda.domain.Contato;

public class BuscarPorId implements IPesquisarContatoStrategy {

    @Override
    public List<Contato> executar(List<Contato> contatos, String value) {
        List<Contato> achados = new ArrayList<>();
        Long id = Long.parseLong(value);

        for (Contato contato : contatos) {
            if (contato != null 
                && contato.getId().equals(id))
                achados.add(contato);
        }
        return achados;
    }

}
