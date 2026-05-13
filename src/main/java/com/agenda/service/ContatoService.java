package com.agenda.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.agenda.domain.*;
import com.agenda.repository.*;



@Service
public class ContatoService {
	
	private final ContatoRepository repo;
	
	private static List<String> logs = new ArrayList<>();

	private static int cont = 0;

	private static boolean init = false;
	
	public ContatoService(ContatoRepository repo) {
		this.repo = repo;

	}
	public String incluir(Contato c) {
		try {
			if (c.getNome() == null || c.getNome().isBlank()) {
				return "erro: nome obrigatório";
			}
			if (c.getNome().length() < 3) {
				return "erro: nome muito curto";
			}
			if (c.getTelefone() == null || c.getTelefone().isBlank()) {
				return "erro: telefone obrigatorio";
	        }
			if (c.getEmail() == null || c.getEmail().isBlank()) {
				return "erro: email obrigatorio";
			}
			if (!c.getEmail().contains("@")) {
				return "erro: email invalido";
			}
			if (!c.getEmail().contains(".")) {
				return "erro: email invalido";
			}
			if (c.getIdade() < 0) {
				return "erro: idade invalida";
			}
			if (c.getIdade() > 150) {
				return "erro: idade invalida";
			}
			if (c.getStatus() == null) {
				System.out.println("STATUS RECEBIDO: " + c.getStatus());
				System.out.println("TIPO RECEBIDO: " + c.getTipo());
			    return "erro: status obrigatorio";
			}

			if (c.getTipo() == null) {
			    return "erro: tipo invalido. Use: FAMILIA, AMIGO, TRABALHO ou OUTRO";
			}

			List<Contato> todos = repo.findAll();
			for (int i = 0; i < todos.size(); i++) {
				if (todos.get(i).getEmail() != null && todos.get(i).getEmail().equals(c.getEmail())) {
					return "erro: ja existe contato com esse email";
				}
			}
			
			c.setDataCad(LocalDateTime.now());

			c.setAtivo(true);

			Contato salvo = repo.save(c);

			String resp = "Contato incluido com sucesso!\n";
			resp = resp + "ID: " + salvo.getId() + "\n";
			resp = resp + "Nome: " + salvo.getNome() + "\n";
			resp = resp + "Tel: " + salvo.getTelefone() + "\n";
			resp = resp + "Email: " + salvo.getEmail() + "\n";
			resp = resp + "Tipo: " + salvo.getTipo() + "\n";
			resp = resp + "Cadastrado em: " + salvo.getDataCad();
			
			return resp;

			
		} catch (Exception e) {
		    e.printStackTrace();
		    return "erro interno: " + e.getMessage();
		}
	}
	public List<Contato> listar() {
	    return repo.findAll();
	}
	
	public String pesquisar(Long id) {
	    Contato contato = repo.findById(id).orElse(null);

	    if (contato == null) {
	        return "erro: contato não encontrado";
	    }

	    return contato.toString();
	}
	
	public String excluir(Long id) {
	    if (!repo.existsById(id)) {
	        return "erro: contato não encontrado";
	    }

	    repo.deleteById(id);

	    return "contato excluído com sucesso";
	}
	
	public String editar(Long id, Contato c) {

	    Contato atual = repo.findById(id).orElse(null);

	    if (atual == null) {
	        return "erro: contato não encontrado";
	    }

	    if (c.getNome() != null && !c.getNome().isBlank()) {
	        atual.setNome(c.getNome());
	    }

	    if (c.getTelefone() != null && !c.getTelefone().isBlank()) {
	        atual.setTelefone(c.getTelefone());
	    }

	    if (c.getEmail() != null && !c.getEmail().isBlank()) {
	        atual.setEmail(c.getEmail());
	    }

	    if (c.getTipo() != null) {
	        atual.setTipo(c.getTipo());
	    }

	    if (c.getStatus() != null) {
	        atual.setStatus(c.getStatus());
	    }

	    Contato salvo = repo.save(atual);

	    return "contato atualizado com sucesso: " + salvo.getNome();
	}
	
	public List<Contato> pesquisar(String tipoBusca, String valor) {
		List<Contato> todos = repo.findAll();
		List<Contato> resultado = new ArrayList<>();
		
		for (Contato c : todos) {
			if (tipoBusca.equalsIgnoreCase("nome")) {
				if (c.getNome() != null &&
						c.getNome().toLowerCase().contains(valor.toLowerCase())) {
					resultado.add(c);
				}
			}
			if (tipoBusca.equalsIgnoreCase("telefone")) {
				if (c.getTelefone() != null &&
						c.getTelefone().toLowerCase().contains(valor.toLowerCase())) {
					resultado.add(c);
				}
			}
			if (tipoBusca.equalsIgnoreCase("email")) {
				if (c.getEmail() != null &&
						c.getEmail().toLowerCase().contains(valor.toLowerCase())) {
					resultado.add(c);
				}
			}
			if (tipoBusca.equalsIgnoreCase("tipo")) {
				if (c.getTipo() != null &&
						c.getTipo().name().equalsIgnoreCase(valor)) {
					resultado.add(c);
				}
			}
			if (tipoBusca.equalsIgnoreCase("id")) {
				Long id = Long.parseLong(valor);
				
				if (c.getId() !=null &&
						c.getId().equals(id)) {
					resultado.add(c);
				}
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



