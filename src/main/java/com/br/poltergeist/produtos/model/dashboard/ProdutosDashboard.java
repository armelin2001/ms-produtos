package com.br.poltergeist.produtos.model.dashboard;

import com.br.poltergeist.produtos.model.filme.FilmeDashboard;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonInclude(Include.NON_NULL)
public class ProdutosDashboard {
    //falta inclusão de series para completar a dashboard
    private Integer ano;

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public FilmeDashboard getFilmeDashboard() {
        return filmeDashboard;
    }

    public void setFilmeDashboard(FilmeDashboard filmeDashboard) {
        this.filmeDashboard = filmeDashboard;
    }

    private FilmeDashboard filmeDashboard;
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
