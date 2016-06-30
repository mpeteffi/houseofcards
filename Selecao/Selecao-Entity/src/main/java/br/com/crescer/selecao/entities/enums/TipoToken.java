package br.com.crescer.selecao.entities.enums;

/**
 *
 * @author michel.fernandes
 */
public enum TipoToken {
    INTERESSADO("INTERESSADO"),
    INSCRICAO("INSCRICAO"),
    ENTREVISTA("ENTREVISTA")
    ;
    private final String text;
    
    private TipoToken(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
