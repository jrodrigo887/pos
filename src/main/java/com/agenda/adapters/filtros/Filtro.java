package com.agenda.adapters.filtros;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.agenda.core.ports.IPesquisarContatoStrategy;

@Component
public class Filtro {

    private final Map<String, IPesquisarContatoStrategy> estrategias;

    public Filtro(Map<String, IPesquisarContatoStrategy> estrategias) {
        this.estrategias = estrategias;
    }

    public IPesquisarContatoStrategy chaveDoContato(String value) {
        IPesquisarContatoStrategy estrategia = estrategias.get(value);

        if (estrategia == null) {
            throw new IllegalArgumentException("Erro: tipo de busca inválido. Use: nome, email, telefone, tipo ou id.");
        }

        return estrategia;
    }
}
