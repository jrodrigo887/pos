package com.agenda.filtros;

import java.util.List;

import com.agenda.domain.Contato;
import com.agenda.repository.ContatoRepository;

public interface IPesquisarContatoStrategy {
    public List<Contato> executar(ContatoRepository repository, String value);
}   
