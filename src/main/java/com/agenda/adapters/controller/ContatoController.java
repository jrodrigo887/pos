package com.agenda.adapters.controller;

import com.agenda.adapters.controller.converters.ContatoConverter;
import com.agenda.adapters.dtos.AtualizarContatoRequest;
import com.agenda.adapters.dtos.ContatoRequest;
import com.agenda.adapters.dtos.ContatoResponse;
import com.agenda.core.usecases.contatos.CriarContatoUsecaseInterface;
import com.agenda.service.ContatoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private CriarContatoUsecaseInterface criarContatoUsecase;

	@PostMapping("/incluir")
	public ResponseEntity<ContatoResponse> incluir(@RequestBody @Valid ContatoRequest request) {

		// converter mapper class
		var contato = ContatoConverter.toDomain(request);

		var result = criarContatoUsecase.execute(contato);

		return ResponseEntity.status(HttpStatus.CREATED).body(ContatoConverter.toResponse(result));
	}

	// ====================== ############## ==============
	//
	//
	//
	//
	//
	//
	//
	// ================ ############# =================
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
