package com.agenda.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.agenda.domain.Contato;
import com.agenda.domain.TipoContato;
import com.agenda.dtos.ContatoResponse;
import com.agenda.dtos.CriarContatoRequest;
import com.agenda.dtos.AtualizarContatoRequest;
import com.agenda.exceptions.ContatoNaoEncontradoException;
import com.agenda.exceptions.RegraDeNegocioException;
import com.agenda.filtros.Filtro;
import com.agenda.filtros.IPesquisarContatoStrategy;
import com.agenda.repository.ContatoRepository;

@Service
public class ContatoService {

	private final ContatoRepository repo;

	private static final List<String> logs = new ArrayList<>();

	public ContatoService(ContatoRepository repo) {
		this.repo = repo;
	}

	public ContatoResponse incluir(CriarContatoRequest request) {
		if (ehEmailCadastrado(request.email())) {
			throw new RegraDeNegocioException("Já existe um contato com esse e-mail.");
		}

		var contato = new Contato();
		contato.setNome(request.nome());
		contato.setTelefone(request.telefone());
		contato.setEmail(request.email());
		contato.setEndereco(request.endereco());
		contato.setIdade(request.idade());
		contato.setTipo(request.tipo());
		contato.setStatus(request.status());
		contato.setDataCad(LocalDateTime.now());
		contato.setAtivo(true);

		return ContatoResponse.from(repo.save(contato));
	}

	public List<ContatoResponse> listar() {
		return repo.findAll().stream()
				.map(ContatoResponse::from)
				.toList();
	}

	public List<ContatoResponse> pesquisar(String tipoBusca, String valor) {
		IPesquisarContatoStrategy pesquisa = Filtro.chaveDoContato(tipoBusca);
		logs.add("pesquisou por " + tipoBusca + " valor=" + valor);
		return pesquisa.executar(repo, valor).stream()
				.map(ContatoResponse::from)
				.toList();
	}

	public void excluir(@NonNull Long id) {
		Contato contato = repo.findById(id)
				.orElseThrow(() -> new ContatoNaoEncontradoException("Contato não encontrado."));

		if (contato.getTipo() == TipoContato.FAMILIA) {
			throw new RegraDeNegocioException("Contatos do tipo FAMILIA não podem ser excluídos.");
		}

		repo.deleteById(id);
	}

	public ContatoResponse editar(@NonNull Long id, AtualizarContatoRequest request) {
		Contato atual = repo.findById(id)
				.orElseThrow(() -> new ContatoNaoEncontradoException("Contato não encontrado."));

		if (request.nome() != null && !request.nome().isBlank())
			atual.setNome(request.nome());
		if (request.telefone() != null && !request.telefone().isBlank())
			atual.setTelefone(request.telefone());
		if (request.email() != null && !request.email().isBlank())
			atual.setEmail(request.email());
		if (request.endereco() != null)
			atual.setEndereco(request.endereco());
		if (request.idade() != null)
			atual.setIdade(request.idade());
		if (request.tipo() != null)
			atual.setTipo(request.tipo());
		if (request.status() != null)
			atual.setStatus(request.status());

		return ContatoResponse.from(repo.save(atual));
	}

	public String verLogs() {
		StringBuilder sb = new StringBuilder("=== LOGS ===\n");
		sb.append("Total de pesquisas: ").append(logs.size()).append("\n\n");
		logs.forEach(log -> sb.append(log).append("\n"));
		return sb.toString();
	}

	private boolean ehEmailCadastrado(String email) {
		return !repo.findByEmail(email).isEmpty();
	}
}
