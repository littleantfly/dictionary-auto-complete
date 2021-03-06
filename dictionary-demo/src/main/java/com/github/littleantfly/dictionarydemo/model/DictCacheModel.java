package com.github.littleantfly.dictionarydemo.model;

import com.github.littleantfly.dictionary.module.DictModel;

import java.util.List;

/**
 * @author littl
 */
public class DictCacheModel {

    private String pdata;

    private List<DictModel> children;

    public String getPdata() {
        return pdata;
    }

    public void setPdata(String pdata) {
        this.pdata = pdata;
    }

    public List<DictModel> getChildren() {
        return children;
    }

    public void setChildren(List<DictModel> children) {
        this.children = children;
    }
}
