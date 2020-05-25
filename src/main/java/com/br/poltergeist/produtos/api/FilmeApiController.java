package com.br.poltergeist.produtos.api;

import com.br.poltergeist.produtos.constants.GeneroEnum;
import com.br.poltergeist.produtos.model.filme.RetornoFilme;
import com.br.poltergeist.produtos.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Controller
public class FilmeApiController implements FilmeApi{
    @Autowired
    private FilmeService filmeService;
    @Override
    public ResponseEntity<RetornoFilme> getFilmesPorFiltro(@NotNull @Valid Integer pagina, @NotNull @Max(500) @Valid Integer tamanhoPagina, @Valid Long idFilme,
                                                           @Valid List<GeneroEnum> generos, @Valid String descricaoFilme, @Valid Date dataLancamento,
                                                           @Valid String textoLivre, @Valid List<String> camposTextoLivre, @Valid List<String> ordenacao, @Valid String tipoRetorno) {
        RetornoFilme retorno = this.filmeService.getFilmePorFiltro(pagina, tamanhoPagina, idFilme, generos, descricaoFilme, dataLancamento, textoLivre, camposTextoLivre, ordenacao, tipoRetorno);
        return ResponseEntity.ok(retorno);
    }
}
