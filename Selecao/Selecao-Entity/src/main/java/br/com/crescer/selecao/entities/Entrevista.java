package br.com.crescer.selecao.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull
    @Column(name = "IDENTREVISTA")
    private Integer idEntrevista;
        
    @Size(max = 4000)
    @Column(name = "PARECERRH")
    private String parecerRh;
    
    @Size(max = 4000)
    @Column(name = "PARECERTECNICO")
    private String parecerTecnico;
    
    @Column(name = "PROVAG36")
    private Double provaG36;
    
    @Column(name = "PROVAAC")
    private Double provaAc;
    
    @Column(name = "PROVATECNICA")
    private Double provaTecnica;
    
    @JoinColumn(name = "IDCANDIDATO", referencedColumnName = "IDCANDIDATO")
    @ManyToOne(optional = false,cascade = CascadeType.PERSIST)
    private Candidato idCandidato;
    
    @JoinColumn(name = "IDUSUARIO", referencedColumnName = "IDUSUARIO")
    @ManyToOne(optional = false)
    private Usuario idUsuario;
    
    @JoinColumn(name = "IDDATAHORA", referencedColumnName = "IDDATAHORA")
    @ManyToOne(optional = true,cascade = CascadeType.PERSIST)
    private Datahora idDataHora;
   
    
    @JoinColumn(name = "IDGRUPODEPROVAS", referencedColumnName = "IDGRUPODEPROVAS")
    @ManyToOne
    private Grupodeprovas idGrupoDeProvas;

    public Entrevista() {
    }

    public Entrevista(Integer identrevista) {
        this.idEntrevista = identrevista;
    }

    public Entrevista(Candidato candidato , Datahora data, Usuario usuario) {
        this.idCandidato = candidato;
        this.idDataHora = data;
        this.idUsuario = usuario;
    }
    
    public Entrevista(Candidato candidato, Usuario usuario ) {
        this.idCandidato = candidato;
        this.idUsuario = usuario;
    }
    
    public Entrevista( String parecerRh, String parecerTecnico, Double provaG36, Double provaAc, Double provaTecnica, Candidato idCandidato, Usuario idUsuario) {
        this.parecerRh = parecerRh;
        this.parecerTecnico = parecerTecnico;
        this.provaG36 = provaG36;
        this.provaAc = provaAc;
        this.provaTecnica = provaTecnica;
        this.idCandidato = idCandidato;
        this.idUsuario = idUsuario;
    }

    public Candidato getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Candidato idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEntrevista() {
        return idEntrevista;
    }

    public void setIdEntrevista(Integer idEntrevista) {
        this.idEntrevista = idEntrevista;
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

    public Datahora getIdDataHora() {
        return idDataHora;
    }

    public void setIdDataHora(Datahora idDataHora) {
        this.idDataHora = idDataHora;
    }

    public Grupodeprovas getIdGrupoDeProvas() {
        return idGrupoDeProvas;
    }

    public void setIdGrupoDeProvas(Grupodeprovas idGrupoDeProvas) {
        this.idGrupoDeProvas = idGrupoDeProvas;
    }

    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Entrevista[ identrevista=" + idEntrevista + " ]";
    }
    
}
