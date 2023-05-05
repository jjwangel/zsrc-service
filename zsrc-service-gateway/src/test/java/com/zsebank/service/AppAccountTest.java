package com.zsebank.service;

import com.alibaba.fastjson2.JSON;
import com.zsebank.entity.AppAccount;
import com.zsebank.service.impl.AppAccountServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class AppAccountTest {

    @Autowired
    private AppAccountServiceImpl appAccountService;
    @Test
    public void getAppAccount(){
        AppAccount appAccount = appAccountService.getById(1);
        log.info(JSON.toJSONString(appAccount));
    }

    @Test
    public void getAppAccountList(){
        List<AppAccount> appAccountList = appAccountService.list();
        log.info(JSON.toJSONString(appAccountList));
    }

}
