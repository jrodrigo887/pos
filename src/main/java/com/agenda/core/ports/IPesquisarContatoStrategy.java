package com.agenda.core.ports;

import java.util.List;

import com.agenda.core.domain.Contato;

public interface IPesquisarContatoStrategy {
    public List<Contato> executar(String value);
}
