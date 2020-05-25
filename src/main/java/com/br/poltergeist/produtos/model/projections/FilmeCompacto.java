package com.br.poltergeist.produtos.model.projections;

import com.br.poltergeist.produtos.constants.GeneroEnum;

import java.util.Date;

public interface FilmeCompacto {
    Long getIdFilme();
    String getNomeFilme();
    GeneroEnum getGenero();
    Date getDataLancamento();
}
