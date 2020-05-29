package com.br.poltergeist.produtos.api;

import com.br.poltergeist.produtos.constants.GeneroEnum;
import com.br.poltergeist.produtos.model.serie.RetornoSerie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public interface SerieApi {
    @GetMapping(value = "/serie",produces = {"applicatio/json"})
    ResponseEntity<RetornoSerie> getSeriesPorFiltro(@NotNull @Valid @RequestParam(value = "pagina", required = true, defaultValue = "1") Integer pagina,
                                                    @NotNull @Max(500) @Valid @RequestParam(value = "tamanhoPagina", required = true, defaultValue = "50") Integer tamanhoPagina,
                                                    @Valid @RequestParam(value = "idSerie", required = false) Long idSerie,
                                                    @Valid @RequestParam(value= "nomeSerie", required = false)String nomeSerie,
                                                    @Valid @RequestParam(value= "generos", required = false) List<GeneroEnum> generos,
                                                    @Valid @RequestParam(value= "dataLancamento", required = false) Date dataLancamento,
                                                    @Valid @RequestParam(value = "textoLivre", required =false) String textoLivre,
                                                    @Valid @RequestParam(value = "camposTextoLivre", required = false) List<String> camposTextoLivre,
                                                    @Valid @RequestParam(value = "ordenacao", required = false) List<String> ordenacao,
                                                    @Valid @RequestParam(value = "tipoRetorno", required = false) String tipoRetorno);
}
