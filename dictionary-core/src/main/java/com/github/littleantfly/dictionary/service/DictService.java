package com.github.littleantfly.dictionary.service;

import com.github.littleantfly.dictionary.module.DictModel;

/**
 *
 * @author jim
 */
public interface DictService {
    /**
     * 获取字典数据
     * @param data
     * @param fieldValue
     * @return
     */
    DictModel getDictData(String data, String fieldValue);
}
