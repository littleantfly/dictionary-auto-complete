package com.github.littleantfly.dictionary;

import com.github.littleantfly.dictionary.model.DictCacheModel;
import com.github.littleantfly.dictionary.module.DictModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的缓存
 * @author littl
 */
public class DefaultDictCache {

    private static final Map<String, DictModel> CACHE = new ConcurrentHashMap<>();


    private static final String CACHE_KEY_SPACER = ":";

    /**
     * 初始化缓存数据
     */
    static {
        DictCacheModel cacheModel = new DictCacheModel();
        cacheModel.setPdata("USER_SEX");

        List<DictModel> children = new ArrayList<>();
        children.add(get("男", "1"));
        children.add(get("女", "2"));

        cacheModel.setChildren(children);


        List<DictCacheModel> cacheModelList = new ArrayList<>();
        cacheModelList.add(cacheModel);
        init(cacheModelList);
    }

    public static DictModel get(String key) {
        return CACHE.get(key);
    }

    private static DictModel get(String name, String data){
        DictModel dictModel = new DictModel();
        dictModel.setId(String.valueOf(new Random().nextInt(100)));
        dictModel.setPid(null);
        dictModel.setName(name);
        dictModel.setAlias1(name);
        dictModel.setAlias2(name);
        dictModel.setData(data);
        dictModel.setSeq(new Random().nextInt(10));
        dictModel.setRemark("");
        return dictModel;
    }


    public static void init(List<DictCacheModel> dataList){
        if(dataList == null || dataList.size() <= 0) {
            return;
        }
        dataList.stream().filter(data->data.getChildren()!=null && data.getChildren().size()>0)
                .forEach(data->{
            data.getChildren().forEach(d->{
                CACHE.put(data.getPdata()+CACHE_KEY_SPACER+d.getData(), d);
            });
        });

    }
}
