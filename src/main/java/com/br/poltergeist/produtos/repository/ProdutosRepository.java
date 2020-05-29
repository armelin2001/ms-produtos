package com.br.poltergeist.produtos.repository;

//@Component
public class ProdutosRepository {
    /*private static final String QUERY_FILME = "";


    public FilmeDashboard consultarFilmesPorMes(EntityManager em, Integer ano){
        Query query = em.createNativeQuery(QUERY_FILME);
        query.setParameter("ano",ano);

        @SuppressWarnings("unchecked")
        List<Object[]> result = (List<Object[]>) query.getResultList();

        return this.popularNovoFilmeDashboard(result);
    }
    public FilmeDashboard popularNovoFilmeDashboard(List<Object[]> result){
        FilmeDashboard filme = null;
        if(CollectionUtils.isNotEmpty(result)){
            filme = new FilmeDashboard();
            Object[] objects = result.get(0);
            //filme.setDataLancamento(Utils.getIntegerOrZero(objects[0]));
            //getGeneroEnumOrNull
            filme.setNomeFilme(Utils.getStringOrNull(objects[0]));
            filme.setGenero(Utils.getGeneroEnumOrNull(objects[1]));
        }
        return filme;
    }*/
}
