package com.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    List<Contato> findByNome(String nome);

    List<Contato> findByEmail(String email);

    List<Contato> findByTelefone(String telefone);
    Optional<Contato> findById(@NonNull Long id);
    List<Contato> findByTipo(String tipo);
}
