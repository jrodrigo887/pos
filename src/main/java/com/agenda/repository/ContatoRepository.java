package com.agenda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.domain.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findByNome(String nome);
    List<Contato> findByEmail(String email);
    List<Contato> findByTelefone(String telefone);
    Optional<Contato> findById(Long id);
    List<Contato> findByTipo(String tipo);
}
