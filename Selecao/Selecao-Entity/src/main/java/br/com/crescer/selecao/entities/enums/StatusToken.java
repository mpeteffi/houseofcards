package br.com.crescer.selecao.entities.enums;

/**
 *
 * @author michel.fernandes
 */
public enum StatusToken {
    PENDENTE("PENDENTE"),
    FINAL("FINAL")
    ;
    private final String text;
    
    private StatusToken(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
