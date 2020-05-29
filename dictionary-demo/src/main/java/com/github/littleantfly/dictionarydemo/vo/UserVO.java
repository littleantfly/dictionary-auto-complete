package com.github.littleantfly.dictionarydemo.vo;

import com.github.littleantfly.dictionary.annotation.Dict;
import lombok.Data;

/**
 * @author littl
 */
@Data
public class UserVO {

    private String username;

    @Dict(pdata = "USER_SEX")
    private Integer sex;


}
