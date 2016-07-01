package br.com.crescer.selecao.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
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
@Table(name = "PROCESSOSELETIVO", uniqueConstraints={@UniqueConstraint(columnNames = {"EDICAO"})})
@XmlRootElement
public class Processoseletivo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "PROCESSOSELETIVO_IDPROCESSOSEL")
    @SequenceGenerator(name = "PROCESSOSELETIVO_IDPROCESSOSEL", sequenceName = "PROCESSOSELETIVO_IDPROCESSOSEL", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "IDPROCESSOSELETIVO")
    private Integer idProcessoSeletivo;
    
    @Basic(optional = false)
    @Column(name = "EDICAO")
    private String edicao;
    
    @Basic(optional = false)
    @Column(name = "INICIOSELECAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioSelecao;
    
    @Basic(optional = false)
    @Column(name = "FINALSELECAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finalSelecao;
    
    @Basic(optional = false)
    @Column(name = "INICIOAULA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioAula;
    
    @Basic(optional = false)
    @Column(name = "FINALAULA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finalAula;

    public Processoseletivo() {
    }

    public Processoseletivo(Integer idprocessoseletivo) {
        this.idProcessoSeletivo = idprocessoseletivo;
    }

    public Processoseletivo(String edicao, Date inicioselecao, Date finalselecao, Date inicioaula, Date finalaula) {
        this.edicao = edicao;
        this.inicioSelecao = inicioselecao;
        this.finalSelecao = finalselecao;
        this.inicioAula = inicioaula;
        this.finalAula = finalaula;
    }

    public Integer getIdProcessoSeletivo() {
        return idProcessoSeletivo;
    }

    public void setIdProcessoSeletivo(Integer idProcessoSeletivo) {
        this.idProcessoSeletivo = idProcessoSeletivo;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public Date getInicioSelecao() {
        return inicioSelecao;
    }

    public void setInicioSelecao(Date inicioSelecao) {
        this.inicioSelecao = inicioSelecao;
    }

    public Date getFinalSelecao() {
        return finalSelecao;
    }

    public void setFinalSelecao(Date finalSelecao) {
        this.finalSelecao = finalSelecao;
    }

    public Date getInicioAula() {
        return inicioAula;
    }

    public void setInicioAula(Date inicioAula) {
        this.inicioAula = inicioAula;
    }

    public Date getFinalAula() {
        return finalAula;
    }

    public void setFinalAula(Date finalAula) {
        this.finalAula = finalAula;
    }

    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Processoseletivo[ idprocessoseletivo=" + idProcessoSeletivo + " ]";
    }
    
}
