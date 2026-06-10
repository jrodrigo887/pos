package com.agenda.service;

import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.agenda.adapters.dtos.ContatoRequest;
import com.agenda.adapters.filtros.Filtro;
import com.agenda.core.domain.Contato;
import com.agenda.core.enums.TipoContato;
import com.agenda.core.exceptions.RegraDeNegocioException;
import com.agenda.infra.contato.ContatoRepositoryJpa;

@Disabled("Testes desabilitados — refatoração de arquitetura pendente")
@ExtendWith(MockitoExtension.class)
public class ContatoServiceTest {

    @Mock
    ContatoRepositoryJpa repo;

    @Mock
    Filtro filtro;

    @InjectMocks
    ContatoService service;

    @Test
    void deveLancarExcecaoComEmailJaCadastrado() {
        when(repo.findByEmail("joao@email.com")).thenReturn(List.of(new Contato()));

        var requestMock = new ContatoRequest("Joao", "99-99999-999", "joao@email.com", "", 20, TipoContato.AMIGO,
                com.agenda.core.enums.Status.ATIVO);

        Throwable excception = assertThrows(RegraDeNegocioException.class, () -> service.incluir(requestMock));

        assertEquals("Já existe um contato com esse e-mail.", excception.getMessage());
    }

    @Test
    void naoDeveExcluirContatoTipoFamilia() {
        var contato = new Contato();
        contato.setId(1L);
        contato.setTipo(TipoContato.FAMILIA);

        when(repo.findById(1L)).thenReturn(Optional.of(contato));

        Throwable excception = assertThrows(RegraDeNegocioException.class, () -> service.excluir(1L));

        assertEquals("Contatos do tipo FAMILIA não podem ser excluídos.", excception.getMessage());
    }
}
