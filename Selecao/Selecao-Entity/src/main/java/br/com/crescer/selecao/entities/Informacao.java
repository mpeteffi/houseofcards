package br.com.crescer.selecao.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author murillo.peteffi
 */
@Entity
@Table(name = "INFORMACAO", uniqueConstraints={@UniqueConstraint(columnNames = {"IDCANDIDATO","IDPROCESSOSELETIVO"})})
@XmlRootElement
public class Informacao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "INFORMACAO_IDINFORMACAO_SEQ")
    @SequenceGenerator(name = "INFORMACAO_IDINFORMACAO_SEQ", sequenceName = "INFORMACAO_IDINFORMACAO_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "IDINFORMACAO")
    private int idinformacao;
    
    @Basic(optional = false)
    @Column(name = "TELEFONE")
    private String telefone;
    
    @Basic(optional = false)
    @Column(name = "DATANASCIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datanascimento;
    
    @Basic(optional = false)
    @Column(name = "CIDADE")
    private String cidade;
    
    @Basic(optional = false)
    @Column(name = "URLLINKEDIN")
    private String urllinkedin;
    
    @Basic(optional = false)
    @Column(name = "SENHA")
    private String senha;
    
    @JoinColumn(name = "IDCANDIDATO", referencedColumnName = "IDCANDIDATO")
    @ManyToOne(optional = false)
    private Candidato idcandidato;
    
    @JoinColumn(name = "IDPROCESSOSELETIVO", referencedColumnName = "IDPROCESSOSELETIVO")
    @ManyToOne(optional = false)
    private Processoseletivo idprocessoseletivo;

    public Informacao() {
    }

    public Informacao(int idinformacao) {
        this.idinformacao = idinformacao;
    }

    public Informacao(String telefone, Date datanascimento, String cidade, String urllinkedin, String senha, Candidato candidato, Processoseletivo processoseletivo) {
        this.telefone = telefone;
        this.datanascimento = datanascimento;
        this.cidade = cidade;
        this.urllinkedin = urllinkedin;
        this.senha = senha;
        this.idcandidato = candidato;
        this.idprocessoseletivo = processoseletivo;
    }

    public int getIdinformacao() {
        return idinformacao;
    }

    public void setIdinformacao(int idinformacao) {
        this.idinformacao = idinformacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUrllinkedin() {
        return urllinkedin;
    }

    public void setUrllinkedin(String urllinkedin) {
        this.urllinkedin = urllinkedin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Candidato getIdcandidato() {
        return idcandidato;
    }

    public void setIdcandidato(Candidato idcandidato) {
        this.idcandidato = idcandidato;
    }

    public Processoseletivo getIdprocessoseletivo() {
        return idprocessoseletivo;
    }

    public void setIdprocessoseletivo(Processoseletivo idprocessoseletivo) {
        this.idprocessoseletivo = idprocessoseletivo;
    }

    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Informacao[ idinformacao=" + idinformacao + " ]";
    }
    
}