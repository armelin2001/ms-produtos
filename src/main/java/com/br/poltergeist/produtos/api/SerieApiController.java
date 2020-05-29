package com.br.poltergeist.produtos.api;

import com.br.poltergeist.produtos.constants.GeneroEnum;
import com.br.poltergeist.produtos.model.serie.RetornoSerie;
import com.br.poltergeist.produtos.service.serieService.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Controller
public class SerieApiController implements SerieApi{
    @Autowired
    private SerieService serieService;
    @Override
    public ResponseEntity<RetornoSerie> getSeriesPorFiltro(@NotNull @Valid Integer pagina, @NotNull @Max(500) @Valid Integer tamanhoPagina,
                                                           @Valid Long idSerie, @Valid String nomeSerie, @Valid List<GeneroEnum> generos,
                                                           @Valid Date dataLancamento, @Valid String textoLivre, @Valid List<String> camposTextoLivre,
                                                           @Valid List<String> ordenacao, @Valid String tipoRetorno) {
        RetornoSerie retorno = this.serieService.getSeriesPorFiltro(pagina,tamanhoPagina,idSerie,nomeSerie,generos,dataLancamento,camposTextoLivre,textoLivre,ordenacao,tipoRetorno);
        return ResponseEntity.ok(retorno);
    }
}
