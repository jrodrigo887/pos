package com.agenda.core.ports.in;

import java.util.List;

import com.agenda.core.domain.Contato;

public interface PesquisarContatoCase {
    List<Contato> execute(String chave, String valor);
}
