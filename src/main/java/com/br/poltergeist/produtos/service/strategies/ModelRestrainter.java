package com.br.poltergeist.produtos.service.strategies;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public interface ModelRestrainter<T> {
    static final Logger logger = LoggerFactory.getLogger(ModelRestrainter.class);
    default T restraintModelFields(T model){
        return restraintModelFields(model,null);
    }
    default T restraintModelFields(T model, T modelBase){
        List<String> acceptableFields = getAcceptableModelFields(model);
        if(acceptableFields == null){
            throw new IllegalStateException("Defina os campos aceitáveis em " + this.getClass().getName());
        }
        Field[] fields = FieldUtils.getAllFields(model.getClass());
        for(Field field: fields){
            if(!acceptableFields.contains(field.getName())){
                try {
                    Object value = modelBase == null ? null : PropertyUtils.getProperty(modelBase, field.getName());
                    PropertyUtils.setProperty(model, field.getName(), value);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    logger.error(e.getMessage(), e);
                    throw new IllegalArgumentException("Erro ao executar setter da propriedade " + field.getName());
                }
            }
        }
        return model;
    }
    default T resetModelFields(T model) {
        List<String> modelFieldsToReset = getModelFieldsToReset(model);

        if (modelFieldsToReset == null) {
            throw new IllegalStateException("Defina os campos a serem resetados em " + this.getClass().getName());
        }

        Field[] fields = FieldUtils.getAllFields(model.getClass());
        for (Field field : fields) {
            if (modelFieldsToReset.contains(field.getName())) {
                try {
                    if ((field.getType() == List.class)) {
                        List<?> value = (List<?>) PropertyUtils.getProperty(model, field.getName());
                        if (value != null) {
                            value.clear();
                        } else {
                            PropertyUtils.setProperty(model, field.getName(), new ArrayList<>());
                        }
                    } else {
                        PropertyUtils.setProperty(model, field.getName(), null);
                    }
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    logger.error(e.getMessage(), e);
                    throw new IllegalArgumentException("Erro ao executar setter da propriedade " + field.getName());
                }
            }
        }

        return model;
    }
    List<String> getAcceptableModelFields(T model);

    default List<String> getModelFieldsToReset(T model) {
        return Collections.emptyList();
    }
}
