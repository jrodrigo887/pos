package com.agenda.dto;

import java.time.LocalDateTime;

import com.agenda.domain.Status;
import com.agenda.domain.TipoContato;

public class ContatoResponse {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private Integer idade;
    private TipoContato tipo;
    private Status status;
    private Boolean ativo;
    private LocalDateTime dataCad;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public Integer getIdade(){
        return idade;
    }

    public void setIdade(Integer idade){
        this.idade = idade;
    }

    public TipoContato getTipo(){
        return tipo;
    }

    public void setTipo(TipoContato tipo){
        this.tipo = tipo;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus (Status status){
        this.status = status;
    }

    public Boolean getAtivo(){
        return ativo;
    }

    public void setAtivo(Boolean ativo){
        this.ativo = ativo;
    }

    public LocalDateTime getDataCad() {
        return dataCad;
    }

    public void setDataCad(LocalDateTime dataCad) {
        this.dataCad = dataCad;
    }

}