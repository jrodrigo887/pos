package com.agenda.core.usecases.contatos;

import com.agenda.core.domain.Contato;
import com.agenda.core.exceptions.RegraDeNegocioException;

public interface CriarContatoUsecaseInterface {
    public Contato execute(Contato contato) throws RegraDeNegocioException;
}
