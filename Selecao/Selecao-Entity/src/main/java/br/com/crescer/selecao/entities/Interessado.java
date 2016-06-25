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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Murillo
 */
@Entity
@Table(name = "INTERESSADO", uniqueConstraints={@UniqueConstraint(columnNames = {"EMAIL"})})
@XmlRootElement
public class Interessado implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "INTERESSADO_IDINTERESSADO_SEQ")
    @SequenceGenerator(name = "INTERESSADO_IDINTERESSADO_SEQ", sequenceName = "INTERESSADO_IDINTERESSADO_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "IDINTERESSADO")
    private int idinteressado;
    
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    
    @Basic(optional = false)
    @Column(name = "INSTITUICAOENSINO")
    private String instituicaoensino;
    
    @Basic(optional = false)
    @Column(name = "CURSO")
    private String curso;
    
    @Basic(optional = false)
    @Column(name = "PREVISAOFORMATURA")
    private String previsaoformatura;
    
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;

    public Interessado() {
    }

    public Interessado(int idinteressado) {
        this.idinteressado = idinteressado;
    }

    public Interessado(String nome, String email, String instituicaoensino, String curso, String previsaoformatura, String status) {
        this.nome = nome;
        this.email = email;
        this.instituicaoensino = instituicaoensino;
        this.curso = curso;
        this.previsaoformatura = previsaoformatura;
        this.status = status;
    }

    public int getIdinteressado() {
        return idinteressado;
    }

    public void setIdinteressado(int idinteressado) {
        this.idinteressado = idinteressado;
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

    public String getInstituicaoensino() {
        return instituicaoensino;
    }

    public void setInstituicaoensino(String instituicaoensino) {
        this.instituicaoensino = instituicaoensino;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPrevisaoformatura() {
        return previsaoformatura;
    }

    public void setPrevisaoformatura(String previsaoformatura) {
        this.previsaoformatura = previsaoformatura;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Interessado[ idinteressado=" + idinteressado + " ]";
    }
    
}
