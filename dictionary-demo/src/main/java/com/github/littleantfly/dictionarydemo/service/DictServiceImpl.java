package com.github.littleantfly.dictionarydemo.service;

import com.github.littleantfly.dictionary.annotation.Dict;
import com.github.littleantfly.dictionary.module.DictModel;
import com.github.littleantfly.dictionary.service.DictService;
import org.springframework.stereotype.Component;

/**
 * 获取字典数据方法，此处若注册为Spring bean 则会覆盖默认Service
 * @author littl
 */
//@Component
public class DictServiceImpl implements DictService {

    @Override
    public DictModel getDictData(Dict dict, String data) {
        String pdata = dict.pdata();
        //TODO 根据参数获取字典数据
        DictModel dictModel = new DictModel();
        dictModel.setName("JIM");
        dictModel.setData(data);
        dictModel.setSeq(1);
        return dictModel;
    }

}
