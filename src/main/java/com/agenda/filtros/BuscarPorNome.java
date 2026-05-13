package com.agenda.filtros;

import java.util.ArrayList;
import java.util.List;

import com.agenda.Contato;

public class BuscarPorNome implements IPesquisarContatoStrategy {

    @Override
    public List<Contato> executar(List<Contato> contatos, String value) {
        
        List<Contato> achados = new ArrayList<>();
        for (Contato c : contatos) {
            if (c.getNome() != null & c.getNome().toLowerCase().contains(value.toLowerCase()))
                achados.add(c);
        }
        return achados;
    }
    
}
