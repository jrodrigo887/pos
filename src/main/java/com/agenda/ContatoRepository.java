package com.agenda;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findByNome(String nome);
    List<Contato> findByEmail(String email);
    List<Contato> findByTel(String tel);
    Optional<Contato> findById(Long id);
    List<Contato> findByTipo(String tipo);
}
