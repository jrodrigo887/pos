package com.agenda.core.ports.in;

import com.agenda.core.domain.Contato;
import com.agenda.core.exceptions.RegraDeNegocioException;

public interface CriarContatoCase {
    Contato execute(Contato contato) throws RegraDeNegocioException;
}
