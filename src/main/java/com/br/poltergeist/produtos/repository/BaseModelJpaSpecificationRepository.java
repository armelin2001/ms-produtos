package com.br.poltergeist.produtos.repository;
import com.br.poltergeist.produtos.model.BaseModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@NoRepositoryBean
public interface BaseModelJpaSpecificationRepository<T> extends JpaRepository<T,Long>, JpaSpecificationExecutor<T> {
    static final String PROPERTY_PATH_SEPARATOR = ".";
    static <T extends BaseModel> Specification<T> consultarPorFiltro(String textoLivre, List<String> camposTextoLivre) {
        return consultarPorFiltro(textoLivre, camposTextoLivre, true);
    }

    static <T extends BaseModel> Specification<T> consultarPorFiltro(String textoLivre, List<String> camposTextoLivre, boolean somenteLancamentos) {
        Specification<T> specification = getPorTextoLivre(textoLivre, camposTextoLivre);
        if (somenteLancamentos) {
            specification = specification.and(getLancamentos());
        }
        return specification;
    }

    static <T extends BaseModel> Specification<T> getLancamentos(){
        return ((root, cq, cb) -> cb.equal(root.get("lancamento"), Boolean.TRUE));
    }
    static <T extends BaseModel> Specification<T> getPorTextoLivre(String textoLivre, List<String> camposTextoLivre){
        return(root,cq,cb)->{
            Predicate predicate = null;
            if(StringUtils.isNotBlank(textoLivre)&&camposTextoLivre != null && !camposTextoLivre.isEmpty()){
                String textoLivreLike = "%" + StringUtils.upperCase(textoLivre) + "%";
                predicate = cb.disjunction();

                for (String campo : camposTextoLivre) {
                    Predicate campoPredicate = createPredicate(root, campo, textoLivreLike, cb);
                    predicate = cb.or(predicate, campoPredicate);
                }
            }
            return predicate;
        };
    }

    static <T extends BaseModel>Predicate createPredicate(Root<T> root, String campo, String textoLivreLike, CriteriaBuilder cb){
        From<?, ?> from = root;
        String campoLike = campo;
        if (StringUtils.contains(campo, PROPERTY_PATH_SEPARATOR)) {
            List<String> props = Arrays.asList(StringUtils.split(campo, PROPERTY_PATH_SEPARATOR));

            for (Iterator<String> iterator = props.iterator(); iterator.hasNext();) {
                String prop = iterator.next();
                if (!iterator.hasNext()) {
                    campoLike = prop;
                    break;
                }
                from = root.join(prop);
            }
        }

        return cb.like(translateAccentsUppercase(cb, from, campoLike), StringUtils.stripAccents(textoLivreLike));

    }

    static Expression<String> translateAccentsUppercase(CriteriaBuilder cb, From<?, ?> from, String campo) {
        Expression<String> upper = cb.upper(from.get(campo).as(String.class));
        return cb.function("TRANSLATE", String.class, upper, cb.literal("ÁÉÍÓÚÀÈÙÂÊÎÔÛËÏÖÜÇÑÄÃÅÌØÐÝÒÕÆ+"), cb.literal("AEIOUAEUAEIOUEIOUCNAAAIODYOOA "));
    }
}
