package com.br.poltergeist.produtos.service.serieService;

import com.br.poltergeist.produtos.constants.GeneroEnum;
import com.br.poltergeist.produtos.constants.TipoRetornoEnum;
import com.br.poltergeist.produtos.model.projections.SerieCompacto;
import com.br.poltergeist.produtos.model.projections.SerieDetalhado;
import com.br.poltergeist.produtos.model.serie.RetornoSerie;
import com.br.poltergeist.produtos.model.serie.Serie;
import com.br.poltergeist.produtos.repository.serieRepository.SerieRepository;
import com.br.poltergeist.produtos.service.BaseService;
import com.br.poltergeist.produtos.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Service
public class SerieService extends BaseService {
    @Autowired
    private SerieRepository serieRepository;

    public RetornoSerie getSeriesPorFiltro(@NotNull Integer pagina, @NotNull @Max(500) @Valid Integer tamanhoPagina, Long idSerie,
                                          String nomeSerie, List<GeneroEnum> generos, Date dataLancamento, List<String> camposTextoLivre,
                                          String textoLivre, @Valid List<String> ordenacao, @Valid String tipoRetorno){
        Pageable pageable = this.getPage(pagina,tamanhoPagina, ordenacao);

        TipoRetornoEnum tipo = TipoRetornoEnum.getByName(tipoRetorno);
        Class<?> projectionClass = tipo == TipoRetornoEnum.DETALHADO ? SerieDetalhado.class : SerieCompacto.class;

        Specification<Serie> spec = SerieRepository.consultaPorFiltro(idSerie,nomeSerie,generos,dataLancamento,camposTextoLivre,textoLivre);

        Page<?> page = this.serieRepository.findAll(spec,projectionClass,pageable);

        RetornoSerie retorno = new RetornoSerie();
        retorno.setLista(Utils.copyProperties(page.getContent(), Serie.class));
        return this.popularRetornoSucesso(retorno,page);
    }
}
