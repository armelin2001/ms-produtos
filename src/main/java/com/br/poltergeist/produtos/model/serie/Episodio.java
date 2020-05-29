package com.br.poltergeist.produtos.model.serie;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Table(name = "Episodio")
@Entity(name = "episodio")
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEpisodio;

    @Size(max=2000,message = "O tamnho do nome do episodio deve ser menor que 2000 caracteres.")
    private String nomeEpisodio;

    @NotNull(message = "A duração não pode ser nula.")
    @Digits(integer = 19,fraction = 0,message = "")
    private BigDecimal duracao;

    @NotNull(message = "O link do episodio nõa pode ser nulo.")
    @Size(max = 2000,message = "O tamanho do link do episodio deve ser menor que 2000 caracteres.")
    private String linkEpisodio;

    @NotNull(message = "O campo de descriç~so nõa pode ser nulo.")
    private String descricaoEpisodio;

    @ManyToOne(fetch = FetchType.LAZY)
    private Temporada temporada;

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public String getDescricaoEpisodio() {
        return descricaoEpisodio;
    }

    public void setDescricaoEpisodio(String descricaoEpisodio) {
        this.descricaoEpisodio = descricaoEpisodio;
    }

    public Long getIdEpisodio() {
        return idEpisodio;
    }

    public void setIdEpisodio(Long idEpisodio) {
        this.idEpisodio = idEpisodio;
    }

    public String getNomeEpisodio() {
        return nomeEpisodio;
    }

    public void setNomeEpisodio(String nomeEpisodio) {
        this.nomeEpisodio = nomeEpisodio;
    }

    public BigDecimal getDuracao() {
        return duracao;
    }

    public void setDuracao(BigDecimal duracao) {
        this.duracao = duracao;
    }

    public String getLinkEpisodio() {
        return linkEpisodio;
    }

    public void setLinkEpisodio(String linkEpisodio) {
        this.linkEpisodio = linkEpisodio;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }
        if(!(obj instanceof Episodio)){
            return false;
        }
        return idEpisodio !=null && idEpisodio.equals(((Episodio)obj).getIdEpisodio());
    }

}
