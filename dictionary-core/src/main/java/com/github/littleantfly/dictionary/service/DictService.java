package com.github.littleantfly.dictionary.service;

import com.github.littleantfly.dictionary.annotation.Dict;
import com.github.littleantfly.dictionary.module.DictModel;

/**
 *
 * @author jim
 */
public interface DictService {
    /**
     * 获取字典数据
     * @param dict
     * @param data
     * @return
     */
    DictModel getDictData(Dict dict, String data);
}
