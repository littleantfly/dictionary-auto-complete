package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.littleantfly.dictionary.annotation.Dict;
import com.github.littleantfly.dictionary.config.DictProperties;
import com.github.littleantfly.dictionary.module.DictModel;
import com.github.littleantfly.dictionary.service.DictService;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * DictSerializer
 *
 * @author Jim
 * @version 1.0
 * @date 2019/10/21 17:53
 */
public class DictSerializer extends JsonSerializer<Object> implements ContextualSerializer {

    private final JsonSerializer<Object> serializer;

    private final BeanProperty property;

    private final DictService dictService;

    private final DictProperties dictProperties;


    public DictSerializer(JsonSerializer<Object> serializer, BeanProperty property, DictService dictService, DictProperties dictProperties){
        this.serializer = serializer;
        this.property = property;
        this.dictService = dictService;
        this.dictProperties = dictProperties;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        serializer.serialize(value, gen, provider);
        if (property != null) {
            if(value == null || StringUtils.isEmpty(value.toString())) {
                return;
            }
            if(dictService == null) {
                return;
            }
            DictModel dict = getDict(value);
            if(dict == null) {
                return;
            }
            gen.writeObjectField(property.getName()+dictProperties.getSuffix(), dict);
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        if (property != null && hasDictAnnotation(property)) {
            return new DictSerializer(serializer, property, dictService, dictProperties);
        }
        return serializer;
    }

    private boolean hasDictAnnotation(BeanProperty property) {
        return (property.getAnnotation(Dict.class) != null);
    }



    private DictModel getDict(Object value){
        Dict dictAnn = property.getAnnotation(Dict.class);
        String data = dictAnn.data();
        if(StringUtils.isEmpty(data)) {
            data = property.getName();
        }
        return dictService.getDictData(data, value.toString());
    }



}
