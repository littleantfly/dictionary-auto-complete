package com.github.littleantfly.dictionary.service;

import com.github.littleantfly.dictionary.DefaultDictCache;
import com.github.littleantfly.dictionary.annotation.Dict;
import com.github.littleantfly.dictionary.module.DictModel;


/**
 *
 * @author littl
 */
public class DefaultDictServiceImpl implements DictService {

    @Override
    public DictModel getDictData(Dict dict, String data) {
        String pdata = dict.pdata();
        String key = pdata+":"+data;
        return DefaultDictCache.get(key);
    }

}
