package com.agenda.core.usecases;

import com.agenda.core.ports.out.ContatoRepositoryPort;
import com.agenda.core.ports.out.PesquisarContatoSelectorPort;

public class ContatoUseCaseFacade {
    public static ContatoUseCases create(ContatoRepositoryPort repository, PesquisarContatoSelectorPort seletor) {
        return new ContatoUseCases(
                new CriarContatoCaseImpl(repository),
                new ListarContatoCaseImpl(repository),
                new PesquisarContatoCaseImpl(seletor),
                new AtualizarContatoCaseImpl(repository),
                new ExcluirContatoCaseImpl(repository));
    }
}
