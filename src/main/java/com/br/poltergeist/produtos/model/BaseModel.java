package com.br.poltergeist.produtos.model;

import com.br.poltergeist.produtos.model.converter.BooleanToStringConverter;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseModel {

    @Column(updatable = false)
    @JsonFormat(pattern="dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date dataInclusao = new Date(System.currentTimeMillis());

    @Convert(converter = BooleanToStringConverter.class)
    @Column(length = 1)
    private Boolean lancamento;
    protected BaseModel() {
    }
    public abstract boolean isNew();

    public Date getDataInclusao() {
        return dataInclusao = new Date(System.currentTimeMillis());
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Boolean getLancamento() {
        return lancamento;
    }

    public void setLancamento(Boolean lancamento) {
        this.lancamento = lancamento;
    }
}
