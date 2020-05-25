package com.br.poltergeist.produtos.model;

import com.br.poltergeist.produtos.model.converter.BooleanToStringConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseModel {

    @Column(updatable = false)
    private Instant dataInclusao;

    @Convert(converter = BooleanToStringConverter.class)
    @Column(length = 1)
    private Boolean lancamento;
    protected BaseModel() {
    }
    public abstract boolean isNew();

    public Instant getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Instant dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Boolean getLancamento() {
        return lancamento;
    }

    public void setLancamento(Boolean lancamento) {
        this.lancamento = lancamento;
    }
}
