package com.github.littleantfly.dictionary;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.DictSerializerModifier;
import com.github.littleantfly.dictionary.config.DictProperties;
import com.github.littleantfly.dictionary.service.DefaultDictServiceImpl;
import com.github.littleantfly.dictionary.service.DictService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
public class DictionaryAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "dictionary")
    public DictProperties dictProperties(){
        return new DictProperties();
    }

    @Bean
    @ConditionalOnMissingBean(DictService.class)
    public DefaultDictServiceImpl dictService(){
        return new DefaultDictServiceImpl();
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


}
