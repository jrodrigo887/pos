package com.agenda.controller;

import com.agenda.service.*;
import com.agenda.dto.ContatoRequest;
import com.agenda.dto.ContatoResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

	private final ContatoService service;
	
	public ContatoController (ContatoService service) {
		this.service = service;
		
	}

	@PostMapping("/incluir")
	public ResponseEntity<ContatoResponse> incluir(@RequestBody ContatoRequest request) {
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
	
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<String> excluir(
		@PathVariable Long id) {

			if (id == null){
				return ResponseEntity.badRequest().build();
			}

			service.excluir(id);

	    	return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<ContatoResponse> editar(
	        @PathVariable Long id,
	        @RequestBody ContatoRequest request) {

				if (id == null){
					return ResponseEntity.badRequest().build();
				}

	    return ResponseEntity.ok(service.editar(id, request));
	}
	
		
	@GetMapping("/logs")
    public ResponseEntity<String> verLogs() {
        return ResponseEntity.ok(service.verLogs());
    }

}
