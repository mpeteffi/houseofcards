package br.com.crescer.selecao.entities;

import br.com.crescer.selecao.entities.enums.StatusCandidato;
import java.io.Serializable;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author murillo.peteffi
 */
@Entity
@Table(name = "CANDIDATO", uniqueConstraints={@UniqueConstraint(columnNames = {"EMAIL"})})
@XmlRootElement
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "CANDIDATO_IDCANDIDATO_SEQ")
    @SequenceGenerator(name = "CANDIDATO_IDCANDIDATO_SEQ", sequenceName = "CANDIDATO_IDCANDIDATO_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "IDCANDIDATO")
    private Integer idCandidato;
    
    @Size(max = 100, message = "Numero maximo de caracteres: 100")
    @NotBlank(message="Campo Nome deve ser preenchido")
    @Basic(optional = false)
    @Column(name = "NOME")
    private String nome;
    
    @Size(max = 100, message = "Numero maximo de caracteres: 100")
    @NotBlank(message="Campo Email deve ser preenchido")
    @Email(message="Digite um endereço de email válido")
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    
    @Size(max = 100, message = "Numero maximo de caracteres: 100")
    @NotBlank(message="Campo Instituição de ensino deve ser preenchido")
    @Basic(optional = false)
    @Column(name = "INSTITUICAOENSINO")
    private String instituicaoEnsino;    
    
    @Size(max = 100, message = "Numero maximo de caracteres: 100")
    @NotBlank(message="Campo Curso deve ser preenchido")
    @Basic(optional = false)
    @Column(name = "CURSO")
    private String curso;
    
    @Size(max = 30, message = "Numero maximo de caracteres: 30")
    @Pattern(regexp="^\\d{4}\\/(0?[1-9]|1[0-2])",message="Informe a previsão de formatura no formato indicado")
    @NotBlank(message="Campo Previsão de formatura deve ser preenchido")
    @Basic(optional = false)
    @Column(name = "PREVISAOFORMATURA")
    private String previsaoFormatura;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "STATUS")
    private StatusCandidato status;

    public Candidato() {
    }

    public Candidato(Integer idcandidato) {
        this.idCandidato = idcandidato;
    }

    public Candidato(String nome, String email, String instituicaoensino, String curso, String previsaoformatura, StatusCandidato status) {
        this.nome = nome;
        this.email = email;
        this.instituicaoEnsino = instituicaoensino;
        this.curso = curso;
        this.previsaoFormatura = previsaoformatura;
        this.status = status;
    }
    
    
    public Candidato(Integer idcandidato, String nome, String email, String instituicaoensino, String curso, String previsaoformatura, StatusCandidato status) {
        this.idCandidato = idcandidato;
        this.nome = nome;
        this.email = email;
        this.instituicaoEnsino = instituicaoensino;
        this.curso = curso;
        this.previsaoFormatura = previsaoformatura;
        this.status = status;
      }

    public Integer getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(Integer idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInstituicaoEnsino() {
        return instituicaoEnsino;
    }

    public void setInstituicaoEnsino(String instituicaoEnsino) {
        this.instituicaoEnsino = instituicaoEnsino;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPrevisaoFormatura() {
        return previsaoFormatura;
    }

    public void setPrevisaoFormatura(String previsaoFormatura) {
        this.previsaoFormatura = previsaoFormatura;
    }

    public StatusCandidato getStatus() {
        return status;
    }

    public void setStatus(StatusCandidato status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Candidato[ idcandidato=" + idCandidato + " ]";
    }
    
}
