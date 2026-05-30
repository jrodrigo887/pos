package com.agenda.service;

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

import com.agenda.domain.Contato;
import com.agenda.domain.TipoContato;
import com.agenda.dtos.CriarContatoRequest;
import com.agenda.exceptions.RegraDeNegocioException;
import com.agenda.filtros.Filtro;
import com.agenda.repository.ContatoRepository;

@ExtendWith(MockitoExtension.class)
public class ContatoServiceTest {

    @Mock
    ContatoRepository repo;

    @Mock
    Filtro filtro;

    @InjectMocks
    ContatoService service;

    @Test
    void deveLancarExcecaoComEmailJaCadastrado() {
        when(repo.findByEmail("joao@email.com")).thenReturn(List.of(new Contato()));

        var requestMock = new CriarContatoRequest("Joao", "99-99999-999", "joao@email.com", "", 20, TipoContato.AMIGO,
                com.agenda.domain.Status.ATIVO);

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
