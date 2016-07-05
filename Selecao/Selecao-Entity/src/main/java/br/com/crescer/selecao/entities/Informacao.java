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
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author murillo.peteffi
 */
@Entity
@Table(name = "INFORMACAO", uniqueConstraints={@UniqueConstraint(columnNames = {"IDCANDIDATO","IDPROCESSOSELETIVO"})})
@XmlRootElement
public class Informacao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "INFORMACAO_IDINFORMACAO_SEQ")
    @SequenceGenerator(name = "INFORMACAO_IDINFORMACAO_SEQ", sequenceName = "INFORMACAO_IDINFORMACAO_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "IDINFORMACAO")
    private Integer idInformacao;
    
    @Size(max = 20, message = "Numero maximo de caracteres: 20")
    @NotBlank(message="Campo Telefone deve ser preenchido")
    @Basic(optional = false)
    @Column(name = "TELEFONE")
    private String telefone;
    
    @DateTimeFormat(pattern="dd/MM/YYYY")
    @NotNull(message="Campo Data de nascimento deve ser preenchido")
    @Past(message="Informe uma data válida")
    @Basic(optional = false)
    @Column(name = "DATANASCIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;
    
    @Size(max = 100, message = "Numero maximo de caracteres: 100")
    @NotBlank(message="Campo Cidade deve ser preenchido")
    @Basic(optional = false)
    @Column(name = "CIDADE")
    private String cidade;
    
    @Size(max = 100, message = "Numero maximo de caracteres: 100")
    @NotBlank(message="Campo Url linkedin deve ser preenchido")
    @Basic(optional = false)
    @Column(name = "URLLINKEDIN")
    private String urlLinkedin;
    
    @NotEmpty(message="Campo Senha deve ser preenchido")
    @Size(min=6, max=100,message="Sua senha deve conter entre 6 e 100 dígitos")
    @Basic(optional = false)
    @Column(name = "SENHA")
    private String senha;
    
    @Valid
    @JoinColumn(name = "IDCANDIDATO", referencedColumnName = "IDCANDIDATO")
    @ManyToOne(optional = false)
    private Candidato idCandidato;
    
    @JoinColumn(name = "IDPROCESSOSELETIVO", referencedColumnName = "IDPROCESSOSELETIVO")
    @ManyToOne(optional = false)
    private Processoseletivo idProcessoSeletivo;

    public Informacao() {
    }

    public Informacao(Integer idinformacao) {
        this.idInformacao = idinformacao;
    }

    public Informacao(String telefone, Date datanascimento, String cidade, String urllinkedin, String senha, Candidato candidato, Processoseletivo processoseletivo) {
        this.telefone = telefone;
        this.dataNascimento = datanascimento;
        this.cidade = cidade;
        this.urlLinkedin = urllinkedin;
        this.senha = senha;
        this.idCandidato = candidato;
        this.idProcessoSeletivo = processoseletivo;
    }

    public Informacao(String telefone, Date datanascimento, String cidade, String urllinkedin, Candidato candidato) {
         this.telefone = telefone;
         this.dataNascimento = datanascimento;
         this.cidade = cidade;
         this.urlLinkedin = urllinkedin;
         this.idCandidato = candidato;
     }

    public Integer getIdInformacao() {
        return idInformacao;
    }

    public void setIdInformacao(Integer idInformacao) {
        this.idInformacao = idInformacao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUrlLinkedin() {
        return urlLinkedin;
    }

    public void setUrlLinkedin(String urlLinkedin) {
        this.urlLinkedin = urlLinkedin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Candidato getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Candidato idCandidato) {
        this.idCandidato = idCandidato;
    }

    public Processoseletivo getIdProcessoSeletivo() {
        return idProcessoSeletivo;
    }

    public void setIdProcessoSeletivo(Processoseletivo idProcessoSeletivo) {
        this.idProcessoSeletivo = idProcessoSeletivo;
    }

    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Informacao[ idinformacao=" + idInformacao + " ]";
    }
    
}
