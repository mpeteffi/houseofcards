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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author murillo.peteffi
 */
@Entity
@Table(name = "DATAHORA")
@XmlRootElement
public class Datahora implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "DATAHORA_IDDATAHORA_SEQ")
    @SequenceGenerator(name = "DATAHORA_IDDATAHORA_SEQ", sequenceName = "DATAHORA_IDDATAHORA_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDATAHORA")
    private Integer idDataHora;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TITULO")
    private String titulo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATAHORAINICIAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraInicial;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATAHORAFINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraFinal;

    public Datahora() {
    }

    public Datahora(Integer iddatahora) {
        this.idDataHora = iddatahora;
    }

    public Datahora(Integer iddatahora, String titulo, Date datahorainicial, Date datahorafinal) {
        this.idDataHora = iddatahora;
        this.titulo = titulo;
        this.dataHoraInicial = datahorainicial;
        this.dataHoraFinal = datahorafinal;
    }

    public Integer getIdDataHora() {
        return idDataHora;
    }

    public void setIdDataHora(Integer idDataHora) {
        this.idDataHora = idDataHora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getDataHoraInicial() {
        return dataHoraInicial;
    }

    public void setDataHoraInicial(Date dataHoraInicial) {
        this.dataHoraInicial = dataHoraInicial;
    }

    public Date getDataHoraFinal() {
        return dataHoraFinal;
    }

    public void setDataHoraFinal(Date dataHoraFinal) {
        this.dataHoraFinal = dataHoraFinal;
    }

    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Datahora[ iddatahora=" + idDataHora + " ]";
    }
    
}
