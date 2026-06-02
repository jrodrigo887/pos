package com.agenda.core.ports;

import java.util.Collection;
import java.util.List;

import com.agenda.core.domain.Contato;
import com.agenda.core.domain.TipoContato;

public interface ContatoRepositoryService {
    public Collection<Contato> getAllContatos();

    public Contato saveContato(Contato contato);

    public Boolean existsContatoEmail(String email);

    public List<Contato> findByEmaiList(String email);

    public List<Contato> findByNome(String nome);

    public List<Contato> findByTelefone(String telefone);

    public List<Contato> findByTipo(TipoContato tipo);
}
