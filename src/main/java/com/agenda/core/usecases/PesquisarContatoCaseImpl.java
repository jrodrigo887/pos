package com.agenda.core.usecases;

import java.util.List;

import com.agenda.core.domain.Contato;
import com.agenda.core.ports.in.PesquisarContatoCase;
import com.agenda.core.ports.out.PesquisarContatoSelectorPort;

public class PesquisarContatoCaseImpl implements PesquisarContatoCase {

    private final PesquisarContatoSelectorPort seletor;

    PesquisarContatoCaseImpl(PesquisarContatoSelectorPort seletorFiltro) {
        this.seletor = seletorFiltro;
    }

    @Override
    public List<Contato> execute(String chave, String valor) {
        return seletor
                .selecionar(chave)
                .executar(valor);
    }
}
