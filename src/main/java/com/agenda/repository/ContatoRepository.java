package com.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.domain.Contato;
import com.agenda.domain.TipoContato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    List<Contato> findByNome(String nome);

    List<Contato> findByEmail(String email);

    List<Contato> findByTelefone(String telefone);  

    List<Contato> findByTipo(TipoContato tipo);
}
