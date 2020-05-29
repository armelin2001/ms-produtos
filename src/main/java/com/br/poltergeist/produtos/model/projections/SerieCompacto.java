package com.br.poltergeist.produtos.model.projections;

import com.br.poltergeist.produtos.constants.GeneroEnum;

public interface SerieCompacto {
    Long getIdSerie();
    String getNomeSerie();
    GeneroEnum getGenero();
}
