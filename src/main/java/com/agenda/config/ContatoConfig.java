package com.agenda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.agenda.adapters.out.persistence.ContatoRepositoryAdapter;
import com.agenda.adapters.out.persistence.ContatoRepositoryJpa;
import com.agenda.core.ports.out.ContatoRepositoryPort;
import com.agenda.core.ports.out.PesquisarContatoSelectorPort;
import com.agenda.core.usecases.ContatoUseCaseFacade;
import com.agenda.core.usecases.ContatoUseCases;

@Configuration
public class ContatoConfig {

    @Bean
    ContatoRepositoryPort contatoRepositoryService(ContatoRepositoryJpa repository) {
        return new ContatoRepositoryAdapter(repository);
    }

    @Bean
    ContatoUseCases contatoUseCase(ContatoRepositoryPort repositoryPort,
            PesquisarContatoSelectorPort selectorPort) {
        return ContatoUseCaseFacade.create(repositoryPort, selectorPort);
    }
}
