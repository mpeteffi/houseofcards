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
    private int idprocessoseletivo;
    
    @Basic(optional = false)
    @Column(name = "EDICAO")
    private String edicao;
    
    @Basic(optional = false)
    @Column(name = "INICIOSELECAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioselecao;
    
    @Basic(optional = false)
    @Column(name = "FINALSELECAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finalselecao;
    
    @Basic(optional = false)
    @Column(name = "INICIOAULA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inicioaula;
    
    @Basic(optional = false)
    @Column(name = "FINALAULA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finalaula;

    public Processoseletivo() {
    }

    public Processoseletivo(int idprocessoseletivo) {
        this.idprocessoseletivo = idprocessoseletivo;
    }

    public Processoseletivo(String edicao, Date inicioselecao, Date finalselecao, Date inicioaula, Date finalaula) {
        this.edicao = edicao;
        this.inicioselecao = inicioselecao;
        this.finalselecao = finalselecao;
        this.inicioaula = inicioaula;
        this.finalaula = finalaula;
    }

    public int getIdprocessoseletivo() {
        return idprocessoseletivo;
    }

    public void setIdprocessoseletivo(int idprocessoseletivo) {
        this.idprocessoseletivo = idprocessoseletivo;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public Date getInicioselecao() {
        return inicioselecao;
    }

    public void setInicioselecao(Date inicioselecao) {
        this.inicioselecao = inicioselecao;
    }

    public Date getFinalselecao() {
        return finalselecao;
    }

    public void setFinalselecao(Date finalselecao) {
        this.finalselecao = finalselecao;
    }

    public Date getInicioaula() {
        return inicioaula;
    }

    public void setInicioaula(Date inicioaula) {
        this.inicioaula = inicioaula;
    }

    public Date getFinalaula() {
        return finalaula;
    }

    public void setFinalaula(Date finalaula) {
        this.finalaula = finalaula;
    }

    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Processoseletivo[ idprocessoseletivo=" + idprocessoseletivo + " ]";
    }
    
}
