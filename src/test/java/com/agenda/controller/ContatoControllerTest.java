package com.agenda.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.agenda.domain.Status;
import com.agenda.domain.TipoContato;
import com.agenda.dtos.AtualizarContatoRequest;
import com.agenda.dtos.ContatoResponse;
import com.agenda.dtos.CriarContatoRequest;
import com.agenda.exceptions.ContatoNaoEncontradoException;
import com.agenda.exceptions.RegraDeNegocioException;
import com.agenda.service.ContatoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ContatoController.class)
public class ContatoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ContatoService service;

    @Autowired
    ObjectMapper objectMapper;

    private ContatoResponse contatoPadrao() {
        return new ContatoResponse(1L, "Joao Silva", "99-99999-999", "joao@email.com",
                "", 20, TipoContato.AMIGO, Status.ATIVO, LocalDateTime.of(2024, 1, 1, 10, 0), true);
    }

    // POST
    @Test
    void deveRetornar201AoCriarContato() throws Exception {
        var request = new CriarContatoRequest("Joao Silva", "99-99999-999", "joao@email.com",
                "", 20, TipoContato.AMIGO, Status.ATIVO);
        when(service.incluir(any())).thenReturn(contatoPadrao());

        mockMvc.perform(post("/contatos/incluir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Joao Silva"))
                .andExpect(jsonPath("$.email").value("joao@email.com"));
    }

    @Test
    void deveRetornar400ParaNomeEmBranco() throws Exception {
        var request = new CriarContatoRequest("", "99-99999-999", "joao@email.com",
                "", 20, TipoContato.AMIGO, Status.ATIVO);

        mockMvc.perform(post("/contatos/incluir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.nome").exists());
    }

    @Test
    void deveRetornar400ParaEmailInvalido() throws Exception {
        var request = new CriarContatoRequest("Joao Silva", "99-99999-999",
                "nao-e-email",
                "", 20, TipoContato.AMIGO, Status.ATIVO);

        mockMvc.perform(post("/contatos/incluir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.email").exists());
    }

    @Test
    void deveRetornar422ParaEmailJaCadastrado() throws Exception {
        var request = new CriarContatoRequest("Joao Silva", "99-99999-999",
                "joao@email.com",
                "", 20, TipoContato.AMIGO, Status.ATIVO);
        when(service.incluir(any()))
                .thenThrow(new RegraDeNegocioException("Já existe um contato com esse e-mail."));

        mockMvc.perform(post("/contatos/incluir")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("Já existe um contato com esse e-mail."));
    }

    // GET

    @Test
    void deveRetornar200ComListaDeContatos() throws Exception {
        when(service.listar()).thenReturn(List.of(contatoPadrao()));

        mockMvc.perform(get("/contatos/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].nome").value("Joao Silva"));
    }

    @Test
    void deveRetornar200ComListaVazia() throws Exception {
        when(service.listar()).thenReturn(List.of());

        mockMvc.perform(get("/contatos/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    // GET/PESQUISAR

    @Test
    void deveRetornar200ComResultadoDaPesquisa() throws Exception {
        when(service.pesquisar("nome", "Joao")).thenReturn(List.of(contatoPadrao()));

        mockMvc.perform(get("/contatos/pesquisar")
                .param("tipoBusca", "nome")
                .param("valor", "Joao"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Joao Silva"));
    }

    @Test
    void deveRetornar400ParaTipoBuscaInvalido() throws Exception {
        when(service.pesquisar(eq("invalido"), any()))
                .thenThrow(new IllegalArgumentException(
                        "Tipo de busca inválido. Use: nome, email, telefone, tipo ou id."));

        mockMvc.perform(get("/contatos/pesquisar")
                .param("tipoBusca", "invalido")
                .param("valor", "Joao"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
    }

    // PUT/EDITAR

    @Test
    void deveRetornar200AoEditarContato() throws Exception {
        var request = new AtualizarContatoRequest("Joao Editado", null, null, null,
                null, null, null);
        var responseAtualizado = new ContatoResponse(1L, "Joao Editado",
                "99-99999-999", "joao@email.com",
                "", 20, TipoContato.AMIGO, Status.ATIVO, LocalDateTime.of(2024, 1, 1, 10, 0),
                true);
        when(service.editar(eq(1L), any())).thenReturn(responseAtualizado);

        mockMvc.perform(put("/contatos/editar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Joao Editado"));
    }

    @Test
    void deveRetornar400ParaNomeComMenosDe3Caracteres() throws Exception {
        var request = new AtualizarContatoRequest("Jo", null, null, null, null, null,
                null);

        mockMvc.perform(put("/contatos/editar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.nome").exists());
    }

    @Test
    void deveRetornar404AoEditarContatoInexistente() throws Exception {
        var request = new AtualizarContatoRequest("Joao", null, null, null, null,
                null, null);
        when(service.editar(eq(99L), any()))
                .thenThrow(new ContatoNaoEncontradoException("Contato não encontrado."));

        mockMvc.perform(put("/contatos/editar/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value("Contato não encontrado."));
    }

    // DELETAR

    @Test
    void deveRetornar204AoExcluirContato() throws Exception {
        doNothing().when(service).excluir(1L);

        mockMvc.perform(delete("/contatos/excluir/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornar422AoExcluirContatoFamilia() throws Exception {
        doThrow(new RegraDeNegocioException("Contatos do tipo FAMILIA não podem ser excluídos."))
                .when(service).excluir(1L);

        mockMvc.perform(delete("/contatos/excluir/1"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message")
                        .value("Contatos do tipo FAMILIA não podem ser excluídos."));
    }

    @Test
    void deveRetornar404AoExcluirContatoInexistente() throws Exception {
        doThrow(new ContatoNaoEncontradoException("Contato não encontrado."))
                .when(service).excluir(99L);

        mockMvc.perform(delete("/contatos/excluir/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Contato não encontrado."));
    }
}
