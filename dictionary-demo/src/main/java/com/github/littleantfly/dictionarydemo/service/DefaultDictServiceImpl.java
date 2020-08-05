package com.github.littleantfly.dictionarydemo.service;

import com.github.littleantfly.dictionary.module.DictModel;
import com.github.littleantfly.dictionary.service.DictService;
import com.github.littleantfly.dictionarydemo.DefaultDictCache;
import org.springframework.stereotype.Service;


/**
 *
 * @author littl
 */
@Service
public class DefaultDictServiceImpl implements DictService {

    @Override
    public DictModel getDictData(String data, String fieldValue) {
        String key = data+":" + fieldValue;
        return DefaultDictCache.get(key);
    }

}
