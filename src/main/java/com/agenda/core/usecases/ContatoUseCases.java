package com.agenda.core.usecases;

import com.agenda.core.ports.in.AtualizarContatoCase;
import com.agenda.core.ports.in.CriarContatoCase;
import com.agenda.core.ports.in.ExcluirContatoCase;
import com.agenda.core.ports.in.ListarContatoCase;
import com.agenda.core.ports.in.PesquisarContatoCase;

public record ContatoUseCases(
                CriarContatoCase criar,
                ListarContatoCase listar,
                PesquisarContatoCase pesquisar,
                AtualizarContatoCase atualizar,
                ExcluirContatoCase excluir) {
}
