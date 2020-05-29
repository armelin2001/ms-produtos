package com.br.poltergeist.produtos.service.filmeService;

import com.br.poltergeist.produtos.constants.GeneroEnum;
import com.br.poltergeist.produtos.constants.TipoRetornoEnum;
import com.br.poltergeist.produtos.exeception.ApiException;
import com.br.poltergeist.produtos.exeception.FilmeNotFoundException;
import com.br.poltergeist.produtos.model.filme.Filme;
import com.br.poltergeist.produtos.model.filme.RetornoFilme;
import com.br.poltergeist.produtos.model.projections.FilmeCompacto;
import com.br.poltergeist.produtos.model.projections.FilmeDetalhado;
import com.br.poltergeist.produtos.repository.filmeRepository.FilmeRepository;
import com.br.poltergeist.produtos.service.BaseService;
import com.br.poltergeist.produtos.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FilmeService extends BaseService {
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

    @Transactional
    public RetornoFilme criarFilme(Filme filme){
        return this.salvarFilme(filme);
    }

    private RetornoFilme salvarFilme(Filme filme){
        Filme filmeBase = null;
        if(!filme.isNew()){
            filmeBase = this.buscarFilmePorId(filme.getIdFilme());
        }
        filme.setLancamento(Boolean.TRUE);

        filme = this.filmeRepository.save(filme);
        RetornoFilme retorno = new RetornoFilme();
        retorno.setLista(Arrays.asList(filme));

        return this.popularRetornoSucesso(retorno);
    }
    private Filme buscarFilmePorId(Long idFilme){
        Optional<Filme> optional = this.filmeRepository.findById(idFilme);
        if(!optional.isPresent()){
            throw new FilmeNotFoundException(idFilme);
        }
        return optional.get();
    }

    private void deletarFilme(Long idFilme){
        try{
           filmeRepository.delete(buscarFilmePorId(idFilme));
        }catch(Exception e){
            throw new ApiException("Erro ao deletar o filme pelo ID " + idFilme);
        }
    }
}
