package com.br.poltergeist.produtos.service;

import com.br.poltergeist.produtos.exeception.ApiException;
import com.br.poltergeist.produtos.model.Retorno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;

import java.util.List;

public abstract class BaseService {

    private static final String SORT_SEPARATOR = "-";
    private static final String SORT_ASC = "asc";
    private static final String SORT_DESC = "desc";
    // pega a pagina sem ordenação
    protected Pageable getPage(Integer pagina, Integer tamanhoPagina){
        return  getPage(pagina,tamanhoPagina,null);
    }

    protected Pageable getPage(Integer pagina, Integer tamanhoPagina, List<String> ordenacao){
        Sort sort = Sort.unsorted();
        if(ordenacao != null){
            this.validateOrderFields(ordenacao);
            for(String ordem : ordenacao){
                String[] ordArr = ordem.split(SORT_SEPARATOR);
                String propriedade = ordArr[0];
                String tipoOrdem = ordArr[1];

                if (SORT_ASC.equalsIgnoreCase(tipoOrdem)) {
                    sort = sort.and(Sort.by(new Sort.Order(Direction.ASC, propriedade).ignoreCase()));
                } else if (SORT_DESC.equalsIgnoreCase(tipoOrdem)) {
                    sort = sort.and(Sort.by(new Sort.Order(Direction.DESC, propriedade).ignoreCase()));
                }
            }
        }
        return PageRequest.of(pagina,tamanhoPagina,sort);
    }
    private void validateOrderFields(List<String> ordenacao){
        for (String ordem : ordenacao) {
            String[] ordArr = ordem.split(SORT_SEPARATOR);
            if (ordArr.length == 2) {
                String tipoOrdem = ordArr[1];

                if (!SORT_ASC.equalsIgnoreCase(tipoOrdem) && !SORT_DESC.equalsIgnoreCase(tipoOrdem)) {
                    throw new ApiException("O tipo de ordenação '" + tipoOrdem + "' é inválido");
                }
            } else {
                throw new ApiException("O parâmetro de ordenação '" + ordem + "' é inválido");
            }
        }
    }
    protected <R extends Retorno> R popularRetornoSucesso(R retorno) {
        retorno.setCodigo(HttpStatus.OK.value());
        retorno.setMensagem("Operação executada com sucesso");
        return retorno;
    }

    protected <R extends Retorno> R popularRetornoSucesso(R retorno, Page<?> page) {
        retorno = this.popularRetornoSucesso(retorno);
        retorno.setPagina(page.getPageable().getPageNumber());
        retorno.setTamanhoPagina(page.getNumberOfElements());
        retorno.setTotalRegistros((int) page.getTotalElements());
        return retorno;
    }
}
