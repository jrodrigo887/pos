package com.agenda.controller;

import com.agenda.service.*;
import com.agenda.domain.*;

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
	public ResponseEntity<String> incluir(@RequestBody Contato c) {
		String resposta = service.incluir(c);
		return ResponseEntity.ok(resposta);
	}
		
			

	@GetMapping("/listar")
	public ResponseEntity<List<Contato>> listar() {
	    return ResponseEntity.ok(service.listar());
	}
	
	@GetMapping("/pesquisar")
	public ResponseEntity<List<Contato>> pesquisar(
	        @RequestParam String tipoBusca,
	        @RequestParam String valor) {

	    return ResponseEntity.ok(service.pesquisar(tipoBusca, valor));
	}
	
	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<String> excluir(@PathVariable Long id) {
	    return ResponseEntity.ok(service.excluir(id));
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<String> editar(
	        @PathVariable Long id,
	        @RequestBody Contato c) {

	    return ResponseEntity.ok(service.editar(id, c));
	}
	
		
	@GetMapping("/logs")
    public ResponseEntity<String> verLogs() {
        return ResponseEntity.ok(service.verLogs());
    }

}
