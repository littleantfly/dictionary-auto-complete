package com.github.littleantfly.dictionary;

import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.github.littleantfly.dictionary.service.DictService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author littl
 */
@Configuration
@AutoConfigureAfter(DictionaryAutoConfiguration.class)
@ConditionalOnBean(name = "dictFieldSerializerFilter", value = DictService.class)
public class FastJsonAutoConfiguration {

    @Resource
    private HttpMessageConverters httpMessageConverters;
    @Resource
    private SerializeFilter dictFieldSerializerFilter;


    @PostConstruct
    public void init(){
        httpMessageConverters.getConverters().stream().filter(c->c.getClass().isAssignableFrom(FastJsonHttpMessageConverter.class)).forEach(converter->{
            FastJsonHttpMessageConverter fastJsonHttpMessageConverter = (FastJsonHttpMessageConverter)converter;
            fastJsonHttpMessageConverter.getFastJsonConfig().setSerializeFilters(dictFieldSerializerFilter);
        });
    }
}
