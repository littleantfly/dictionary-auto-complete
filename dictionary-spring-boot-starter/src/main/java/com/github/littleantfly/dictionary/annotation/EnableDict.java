package com.github.littleantfly.dictionary.annotation;

import com.github.littleantfly.dictionary.DictionaryAutoConfiguration;
import com.github.littleantfly.dictionary.FastJsonAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author littl
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({DictionaryAutoConfiguration.class, FastJsonAutoConfiguration.class})
public @interface EnableDict {
}
