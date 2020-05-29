package com.br.poltergeist.produtos.repository.filmeRepository;

import com.br.poltergeist.produtos.constants.GeneroEnum;
import com.br.poltergeist.produtos.model.BaseModel;
import com.br.poltergeist.produtos.model.filme.Filme;
import com.br.poltergeist.produtos.repository.BaseModelJpaSpecificationRepository;
import io.micrometer.core.lang.Nullable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import java.util.Date;
import java.util.List;

@Repository
public interface FilmeRepository extends BaseModelJpaSpecificationRepository<Filme>, JpaSpecificationExecutorWithProjection<Filme>{
    static <T extends BaseModel> Specification<T> consultaPorFiltro(@Nullable Long idFilme, @Nullable List<GeneroEnum> generos,
            @Nullable String descricaoFilme, @Nullable Date dataLancamento, @Nullable String textoLivre, @Nullable List<String> camposTextoLivre) {
        Specification<T> specification = BaseModelJpaSpecificationRepository.consultarPorFiltro(textoLivre,camposTextoLivre,false);
        specification = specification.and(FilmeRepository.getPorIdFilme(idFilme));
        specification = specification.and(FilmeRepository.getPorGenero(generos));
        specification = specification.and(FilmeRepository.getDescricaoFime(descricaoFilme));
        specification = specification.and(FilmeRepository.getDataLancamento(dataLancamento));
        return specification;
    }
    static <T extends BaseModel> Specification<T> getPorIdFilme(Long idFilme){
        return (root, cq, cb) -> idFilme != null ? cb.equal(root.get("idFilme"), idFilme) : null;
    }
    static <T extends BaseModel> Specification<T> getPorGenero(List<GeneroEnum> generos){
        return (root, cq, cb) -> generos != null && !generos.isEmpty() ? root.get("genero").in(generos) : null;
    }
    static <T extends BaseModel> Specification<T> getDescricaoFime(String descricaoFilme){
        return (root, cq, cb) -> descricaoFilme != null ? cb.equal(root.get("descricaoFilme"), descricaoFilme) : null;
    }
    static <T extends BaseModel> Specification<T> getDataLancamento(Date dataLancamento){
        return  (root, cq, cb) -> dataLancamento != null ? cb.equal(root.get("dataLancamento"), dataLancamento) : null;
    }
}
