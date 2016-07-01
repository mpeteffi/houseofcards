/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.selecao.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Michel
 */
@Entity
@Table(name = "GRUPODEPROVAS")
@XmlRootElement
public class GrupoDeProvas implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "GRUPODEPROVAS_IDGRUPODEPROVAS_SEQ")
    @SequenceGenerator(name = "GRUPODEPROVAS_IDGRUPODEPROVAS_SEQ", sequenceName = "GRUPODEPROVAS_IDGRUPODEPROVAS_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "IDGRUPODEPROVAS")
    private Integer idGrupoDeProvas;    
    
    @JoinColumn(name = "IDDATAHORA", referencedColumnName = "IDDATAHORA")
    @ManyToOne(optional = false)
    private DataHora dataDeProvas;    
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy="grupoDeProvas")
    private List<Entrevista> entrevistasDeUmGrupo;    
}
