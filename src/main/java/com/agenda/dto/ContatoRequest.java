package com.agenda.dto;

import com.agenda.domain.Status;
import com.agenda.domain.TipoContato;

public class ContatoRequest {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private Integer idade;
    private TipoContato tipo;
    private Status status;

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




}
