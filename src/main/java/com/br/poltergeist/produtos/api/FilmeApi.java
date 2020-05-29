package com.br.poltergeist.produtos.api;

import com.br.poltergeist.produtos.constants.GeneroEnum;
import com.br.poltergeist.produtos.model.filme.Filme;
import com.br.poltergeist.produtos.model.filme.RetornoFilme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
//Falta operações de Crud
public interface FilmeApi {
    @GetMapping(value = "/filme", produces = {"application/json"})
    ResponseEntity<RetornoFilme> getFilmesPorFiltro(@NotNull @Valid @RequestParam(value = "pagina", required = true, defaultValue = "1") Integer pagina,
                                                    @NotNull @Max(500) @Valid @RequestParam(value = "tamanhoPagina", required = true, defaultValue = "50") Integer tamanhoPagina,
                                                    @Valid @RequestParam(value = "idFilme", required = false) Long idFilme,
                                                    @Valid @RequestParam(value = "generos", required = false) List<GeneroEnum> generos,
                                                    @Valid @RequestParam(value = "descricaoFilme", required = false) String descricaoFilme,
                                                    @Valid @RequestParam(value = "dataLancamento", required = false)Date dataLancamento,
                                                    @Valid @RequestParam(value = "textoLivre", required =false) String textoLivre,
                                                    @Valid @RequestParam(value = "camposTextoLivre", required = false) List<String> camposTextoLivre,
                                                    @Valid @RequestParam(value = "ordenacao", required = false) List<String> ordenacao,
                                                    @Valid @RequestParam(value = "tipoRetorno", required = false) String tipoRetorno);

    @PostMapping(value = "/filme", produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<RetornoFilme> criarFilme(@RequestBody Filme filme);

    @DeleteMapping(value = "/filme")
    ResponseEntity<RetornoFilme> deletarFilme();



}
