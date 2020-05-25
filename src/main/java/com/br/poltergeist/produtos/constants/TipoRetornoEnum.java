package com.br.poltergeist.produtos.constants;

import com.br.poltergeist.produtos.exeception.ApiException;
import org.apache.commons.lang3.StringUtils;

public enum TipoRetornoEnum {
    COMPACTO,
    DETALHADO;
    public static TipoRetornoEnum getByName(String tipoRetorno) {
        if (StringUtils.isNotBlank(tipoRetorno)) {
            for (TipoRetornoEnum tipo : TipoRetornoEnum.values()) {
                if (StringUtils.equalsIgnoreCase(tipoRetorno, tipo.name())) {
                    return tipo;
                }
            }

            throw new ApiException("Tipo de retorno inválido: " + tipoRetorno);
        }
        return TipoRetornoEnum.COMPACTO;
    }
}
