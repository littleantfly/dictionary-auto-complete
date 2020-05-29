package com.github.littleantfly.dictionary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Dict
 *
 * @author Jim
 * @version 1.0
 * @date 2019/10/21 13:30
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {

    String pdata() default "";

}
