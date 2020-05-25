package com.br.poltergeist.produtos.service;

import com.br.poltergeist.produtos.constants.GeneroEnum;
import com.br.poltergeist.produtos.constants.TipoRetornoEnum;
import com.br.poltergeist.produtos.exeception.FilmeNotFoundException;
import com.br.poltergeist.produtos.model.filme.Filme;
import com.br.poltergeist.produtos.model.filme.RetornoFilme;
import com.br.poltergeist.produtos.model.projections.FilmeCompacto;
import com.br.poltergeist.produtos.model.projections.FilmeDetalhado;
import com.br.poltergeist.produtos.repository.FilmeRepository;
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
import java.util.Optional;

@Service
public class FilmeService extends BaseService{
    @Autowired
    private FilmeRepository filmeRepository;

    public RetornoFilme getFilmePorFiltro(@NotNull Integer pagina,@NotNull @Max(500) @Valid Integer tamanhoPagina, Long idFilme,
                    List<GeneroEnum> generos, String descricaoFilme, Date dataLancamento, String textoLivre,List<String> camposTextoLivre,@Valid List<String> ordenacao,
                                          @Valid String tipoRetorno){

        Pageable pageable = this.getPage(pagina, tamanhoPagina, ordenacao);

        TipoRetornoEnum tipo = TipoRetornoEnum.getByName(tipoRetorno);
        Class<?> projectionClass = tipo == TipoRetornoEnum.DETALHADO ? FilmeDetalhado.class : FilmeCompacto.class;

        Specification<Filme> spec = FilmeRepository.consultaPorFiltro(idFilme,generos,descricaoFilme,dataLancamento,textoLivre,camposTextoLivre);

        Page<?> page = this.filmeRepository.findAll(spec,projectionClass,pageable);//specification,projectionClass,"graph.Filme" , EntityGraphType.FETCH, pageable

        RetornoFilme retorno = new RetornoFilme();
        retorno.setLista(Utils.copyProperties(page.getContent(), Filme.class));
        return this.popularRetornoSucesso(retorno,page);
    }
    private Filme buscarFilmePorId(Long idFilme){
        Optional<Filme> optional = this.filmeRepository.findById(idFilme);
        if(!optional.isPresent()){
            throw new FilmeNotFoundException(idFilme);
        }
        return optional.get();
    }
    public RetornoFilme criarFilme(){
        return null;
    }
    
}
