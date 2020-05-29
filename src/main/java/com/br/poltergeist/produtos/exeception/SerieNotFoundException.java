package com.br.poltergeist.produtos.exeception;

public class SerieNotFoundException extends ApiException{
    public SerieNotFoundException(Long idSerie){super("A serie com o Id ("+idSerie+") não foi encontrado.");}
}
