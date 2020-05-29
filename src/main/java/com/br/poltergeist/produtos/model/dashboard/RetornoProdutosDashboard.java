package com.br.poltergeist.produtos.model.dashboard;

import com.br.poltergeist.produtos.model.Retorno;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class RetornoProdutosDashboard extends Retorno {
    private List<ProdutosDashboard> lista;

    public List<ProdutosDashboard> getLista() {
        return lista;
    }

    public void setLista(List<ProdutosDashboard> lista) {
        this.lista = lista;
    }

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
