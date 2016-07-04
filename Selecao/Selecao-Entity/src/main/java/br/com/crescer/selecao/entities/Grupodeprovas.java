package br.com.crescer.selecao.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author murillo.peteffi
 */
@Entity
@Table(name = "GRUPODEPROVAS")
@XmlRootElement
public class Grupodeprovas implements Serializable {

    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "GRUPODEPROVAS_IDGRUPODEPROVAS")
    @SequenceGenerator(name = "GRUPODEPROVAS_IDGRUPODEPROVAS", sequenceName = "GRUPODEPROVAS_IDGRUPODEPROVAS", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDGRUPODEPROVAS")
    private Integer idGrupoDeProvas;
    
    @JoinColumn(name = "IDDATAHORA", referencedColumnName = "IDDATAHORA")
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Datahora idDataHora;

    public Grupodeprovas() {
    }

    public Grupodeprovas(Integer idgrupodeprovas) {
        this.idGrupoDeProvas = idgrupodeprovas;
    }
    
    public Grupodeprovas(Datahora dataHora) {
        this.idDataHora = dataHora;
    }

    public Grupodeprovas(Integer idgrupodeprovas ,Datahora dataHora) {
        this.idGrupoDeProvas = idgrupodeprovas;
        this.idDataHora = dataHora;
    }
    
    public Integer getIdGrupoDeProvas() {
        return idGrupoDeProvas;
    }

    public void setIdGrupoDeProvas(Integer idGrupoDeProvas) {
        this.idGrupoDeProvas = idGrupoDeProvas;
    }

    public Datahora getIdDataHora() {
        return idDataHora;
    }

    public void setIdDataHora(Datahora idDataHora) {
        this.idDataHora = idDataHora;
    }

    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Grupodeprovas[ idgrupodeprovas=" + idGrupoDeProvas + " ]";
    }
    
}
