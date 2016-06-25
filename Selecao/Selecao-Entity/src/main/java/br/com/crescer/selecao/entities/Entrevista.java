package br.com.crescer.selecao.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author Murillo
 */
@Entity
@Table(name = "ENTREVISTA", uniqueConstraints={
    @UniqueConstraint(columnNames = {"IDCANDIDATO","PROVAG36"}), 
    @UniqueConstraint(columnNames = {"IDCANDIDATO","PROVAAC"}), 
    @UniqueConstraint(columnNames = {"IDCANDIDATO","PROVATECNICA"})})
@XmlRootElement
public class Entrevista implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "ENTREVISTA_IDENTREVISTA_SEQ")
    @SequenceGenerator(name = "ENTREVISTA_IDENTREVISTA_SEQ", sequenceName = "ENTREVISTA_IDENTREVISTA_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "IDENTREVISTA")
    private int identrevista;
    
    @Basic(optional = false)
    @Column(name = "DATAENTREVISTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataentrevista;
    
    @Basic(optional = false)
    @Column(name = "PARECERRH")
    private String parecerrh;
    
    @Basic(optional = false)
    @Column(name = "PARECERTECNICO")
    private String parecertecnico;
    
    @Column(name = "PROVAG36")
    private BigDecimal provag36;
    
    @Column(name = "PROVAAC")
    private BigDecimal provaac;
    
    @Column(name = "PROVATECNICA")
    private BigDecimal provatecnica;
    
    @JoinColumn(name = "IDCANDIDATO", referencedColumnName = "IDCANDIDATO")
    @ManyToOne(optional = false)
    private Candidato idcandidato;
    
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario idusuario;

    public Entrevista() {
    }

    public Entrevista(int identrevista) {
        this.identrevista = identrevista;
    }

    public Entrevista(Date dataentrevista, String parecerrh, String parecertecnico, Candidato candidato, Usuario usuario) {
        this.dataentrevista = dataentrevista;
        this.parecerrh = parecerrh;
        this.parecertecnico = parecertecnico;
        this.idcandidato = candidato;
        this.idusuario = usuario;
    }

    public int getIdentrevista() {
        return identrevista;
    }

    public void setIdentrevista(int identrevista) {
        this.identrevista = identrevista;
    }

    public Date getDataentrevista() {
        return dataentrevista;
    }

    public void setDataentrevista(Date dataentrevista) {
        this.dataentrevista = dataentrevista;
    }

    public String getParecerrh() {
        return parecerrh;
    }

    public void setParecerrh(String parecerrh) {
        this.parecerrh = parecerrh;
    }

    public String getParecertecnico() {
        return parecertecnico;
    }

    public void setParecertecnico(String parecertecnico) {
        this.parecertecnico = parecertecnico;
    }

    public BigDecimal getProvag36() {
        return provag36;
    }

    public void setProvag36(BigDecimal provag36) {
        this.provag36 = provag36;
    }

    public BigDecimal getProvaac() {
        return provaac;
    }

    public void setProvaac(BigDecimal provaac) {
        this.provaac = provaac;
    }

    public BigDecimal getProvatecnica() {
        return provatecnica;
    }

    public void setProvatecnica(BigDecimal provatecnica) {
        this.provatecnica = provatecnica;
    }

    public Candidato getIdcandidato() {
        return idcandidato;
    }

    public void setIdcandidato(Candidato idcandidato) {
        this.idcandidato = idcandidato;
    }

    public Usuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Usuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Entrevista[ identrevista=" + identrevista + " ]";
    }
    
}
