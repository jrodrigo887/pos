package com.agenda.service;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import com.agenda.domain.Contato;
import com.agenda.dto.ContatoRequest;
import com.agenda.dto.ContatoResponse;
import com.agenda.repository.ContatoRepository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;



@Service
public class ContatoService {
	
	private final ContatoRepository repo;
	
	private static List<String> logs = new ArrayList<>();

	private static Integer cont = 0;

		
	public ContatoService(ContatoRepository repo) {
		this.repo = repo;

	}

	public ContatoResponse incluir(ContatoRequest request) {
    try {
        if (request.getNome() == null || request.getNome().isBlank()) {
            throw new RuntimeException("erro: nome obrigatório");
        }

        if (request.getNome().length() < 3) {
            throw new RuntimeException("erro: nome muito curto");
        }

        if (request.getTelefone() == null || request.getTelefone().isBlank()) {
            throw new RuntimeException("erro: telefone obrigatório");
        }

        if (request.getEmail() == null || request.getEmail().isBlank()) {
            throw new RuntimeException("erro: email obrigatório");
        }

        if (!request.getEmail().contains("@") || !request.getEmail().contains(".")) {
            throw new RuntimeException("erro: email inválido");
        }

        if (request.getIdade() == null) {
            throw new RuntimeException("erro: idade obrigatória");
        }

        if (request.getIdade() < 0 || request.getIdade() > 150) {
            throw new RuntimeException("erro: idade inválida");
        }

        if (request.getStatus() == null) {
            throw new RuntimeException("erro: status obrigatório");
        }

        if (request.getTipo() == null) {
            throw new RuntimeException("erro: tipo inválido. Use: FAMILIA, AMIGO, TRABALHO ou OUTRO");
        }

        List<Contato> todos = repo.findAll();

        for (Contato contatoExistente : todos) {
            if (contatoExistente.getEmail() != null &&
                contatoExistente.getEmail().equals(request.getEmail())) {
                throw new RuntimeException("erro: já existe contato com esse email");
            }
        }

        Contato contato = new Contato();

        contato.setNome(request.getNome());
        contato.setTelefone(request.getTelefone());
        contato.setEmail(request.getEmail());
        contato.setEndereco(request.getEndereco());
        contato.setIdade(request.getIdade());
        contato.setTipo(request.getTipo());
        contato.setStatus(request.getStatus());
        contato.setDataCad(LocalDateTime.now());
        contato.setAtivo(true);

        Contato salvo = repo.save(contato);

        ContatoResponse response = new ContatoResponse();

        response.setId(salvo.getId());
        response.setNome(salvo.getNome());
        response.setTelefone(salvo.getTelefone());
        response.setEmail(salvo.getEmail());
        response.setEndereco(salvo.getEndereco());
        response.setIdade(salvo.getIdade());
        response.setTipo(salvo.getTipo());
        response.setStatus(salvo.getStatus());
        response.setAtivo(salvo.isAtivo());
        response.setDataCad(salvo.getDataCad());

        return response;

    } catch (Exception e) {
        throw new RuntimeException("erro interno: " + e.getMessage());
    }
}

	public List<ContatoResponse> listar() {

		List<Contato> contatos = repo.findAll();

		List<ContatoResponse> responses = new ArrayList<>();

		for (Contato contato : contatos){

			ContatoResponse response = new ContatoResponse();

			response.setId(contato.getId());
			response.setNome(contato.getNome());
			response.setTelefone(contato.getTelefone());
			response.setEmail(contato.getEmail());
			response.setEndereco(contato.getEndereco());
			response.setIdade(contato.getIdade());
			response.setTipo(contato.getTipo());
			response.setStatus(contato.getStatus());
			response.setAtivo(contato.isAtivo());
			response.setDataCad(contato.getDataCad());

        responses.add(response);
		}

		return responses;
	}
	
	public String excluir(@NonNull Long id) {
	if (!repo.existsById(id)) {
		return "erro: contato não encontrado";
	}

	repo.deleteById(id);

	return "contato excluído com sucesso";
	}
	
	public ContatoResponse editar(@NonNull Long id, ContatoRequest request) {

    Contato atual = repo.findById(id).orElse(null);

    if (atual == null) {
        throw new RuntimeException("erro: contato não encontrado");
    }

    if (request.getNome() != null && !request.getNome().isBlank()) {
        atual.setNome(request.getNome());
    }

    if (request.getTelefone() != null && !request.getTelefone().isBlank()) {
        atual.setTelefone(request.getTelefone());
    }

    if (request.getEmail() != null && !request.getEmail().isBlank()) {
        atual.setEmail(request.getEmail());
    }

    if (request.getTipo() != null) {
        atual.setTipo(request.getTipo());
    }

    if (request.getStatus() != null) {
        atual.setStatus(request.getStatus());
    }

    if (request.getIdade() != null) {
        atual.setIdade(request.getIdade());
    }

    if (request.getEndereco() != null && !request.getEndereco().isBlank()) {
        atual.setEndereco(request.getEndereco());
    }

    Contato salvo = repo.save(atual);

    ContatoResponse response = new ContatoResponse();

    response.setId(salvo.getId());
    response.setNome(salvo.getNome());
    response.setTelefone(salvo.getTelefone());
    response.setEmail(salvo.getEmail());
    response.setEndereco(salvo.getEndereco());
    response.setIdade(salvo.getIdade());
    response.setTipo(salvo.getTipo());
    response.setStatus(salvo.getStatus());
    response.setAtivo(salvo.isAtivo());
    response.setDataCad(salvo.getDataCad());

    return response;
}
	
	public List<ContatoResponse> pesquisar(String tipoBusca, String valor) {
		List<Contato> todos = repo.findAll();
		List<ContatoResponse> resultado = new ArrayList<>();
		
		for (Contato contato : todos) {
			boolean encontrou = false;
			
			if (tipoBusca.equalsIgnoreCase("nome")) {
            encontrou = contato.getNome() != null &&
                    contato.getNome().toLowerCase().contains(valor.toLowerCase());
        }

        if (tipoBusca.equalsIgnoreCase("telefone")) {
            encontrou = contato.getTelefone() != null &&
                    contato.getTelefone().toLowerCase().contains(valor.toLowerCase());
        }

        if (tipoBusca.equalsIgnoreCase("email")) {
            encontrou = contato.getEmail() != null &&
                    contato.getEmail().toLowerCase().contains(valor.toLowerCase());
        }

        if (tipoBusca.equalsIgnoreCase("tipo")) {
            encontrou = contato.getTipo() != null &&
                    contato.getTipo().name().equalsIgnoreCase(valor);
        }

        if (tipoBusca.equalsIgnoreCase("id")) {
            Long id = Long.parseLong(valor);

            encontrou = contato.getId() != null &&
                    contato.getId().equals(id);
        }

        if (encontrou) {
            ContatoResponse response = new ContatoResponse();

            response.setId(contato.getId());
            response.setNome(contato.getNome());
            response.setTelefone(contato.getTelefone());
            response.setEmail(contato.getEmail());
            response.setEndereco(contato.getEndereco());
            response.setIdade(contato.getIdade());
            response.setTipo(contato.getTipo());
            response.setStatus(contato.getStatus());
            response.setAtivo(contato.isAtivo());
            response.setDataCad(contato.getDataCad());

            resultado.add(response);
        }
    }

    return resultado;
}

	
	public String verLogs() {
		String s = "=== LOGS ===\n";
		s = s + "Total operacoes: " + cont + "\n\n";

	for (int i = 0; i < logs.size(); i++) {
		s = s + logs.get(i) + "\n";
	}

	return s;
	}

}



