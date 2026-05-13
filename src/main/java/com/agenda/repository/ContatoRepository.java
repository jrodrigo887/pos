package com.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenda.domain.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
