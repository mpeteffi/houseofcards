package br.com.crescer.selecao.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    private int idtoken;
    
    @Basic(optional = false)
    @Column(name = "IDPARACONFIRMAR")
    private int idparaconfirmar;
    
    @Basic(optional = false)
    @Column(name = "TOKEN")
    private String token;
    
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    
    @Basic(optional = false)
    @Column(name = "TIPO")
    private String tipo;

    public Token() {
    }

    public Token(int idtoken) {
        this.idtoken = idtoken;
    }

    public Token(int idtoken, int idparaconfirmar, String token, String status, String tipo) {
        this.idtoken = idtoken;
        this.idparaconfirmar = idparaconfirmar;
        this.token = token;
        this.status = status;
        this.tipo = tipo;
    }

    public int getIdtoken() {
        return idtoken;
    }

    public void setIdtoken(int idtoken) {
        this.idtoken = idtoken;
    }

    public int getIdparaconfirmar() {
        return idparaconfirmar;
    }

    public void setIdparaconfirmar(int idparaconfirmar) {
        this.idparaconfirmar = idparaconfirmar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "br.com.crescer.selecao.entities.Token[ idtoken=" + idtoken + " ]";
    }
    
}
