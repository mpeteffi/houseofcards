/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.selecao.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author Michel
 */
@Entity
@Table(name = "DATAHORA")
@XmlRootElement
public class DataHora implements Serializable{

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "DATAHORA_IDDATAHORA_SEQ")
    @SequenceGenerator(name = "DATAHORA_IDDATAHORA_SEQ", sequenceName = "DATAHORA_IDDATAHORA_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "IDDATAHORA")
    private Integer idDataHora;

    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;

    @Basic(optional = false)
    @Column(name = "DATAHORAINICIAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraInicial;

    @Basic(optional = false)
    @Column(name = "DATAHORAFINAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraFinal;  
    
    

}
