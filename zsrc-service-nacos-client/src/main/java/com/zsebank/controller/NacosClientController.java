package com.zsebank.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.zsebank.entity.AuthSignatureInfo;
import com.zsebank.service.NacosClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("nacos-client")
public class NacosClientController {

    private final NacosClientService nacosClientService;

    public NacosClientController(NacosClientService nacosClientService) {
        this.nacosClientService = nacosClientService;
    }

    @GetMapping("/service-instance")
    public List<ServiceInstance> logNacosClientInfo(
            @RequestParam(defaultValue = "nacos-client") String serviceId) {
        log.info("logNacosClientInfo: [{}]", serviceId);
        return nacosClientService.getNacosClientInfo(serviceId);
    }

    @PostMapping("/postTest1")
    public AuthSignatureInfo test1(@RequestBody String serviceId) {
        log.info("serviceId:[{}]",serviceId);
        return new AuthSignatureInfo(DateUtil.current(), RandomUtil.randomNumbers(6));
    }


}
