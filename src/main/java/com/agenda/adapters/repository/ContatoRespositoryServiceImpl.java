package com.agenda.adapters.repository;

import java.util.Collection;
import java.util.List;

import com.agenda.adapters.repository.converters.ContatoEntityConverter;
import com.agenda.core.domain.Contato;
import com.agenda.core.domain.TipoContato;
import com.agenda.core.ports.ContatoRepositoryService;

public class ContatoRespositoryServiceImpl implements ContatoRepositoryService {

    private final ContatoRepository repository;

    public ContatoRespositoryServiceImpl(ContatoRepository repos) {
        repository = repos;
    }

    @Override
    public Collection<Contato> getAllContatos() {
        return repository.findAll().stream()
                .map(ContatoEntityConverter::toDomain)
                .toList();
    }

    @Override
    public Contato saveContato(Contato contato) {
        var cto = repository.save(ContatoEntityConverter.toEntity(contato));

        return ContatoEntityConverter.toDomain(cto);
    }

    @Override
    public Boolean existsContatoEmail(String email) {
        return !repository.findByEmail(email).isEmpty();
    }

    @Override
    public List<Contato> findByEmaiList(String email) {
        var list = repository.findByEmail(email);

        return list.stream()
                .map(ContatoEntityConverter::toDomain)
                .toList();
    }

    @Override
    public List<Contato> findByNome(String nome) {
        var list = repository.findByNome(nome);

        return list.stream()
                .map(ContatoEntityConverter::toDomain)
                .toList();
    }

    @Override
    public List<Contato> findByTelefone(String telefone) {
        var list = repository.findByTelefone(telefone);

        return list.stream()
                .map(ContatoEntityConverter::toDomain)
                .toList();
    }

    @Override
    public List<Contato> findByTipo(TipoContato tipo) {
        var list = repository.findByTipo(tipo);

        return list.stream()
                .map(ContatoEntityConverter::toDomain)
                .toList();
    }

}
