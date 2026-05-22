package com.agenda.controller;

import com.agenda.dtos.ContatoResponse;
import com.agenda.dtos.CriarContatoRequest;
import com.agenda.dtos.AtualizarContatoRequest;
import com.agenda.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

	private final ContatoService service;

	public ContatoController(ContatoService service) {
		this.service = service;
	}

	@PostMapping("/incluir")
	public ResponseEntity<ContatoResponse> incluir(@RequestBody @Valid CriarContatoRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.incluir(request));
	}

	@GetMapping("/listar")
	public ResponseEntity<List<ContatoResponse>> listar() {
		return ResponseEntity.ok(service.listar());
	}

	@GetMapping("/pesquisar")
	public ResponseEntity<List<ContatoResponse>> pesquisar(
			@RequestParam String tipoBusca,
			@RequestParam String valor) {
		return ResponseEntity.ok(service.pesquisar(tipoBusca, valor));
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<ContatoResponse> editar(
			@PathVariable Long id,
			@RequestBody @Valid AtualizarContatoRequest request) {
		return ResponseEntity.ok(service.editar(id, request));
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/logs")
	public ResponseEntity<String> verLogs() {
		return ResponseEntity.ok(service.verLogs());
	}
}
