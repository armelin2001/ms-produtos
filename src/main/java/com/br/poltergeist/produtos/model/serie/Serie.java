package com.br.poltergeist.produtos.model.serie;

import com.br.poltergeist.produtos.constants.GeneroEnum;
import com.br.poltergeist.produtos.model.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "Serie")
@Entity(name = "serie")
public class Serie extends BaseModel {

    @Id
    @NotNull(message = "O ID do da serie não deve ser nulo.")
    @SequenceGenerator(name = "SEQ_SERIE", sequenceName = "SEQ_SERIE", initialValue = 1,allocationSize = 1)
    @GeneratedValue(generator = "SEQ_SERIE", strategy = GenerationType.SEQUENCE)
    private Long idSerie;

    @NotNull(message = "O campo nomeSerie não pode ser nulo.")
    @Size(max = 256, message = "O campo nomeSerie não pode ser maior que 256 caracteres.")
    private String nomeSerie;

    @Enumerated(EnumType.STRING)
    @Column(length = 32)
    private GeneroEnum genero;

    @NotNull(message = "O campo dataLancamento não pode ser nulo.")
    @JsonFormat(pattern="dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date dataLancamento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Temporada> temporadas = new ArrayList<>();

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Long getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Long idSerie) {
        this.idSerie = idSerie;
    }

    public String getNomeSerie() {
        return nomeSerie;
    }

    public void setNomeSerie(String nomeSerie) {
        this.nomeSerie = nomeSerie;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return false;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(idSerie).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Serie other = (Serie) obj;
        return new EqualsBuilder().append(idSerie, other.idSerie).isEquals();
    }
}
