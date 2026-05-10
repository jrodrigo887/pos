package com.agenda.filtros;

import java.util.Map;

public class Filtro {
    public static IPesquisarContatoStrategy chaveDoContato(String value) {
        // pela chave busque a classe compartível
        IPesquisarContatoStrategy estrategia =  Map.of(
            "nome", new BuscarPorNome(),
            "email", new BuscarPorEmail(),
            "tel", new BuscarPorTelefone(),
            "telefone", new BuscarPorTelefone(),
            "tipo", new BuscarPorTipo(),
            "id", new BuscarPorId()
        ).get(value);

        if (estrategia == null) {
            throw new IllegalArgumentException("Erro: tipo de busca inválido. Use: nome, email, telefone, tipo ou id.");
        }

        return estrategia;
        
    }
}
