package com.br.poltergeist.produtos.exeception;

public class ApiException extends RuntimeException{
    public ApiException(String msg){
        super(msg);
    }
}
