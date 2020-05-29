package com.br.poltergeist.produtos.model.projections;

import com.br.poltergeist.produtos.constants.GeneroEnum;
import com.br.poltergeist.produtos.model.serie.Temporada;

import java.util.Date;
import java.util.List;

public interface SerieDetalhado {
    Long getIdSerie();
    String getNomeSerie();
    GeneroEnum getGenero();
    Date getDataLancamento();
    List<Temporada> getTemporadas();
}
