package com.br.poltergeist.produtos.model.converter;

import javax.persistence.AttributeConverter;

public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if(attribute == null) {
            return null;
        }
        return attribute ? "S" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        if(dbData == null) {
            return null;
        }
        return dbData.equalsIgnoreCase("S");
    }

}
