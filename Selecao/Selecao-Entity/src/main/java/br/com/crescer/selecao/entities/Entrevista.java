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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author murillo.peteffi
 */
@Entity
@Table(name = "ENTREVISTA")
@XmlRootElement
public class Entrevista implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "ENTREVISTA_IDENTREVISTA_SEQ")
    @SequenceGenerator(name = "ENTREVISTA_IDENTREVISTA_SEQ", sequenceName = "ENTREVISTA_IDENTREVISTA_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "IDENTREVISTA")
    private Integer idEntrevista;
    
    @Basic(optional = false)
    @Column(name = "DATAENTREVISTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrevista;
    
    @Basic(optional = false)
    @Column(name = "PARECERRH")
    private String parecerRh;
    
    @Basic(optional = false)
    @Column(name = "PARECERTECNICO")
    private String parecerTecnico;
    
    @Column(name = "PROVAG36")
    private Double provaG36;
    
    @Column(name = "PROVAAC")
    private Double provaAc;
    
    @Column(name = "PROVATECNICA")
    private Double provaTecnica;
    
    @JoinColumn(name = "IDCANDIDATO", referencedColumnName = "IDCANDIDATO")
    @ManyToOne(optional = false)
    private Candidato idCandidato;
    
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    
    @JoinColumn(name = "IDGRUPODEPROVAS", referencedColumnName = "IDGRUPODEPROVAS")
    @ManyToOne(optional = false)
    private GrupoDeProvas grupoDeProvas;
    
    @JoinColumn(name = "IDDATAHORA", referencedColumnName = "IDDATAHORA")
    @ManyToOne(optional = false)
    private DataHora entrevistaRH;   

    public Entrevista() {
    }

    public Entrevista(Integer identrevista) {
        this.idEntrevista = identrevista;
    }

    public Entrevista(Date dataentrevista, String parecerrh, String parecertecnico, Double provag36, Double provaac, Double provatecnica, Candidato idCandidato, Usuario idusuario) {
        this.dataEntrevista = dataentrevista;
        this.parecerRh = parecerrh;
        this.parecerTecnico = parecertecnico;
        this.provaG36 = provag36;
        this.provaAc = provaac;
        this.provaTecnica = provatecnica;
        this.idCandidato = idCandidato;
        this.idUsuario = idusuario;
    }

    public int getIdEntrevista() {
        return idEntrevista;
    }

    public void setIdEntrevista(Integer idEntrevista) {
        this.idEntrevista = idEntrevista;
    }

    public Date getDataEntrevista() {
        return dataEntrevista;
    }

    public void setDataEntrevista(Date dataEntrevista) {
        this.dataEntrevista = dataEntrevista;
    }

    public String getParecerRh() {
        return parecerRh;
    }

    public void setParecerRh(String parecerRh) {
        this.parecerRh = parecerRh;
    }

    public String getParecerTecnico() {
        return parecerTecnico;
    }

    public void setParecerTecnico(String parecerTecnico) {
        this.parecerTecnico = parecerTecnico;
    }

    public Double getProvaG36() {
        return provaG36;
    }

    public void setProvaG36(Double provaG36) {
        this.provaG36 = provaG36;
    }

    public Double getProvaAc() {
        return provaAc;
    }

    public void setProvaAc(Double provaAc) {
        this.provaAc = provaAc;
    }

    public Double getProvaTecnica() {
        return provaTecnica;
    }

    public void setProvaTecnica(Double provaTecnica) {
        this.provaTecnica = provaTecnica;
    }

    public Candidato getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Candidato idcandidato) {
        this.idCandidato = idcandidato;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Entrevista[ identrevista=" + idEntrevista + " ]";
    }
    
}
