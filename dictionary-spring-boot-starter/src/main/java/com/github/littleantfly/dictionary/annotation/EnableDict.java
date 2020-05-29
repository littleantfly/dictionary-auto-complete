package com.github.littleantfly.dictionary.annotation;

import com.github.littleantfly.dictionary.DictionaryAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author littl
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(DictionaryAutoConfiguration.class)
public @interface EnableDict {
}
