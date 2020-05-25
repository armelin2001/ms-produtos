package com.br.poltergeist.produtos.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GeneroEnum {
    ACAO("Ação"),
    ANIMACAO("Animação"),
    AVENTURA("Aventura"),
    COMEDIA("Comédia"),
    COMEDIA_ROMANTICA("Comédia romantica"),
    COMEDIA_DRAMATICA("Comédia dramatica"),
    COMEDIA_ACAO("Comédia de ação"),
    DOCUMENTARIO("Documentario"),
    DRAMA("Drama"),
    ESPIONAGEM("Espionagem"),
    FAROESTE("Faroeste"),
    FANTASIA_CIENTIFICA("Fantasia científica"),
    GUERRA("Guerra"),
    MUSICAL("Musical"),
    POLICIAL("Policial"),
    ROMANCE("Romance"),
    SUSPENSE("Supense"),
    TERROR("Terror");
    private final String descricao;

    public String getDescricao() {
        return descricao;
    }

    private GeneroEnum(String descricao){
        this.descricao = descricao;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(name());
    }
    @JsonCreator
    public static GeneroEnum fromValue(String text){
        for(GeneroEnum b: GeneroEnum.values()){
            if(String.valueOf(b.name()).equals(text)){
                return b;
            }
        }
        return null;
    }
}
