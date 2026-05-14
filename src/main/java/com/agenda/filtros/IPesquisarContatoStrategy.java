package com.agenda.filtros;

import java.util.List;

import com.agenda.domain.Contato;

public interface IPesquisarContatoStrategy {
    public List<Contato> executar(List<Contato> contatos, String value);
}   
