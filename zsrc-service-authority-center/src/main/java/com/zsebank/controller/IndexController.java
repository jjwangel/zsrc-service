package com.zsebank.controller;

import com.alibaba.fastjson2.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(tags = "首页模块")
@RestController
public class IndexController {

    @ApiImplicitParam(name = "name",value = "姓名",required = true)
    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    public Object sayHi(@RequestParam(value = "name")String name){
        log.info("request name: [{}]",name);
        Map<String,String> tmp =new HashMap<>();
        tmp.put("name",name);
        return tmp;
    }
}