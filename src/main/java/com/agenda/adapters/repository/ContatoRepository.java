package com.agenda.adapters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agenda.core.domain.TipoContato;
import com.agenda.infra.contato.ContatoEntity;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {

    List<ContatoEntity> findByNome(String nome);

    List<ContatoEntity> findByEmail(String email);

    List<ContatoEntity> findByTelefone(String telefone);

    List<ContatoEntity> findByTipo(TipoContato tipo);
}
