package com.br.poltergeist.produtos.exeception;

public class FilmeNotFoundException extends ApiException{
    public FilmeNotFoundException(Long idFilme){
        super("O filme de Id ("+idFilme+") não foi encontrado.");
    }
}
