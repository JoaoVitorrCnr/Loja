package com.ProjetoWeb.Loja;

public class Cliente {
     private int id;
     private String nome;
     private String email;
     private String senha;
 
     // Construtor
     /*public Cliente(String nome, String email, String senha) {
         this.nome = nome;
         this.email = email;
         this.senha = senha;
     }*/
 
     // Getters e Setters
     public int getId() {
         return id;
     }
 
     public void setId(int id) {
         this.id = id;
     }
 
     public String getNome() {
         return nome;
     }
 
     public void setNome(String nome) {
         this.nome = nome;
     }
 
     public String getEmail() {
         return email;
     }
 
     public void setEmail(String email) {
         this.email = email;
     }
 
     public String getSenha() {
         return senha;
     }
 
     public void setSenha(String senha) {
         this.senha = senha;
     }
}