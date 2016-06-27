package br.com.crescer.selecao.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author murillo.peteffi
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "USUARIO_IDUSUARIO_SEQ")
    @SequenceGenerator(name = "USUARIO_IDUSUARIO_SEQ", sequenceName = "USUARIO_IDUSUARIO_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "IDUSUARIO")
    private int idusuario;
    
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    
    @Basic(optional = false)
    @Column(name = "SENHA")
    private String senha;
    
    @Basic(optional = false)
    @Column(name = "IMAGEM")
    private String imagem;

    public Usuario() {
    }

    public Usuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(String nome, String email, String senha, String imagem) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.imagem = imagem;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Usuario[ idusuario=" + idusuario + " ]";
    }
    
}
