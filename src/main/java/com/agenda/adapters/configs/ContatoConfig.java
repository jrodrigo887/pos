package com.agenda.adapters.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.agenda.adapters.repository.ContatoRepository;
import com.agenda.adapters.repository.ContatoRespositoryServiceImpl;
import com.agenda.core.ports.ContatoRepositoryService;
import com.agenda.core.usecases.contatos.CriarContatoCase;
import com.agenda.core.usecases.contatos.CriarContatoUsecaseInterface;

@Configuration
public class ContatoConfig {

    @Bean
    public ContatoRepositoryService contatoRepositoryService(ContatoRepository repository) {
        return new ContatoRespositoryServiceImpl(repository);
    }

    @Bean
    public CriarContatoUsecaseInterface criarContatoUseCase(ContatoRepositoryService repositoryService) {
        return new CriarContatoCase(repositoryService);
    }
}
