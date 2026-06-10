package com.agenda.core.ports.in;

import com.agenda.core.domain.Contato;

public interface AtualizarContatoCase {
    Contato execute(Long id, Contato contato);
}
