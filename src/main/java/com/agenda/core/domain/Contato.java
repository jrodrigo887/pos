package com.agenda.core.domain;

import java.time.LocalDateTime;

import com.agenda.core.enums.Status;
import com.agenda.core.enums.TipoContato;

public class Contato {
    private Long id; // publico pq e mais facil

    // nome da pessoa
    private String nome;

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
    public Contato(Long id, String nome, String telefone, String email, String endereco, Integer idade,
            TipoContato tipo, LocalDateTime dataCad,
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
