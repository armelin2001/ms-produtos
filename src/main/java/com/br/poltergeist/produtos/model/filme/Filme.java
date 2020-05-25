package com.br.poltergeist.produtos.model.filme;

import com.br.poltergeist.produtos.constants.GeneroEnum;
import com.br.poltergeist.produtos.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Table
@Entity
public class Filme extends BaseModel {


    @Id
    @NotNull(message = "O id do filme não pode ser nulo")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFilme;

    @Column(name = "NOME_FILME")
    @Size(max = 256)
    @NotNull(message = "O nome do filme é obrigatorio")
    private String nomeFilme;

    @NotNull(message = "O link do filme não pode ser nulo")
    @Column(name = "LINK_FILME")
    private String linkFilme;

    @NotNull(message = "A duração do filme é obrigatoria")
    @Digits(integer = 7, fraction = 2, message = "O formato do campo duracao está inválido.")
    private BigDecimal duracao;

    @Enumerated(EnumType.STRING)
    @Column(length = 32)
    private GeneroEnum genero;

    @Column(name = "DESCRICAO_FILME")
    @Size(max = 256, message = "O campo descricaoFilme não pode ser maior que 256 caracteres.")
    private String descricaoFilme;

    @NotNull(message = "O campo data de lançamento não pode ser nulo")
    @Column(name = "DATA_LACAMENTO")
    private Date dataLancamento;
    public Long getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Long idFilme) {
        this.idFilme = idFilme;
    }


    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public String getLinkFilme() {
        return linkFilme;
    }

    public void setLinkFilme(String linkFilme) {
        this.linkFilme = linkFilme;
    }

    public BigDecimal getDuracao() {
        return duracao;
    }

    public void setDuracao(BigDecimal duracao) {
        this.duracao = duracao;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    public String getDescricaoFilme() {
        return descricaoFilme;
    }

    public void setDescricaoFilme(String descricaoFilme) {
        this.descricaoFilme = descricaoFilme;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return idFilme==null;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder(17, 37).append(idFilme).toHashCode();
    }
    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj==null){
            return false;
        }
        if(obj.getClass()!= getClass()){
            return false;
        }
        Filme other = (Filme) obj;
        return new EqualsBuilder().append(idFilme, other.idFilme).isEquals();
    }
}
