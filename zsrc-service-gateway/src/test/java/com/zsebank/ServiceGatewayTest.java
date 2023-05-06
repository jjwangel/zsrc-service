package com.zsebank;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ServiceGatewayTest {
    @Autowired
    private SignatureInfoConfig signatureInfoConfig;

    @Test
    public void contextLoad() {
        log.info(this.signatureInfoConfig.getSignatureInfoKey());
        log.info(this.signatureInfoConfig.getPublicKey());
        log.info(this.signatureInfoConfig.getExpireTime().toString());
        log.info(String.valueOf(System.currentTimeMillis()));
        log.info(RandomUtil.randomNumbers(6));
    }

}
