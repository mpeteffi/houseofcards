package br.com.crescer.selecao.entities.enums;

/**
 *
 * @author michel.fernandes
 */
public enum StatusCandidato {
    INICIAL("INICIAL"),
    INTERESSADO("INTERESSADO"),
    NOTIFICADO("NOTIFICADO"),
    AGUARDANDOCONTATO("AGUARDANDO CONTATO"),
    ENTREVISTAAGENDADA("ENTREVISTA AGENDADA"),
    SEMINTERESSE("SEM INTERESSE"),        
    ENTREVISTADO("ENTREVISTADO"),
    EMANALISE("EM ANALISE"),
    NAOCLASSIFICADO("NAO CLASSIFICADO"),
    PRESELECIONADO("PRE SELECIONADO")
    ;
    private final String text;
    
    private StatusCandidato(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
