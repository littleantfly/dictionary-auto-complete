package com.alibaba.fastjson.serializer;

import com.github.littleantfly.dictionary.annotation.Dict;
import com.github.littleantfly.dictionary.config.DictProperties;
import com.github.littleantfly.dictionary.module.DictModel;
import com.github.littleantfly.dictionary.service.DictService;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author littl
 */
public class DictFieldSerializerFilter  extends AfterFilter {


    private final DictService dictService;

    private final DictProperties dictProperties;

    public DictFieldSerializerFilter(DictService dictService, DictProperties dictProperties) {
        this.dictService = dictService;
        this.dictProperties = dictProperties;
    }

    @Override
    public void writeAfter(Object object) {
        try {
            Field[] declaredFields = object.getClass().getDeclaredFields();
            Map<String, Object> addProperty = new HashMap<>();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                Dict annotation = field.getAnnotation(Dict.class);
                if(annotation == null) {
                    continue;
                }
                String key = getKey(annotation, field.getName());
                DictModel dict = getDict(key, field.get(object));
                if(dict == null) {
                    continue;
                }
                super.writeKeyValue(key+dictProperties.getSuffix(), dict);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String getKey(Dict annotation, String name) {
        String key = annotation.data();
        if(StringUtils.isEmpty(key)) {
            key = name;
        }
        return key;
    }

    private DictModel getDict(String key, Object val) {
        if(val == null) {
            return null;
        }
        return dictService.getDictData(key, val.toString());
    }


}
