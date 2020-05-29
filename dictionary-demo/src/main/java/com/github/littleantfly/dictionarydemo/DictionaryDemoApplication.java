package com.github.littleantfly.dictionarydemo;

import com.github.littleantfly.dictionary.annotation.EnableDict;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author littl
 */
@EnableDict
@SpringBootApplication
public class DictionaryDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictionaryDemoApplication.class, args);
    }

}
