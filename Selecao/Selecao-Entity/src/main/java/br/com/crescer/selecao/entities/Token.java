package br.com.crescer.selecao.entities;

import br.com.crescer.selecao.entities.enums.StatusToken;
import br.com.crescer.selecao.entities.enums.TipoToken;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author murillo.peteffi
 */
@Entity
@Table(name = "TOKEN", uniqueConstraints={@UniqueConstraint(columnNames = {"TOKEN"})})
@XmlRootElement
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "TOKEN_IDTOKEN_SEQ")
    @SequenceGenerator(name = "TOKEN_IDTOKEN_SEQ", sequenceName = "TOKEN_IDTOKEN_SEQ", allocationSize = 1)
    @Basic(optional = false)
    @Column(name = "IDTOKEN")
    private Integer idtoken;
    
    @Basic(optional = false)
    @Column(name = "IDPARACONFIRMAR")
    private Integer idparaconfirmar;
    
    @Basic(optional = false)
    @Column(name = "TOKEN")
    private String token;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "STATUS")
    private StatusToken status;
    
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "TIPO")
    private TipoToken tipo;

    public Token() {
    }

    public Token(Integer idtoken) {
        this.idtoken = idtoken;
    }

    public Token(Integer idparaconfirmar, String token, StatusToken status, TipoToken tipo) {
        this.idparaconfirmar = idparaconfirmar;
        this.token = token;
        this.status = status;
        this.tipo = tipo;
    }

    public int getIdtoken() {
        return idtoken;
    }

    public void setIdtoken(Integer idtoken) {
        this.idtoken = idtoken;
    }

    public int getIdparaconfirmar() {
        return idparaconfirmar;
    }

    public void setIdparaconfirmar(Integer idparaconfirmar) {
        this.idparaconfirmar = idparaconfirmar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public StatusToken getStatus() {
        return status;
    }

    public void setStatus(StatusToken status) {
        this.status = status;
    }

    public TipoToken getTipo() {
        return tipo;
    }

    public void setTipo(TipoToken tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Token[ idtoken=" + idtoken + " ]";
    }
    
}
