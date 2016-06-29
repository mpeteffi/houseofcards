/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.selecao.entities;

/**
 *
 * @author andrews.silva
 */
public class CandidatoFiltroDTO {
    private String status;
    private String nome;
    private String email;
    private String telefone;

    public CandidatoFiltroDTO(String status, String nome, String email, String telefone) {
        this.status = status;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
    
   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
