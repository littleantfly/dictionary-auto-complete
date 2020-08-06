package com.github.littleantfly.dictionary;


import com.alibaba.fastjson.serializer.DictFieldSerializerFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.DictSerializerModifier;
import com.github.littleantfly.dictionary.config.DictProperties;
import com.github.littleantfly.dictionary.service.DictService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 *
 * @author jim
 */
@Configuration
public class DictionaryAutoConfiguration  {

    @Bean
    @ConfigurationProperties(prefix = "dictionary")
    public DictProperties dictProperties(){
        return new DictProperties();
    }

    @Bean
    @ConditionalOnBean(DictService.class)
    @ConditionalOnProperty(prefix = "dictionary", name = "enabled", havingValue = "true", matchIfMissing= true)
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter(ObjectMapper jacksonObjectMapper, DictService dictService) {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(jacksonObjectMapper);
        ObjectMapper mapper = converter.getObjectMapper();
        mapper.setSerializerFactory(mapper.getSerializerFactory().withSerializerModifier(new DictSerializerModifier(dictService, dictProperties())));
        return converter;
    }


    @Bean
    @ConditionalOnBean(DictService.class)
    @ConditionalOnProperty(prefix = "dictionary", name = "enabled", havingValue = "true", matchIfMissing= true)
    public SerializeFilter dictFieldSerializerFilter(DictProperties dictProperties, DictService dictService){
        return new DictFieldSerializerFilter(dictService, dictProperties);
    }


    public void init(FastJsonHttpMessageConverter fastJsonHttpMessageConverter, SerializeFilter dictFieldSerializerFilter){
        fastJsonHttpMessageConverter.getFastJsonConfig().setSerializeFilters(dictFieldSerializerFilter);
    }

}
