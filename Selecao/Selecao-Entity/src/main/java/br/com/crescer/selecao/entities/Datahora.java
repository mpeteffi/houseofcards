package br.com.crescer.selecao.entities;

import br.com.crescer.selecao.entities.enums.TipoAgendamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author murillo.peteffi
 */
@Entity
@Table(name = "DATAHORA")
@XmlRootElement
public class Datahora implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "DATAHORA_IDDATAHORA_SEQ")
    @SequenceGenerator(name = "DATAHORA_IDDATAHORA_SEQ", sequenceName = "DATAHORA_IDDATAHORA_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDATAHORA")
    @JsonProperty("id")
    private Integer idDataHora;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TITULO")
    @JsonProperty("title")
    private String titulo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATAHORAINICIAL")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("start")
    private Date dataHoraInicial;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATAHORAFINAL")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("end")
    private Date dataHoraFinal;
    
    //TODO:Refatorar retirar, pois não terá evento o dia inteiro.
    @Basic(optional = false)
    @NotNull
    @Column(name = "TODODIA")
    @JsonProperty("allDay")
    private boolean  todoDia;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIPO")
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private TipoAgendamento  tipo;

    public Datahora() {
    }

    public Datahora(Integer idDataHora) {
        this.idDataHora = idDataHora;
    }

    public Datahora(Integer idDatahora, String titulo, Date dataHoraInicial, Date dataHoraFinal,boolean todoDia,TipoAgendamento tipo) {
        this.idDataHora = idDatahora;
        this.titulo = titulo;
        this.dataHoraInicial = dataHoraInicial;
        this.dataHoraFinal = dataHoraFinal;
        this.todoDia = todoDia;
        this.tipo = tipo;
    }
    
    public Datahora(String titulo, Date dataHoraInicial, Date dataHoraFinal,boolean todoDia,TipoAgendamento tipo) {
        this.titulo = titulo;
        this.dataHoraInicial = dataHoraInicial;
        this.dataHoraFinal = dataHoraFinal;
        this.todoDia = todoDia;
        this.tipo = tipo;
    }   

    public Integer getIdDataHora() {
        return idDataHora;
    }

    public boolean isTodoDia() {
        return todoDia;
    }

    public void setTodoDia(boolean todoDia) {
        this.todoDia = todoDia;
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
    
    public TipoAgendamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAgendamento tipo) {
        this.tipo = tipo;
    }
}
