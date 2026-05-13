package com.agenda.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contatos")
public class Contato {

    // id do contato
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // publico pq e mais facil

    // nome da pessoa
    private String nome;

    @Enumerated(EnumType.STRING)
    private Status status;

    // telefone
    private String telefone; // abreviado pra economizar

    // email
    private String email;

    private String endereco; // endereco abreviado com palavra reservada END

    private Integer idade;

    private TipoContato tipo; // FAMILIA, AMIGO, TRABALHO, OUTRO - string mesmo - ALTERADO SERGIO

    // data de cadastro - salva como string mesmo pq e mais facil - ALTERADO SERGIO
    private LocalDateTime dataCad = LocalDateTime.now();

    // flag se ta ativo
    private boolean ativo = true; // "S" ou "N" - ALTERADO SERGIO

    // construtor vazio pro JPA
    public Contato() {
    }

    // construtor com tudo
    public Contato(Long id, String nome, String telefone, String email, String endereco, Integer idade, TipoContato tipo, LocalDateTime dataCad,
            boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.idade = idade;
        this.tipo = tipo;
        this.dataCad = dataCad;
        this.ativo = ativo;
    }

    // getters e setters - alguns sim outros nao
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String tel) {
        this.telefone = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public TipoContato getTipo() {
        return tipo;
    }

    public void setTipo(TipoContato tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataCad() {
        return dataCad;
    }

    public void setDataCad(LocalDateTime dataCad) {
        this.dataCad = dataCad;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    // metodo que valida o contato - poderia estar em outro lugar mas ta aqui mesmo
    public boolean valida() {
        if (this.nome == null || this.nome.isBlank())
            return false;
        if (this.nome.length() < 3)
            return false;
        if (this.telefone == null || this.telefone.isBlank())
            return false;
        if (this.email == null || this.email.isBlank())
            return false;
        if (!this.email.contains("@"))
            return false;
        if (!this.email.contains("."))
        	return false;
        if (this.idade < 0 || this.idade > 150)
            return false;
        if (this.status == null)
        	return false;
        
        return true;
    }

    // formata o contato pra exibir - mistura model com view
    public String formatar() {
        String s = "";
        s = s + "ID: " + this.id + " | ";
        s = s + "Nome: " + this.nome + " | ";
        s = s + "Tel: " + this.telefone + " | ";
        s = s + "Email: " + this.email + " | ";
        s = s + "End: " + this.endereco + " | ";
        s = s + "Idade: " + this.idade + " | ";
        s = s + "Tipo: " + this.tipo + " | ";
        s = s + "Ativo: " + this.ativo;
        return s;
    }

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
