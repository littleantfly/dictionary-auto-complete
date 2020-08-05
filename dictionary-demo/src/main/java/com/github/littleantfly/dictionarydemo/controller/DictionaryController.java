package com.github.littleantfly.dictionarydemo.controller;

import com.github.littleantfly.dictionarydemo.vo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author littl
 */
@RestController
@RequestMapping("/dict/")
public class DictionaryController {

    @GetMapping("/{id}")
    public UserVO getUser(@PathVariable("id")String id){
        UserVO vo = new UserVO();
        vo.setUsername("Jim");
        vo.setSex(1);
        return vo;
    }

}
