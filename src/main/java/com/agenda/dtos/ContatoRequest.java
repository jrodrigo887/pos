package com.agenda.dtos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public class ContatoRequest {
    @NotBlank(message ="Nome obrigatótio")
    private String nome;

    @Email(message = "Email inválido")
    private String email;

     @NotBlank(message ="Telefone é obrigatório")
    private String telefone;
    
//Getter Nome
public String getNome() {
return nome;
}
 //Setter nome
 public void setNome(String nome) {
this.nome = nome;
 }
 //Getter nome
 public String getEmail() {
    return email;
 }
  
 //Setter Email
 public void setEmail(String email){
    this.email = email;
 } 
 //Getter Telefone
 public String getTelefone() {
   return telefone;
 }
   //Setter Telefone
   public void setTelefone(String telefone) {
   this.telefone = telefone;
 }
}




