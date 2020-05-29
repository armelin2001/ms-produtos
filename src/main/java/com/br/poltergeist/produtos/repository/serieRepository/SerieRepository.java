package com.br.poltergeist.produtos.repository.serieRepository;

import com.br.poltergeist.produtos.constants.GeneroEnum;
import com.br.poltergeist.produtos.model.BaseModel;
import com.br.poltergeist.produtos.model.serie.Serie;
import com.br.poltergeist.produtos.repository.BaseModelJpaSpecificationRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import java.util.Date;
import java.util.List;

@Repository
public interface SerieRepository extends BaseModelJpaSpecificationRepository<Serie>, JpaSpecificationExecutorWithProjection<Serie> {
    static <T extends BaseModel>Specification<T> consultaPorFiltro(Long idSerie, String nomeSerie, List<GeneroEnum> generos, Date dataLancamento, List<String> camposTextoLivre, String textoLivre){
        Specification<T> specification = BaseModelJpaSpecificationRepository.consultarPorFiltro(textoLivre,camposTextoLivre,false);
        specification = specification.and(SerieRepository.getPorIdSerie(idSerie));
        specification = specification.and(SerieRepository.getPorNomeSerie(nomeSerie));
        specification = specification.and(SerieRepository.getPorGenero(generos));
        specification = specification.and(SerieRepository.getPorDataLancamento(dataLancamento));
        return specification;
    }
    static <T extends BaseModel> Specification<T> getPorIdSerie(Long idSerie){
        return (root, cq, cb) -> idSerie != null ? cb.equal(root.get("idSerie"), idSerie) : null;
    }
    static <T extends BaseModel> Specification<T> getPorNomeSerie(String nomeSerie){
        return (root, cq, cb) -> nomeSerie != null ? cb.equal(root.get("nomeSerie"),nomeSerie):null;
    }
    static <T extends BaseModel> Specification<T> getPorGenero(List<GeneroEnum> generos){
        return (root, cq, cb) -> generos != null && !generos.isEmpty() ? root.get("genero").in(generos) : null;
    }
    static <T extends BaseModel> Specification<T> getPorDataLancamento(Date dataLancamento){
        return (root, cq, cb) -> dataLancamento != null ? cb.equal(root.get("dataLancamento"), dataLancamento) : null;
    }

}
