package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.github.littleantfly.dictionary.config.DictProperties;
import com.github.littleantfly.dictionary.service.DictService;

/**
 * DictSerializerModifier
 *
 * @author Jim
 * @version 1.0
 * @date 2019/10/21 10:47
 */
public class DictSerializerModifier extends BeanSerializerModifier {

    private final DictService dictService;
    private final DictProperties dictProperties;

    public DictSerializerModifier(DictService dictService, DictProperties dictProperties){
        this.dictService = dictService;
        this.dictProperties = dictProperties;
    }

    @Override
    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        return new DictSerializer((JsonSerializer<Object>) serializer, null, dictService, dictProperties);
    }
}
