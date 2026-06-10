package com.agenda.adapters.in.http;

import com.agenda.adapters.in.http.dto.AtualizarContatoRequest;
import com.agenda.adapters.in.http.dto.ContatoRequest;
import com.agenda.adapters.in.http.dto.ContatoResponse;
import com.agenda.core.usecases.ContatoUseCases;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

	private final ContatoUseCases contatoCase;

	public ContatoController(ContatoUseCases contatoUseCases) {
		this.contatoCase = contatoUseCases;
	}

	@PostMapping("/incluir")
	public ResponseEntity<ContatoResponse> incluir(@RequestBody @Valid ContatoRequest request) {

		var contato = ContatoHttpConverter.toDomain(request);

		var result = contatoCase
				.criar()
				.execute(contato);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(ContatoHttpConverter.toResponse(result));
	}

	@GetMapping("/listar")
	public ResponseEntity<List<ContatoResponse>> listar() {
		var contatos = contatoCase
				.listar()
				.execute();

		return ResponseEntity
				.ok(contatos.stream()
						.map(ContatoHttpConverter::toResponse)
						.toList());
	}

	@GetMapping("/pesquisar")
	public ResponseEntity<List<ContatoResponse>> pesquisar(
			@RequestParam String tipoBusca,
			@RequestParam String valor) {

		var result = contatoCase
				.pesquisar()
				.execute(tipoBusca, valor);

		return ResponseEntity
				.ok(result.stream()
						.map(ContatoHttpConverter::toResponse)
						.toList());
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<ContatoResponse> editar(
			@PathVariable Long id,
			@RequestBody @Valid AtualizarContatoRequest request) {

		var result = contatoCase
				.atualizar()
				.execute(id, ContatoHttpConverter.applyUpdate(request));

		return ResponseEntity.ok(ContatoHttpConverter.toResponse(result));
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		this.contatoCase.excluir().execute(id);

		return ResponseEntity.noContent().build();
	}
}
