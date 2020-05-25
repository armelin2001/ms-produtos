package com.br.poltergeist.produtos.model.projections;

import com.br.poltergeist.produtos.constants.GeneroEnum;

import java.math.BigDecimal;
import java.util.Date;
//
public interface FilmeDetalhado {
    Long getIdFilme();
    String getNomeFilme();
    String getLinkFilme();
    BigDecimal getDuracao();
    GeneroEnum getGenero();
    String getDescricaoFilme();
    Date getDataLancamento();
}
