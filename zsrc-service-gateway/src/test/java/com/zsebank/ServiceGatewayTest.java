package com.zsebank;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.zsebank.entity.AuthSignatureInfo;
import com.zsebank.service.impl.SignatureParseServiceImpl;
import com.zsebank.utils.SignatureUtil;
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
    private SignatureParseServiceImpl signatureParseService;

    @Test
    public void contextLoad() {

    }

    @Test
    public void sdkTest() throws Exception {
        String privateKey="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCQxCcS5u3GexTVveuRr8/mB7J4r9tBv5VI/HHMVcpmpa/waNRXfTErik6qezJb8uF5EYnkqgZzWdNhW6/FPm8TXNvdYVTkjlCmTAUF0Xe0RnEnCdlfkfIBcYUSEbRMPFp8CtbXzC2tmpMZ30j4Su4d21wFw5gebCR2n7AHuBIUKDYCPgQZeWdv++Cr66PVY6DOgVZb32bFgb1TxhLqL0fuJ334m0uoQReC03bcxq2NU2vFdWNtbe1Xqktx++apuABbfOyiu/ZEzS/e9QezrtHHjV9iQHJgCnAe3IAbynSyM3G2T78PGIoiTunvkJEqm9gt+M8ip54NguzcDzrvI2A7AgMBAAECggEAZygfX8zGqqSSGC0Za9sIHjZl6rIFXziRvEyHGsAXVkXCtWE9zoobO5d2ruzS2cqc+JSS2ip+Jjxf8ARRoA/5Pqbw1TPCNoti1N1exuKYlhGMF/FgwEVTWcv2swWCoMBxCer/ZQ7NjeOT5t/BM4zRWN0zGbhi8WrGIPFxkk6MM2fOYTrwBI49zPpyZCu7roaVm7AYGLSIzMDsB5OVKjpn77huk1MNGwPjP5+PI+6WiElB/BHDnTmXH1Isns5E9KAosyBboyu9huTUTDkpZpdI7nn+xAh7E01jbj7ASoA3dKLijEEdTTfdC89CvYQZ7QQBpafdb1abvq/mTHk2CxaaoQKBgQDgU8jIXg71z+HCyCD4XtD6NYgW/f/o3a8jLjsvpxin/mn0/Q6WUY4EvUdQzUihzhW8rYYpuq7hAtXeQLLEOudcmoyfvlQKwec7LQuoBXr7GHqY5Cl2eb6bMqAE0zjh6qJFrWMeCqmbLrriCyN5XLr9ZIVvTbt0v/SlmeCVcoKs/wKBgQClNKrFz3d/4eHa40wxzHifWWAb8yv80CdeTfp4nsSxYP4IRXURh/yEW3LDhcIYKfvS+yU72kY/1VaFWdXMe/y0wEwHUd6yHQiVU+x04qumw2feNOajsAu5OxMRsnlmG/O3oMs/TATsyFBv7rz/rZzjPeA0ORBTBfcmlb/bQCvAxQKBgGT8ctP4u+Ve+zQTofwNuygYdzZajB0JxzEgO9a883WVK5/1V97FwhhSGUu/zmXA/7obp90bRGTZQOw31gJOvh7LGGlFZlIdbnoOUkQGi5GEOJShiiTis7LsPGtPpzjUxs51OryocigBBnKCLysQWsF9wjAUH0J0s67lMdw1W6yDAoGAdnHjOj171NETSAcM2utZVA4VLRTUZcarYx6PRKm4JeB/YVjDDVWtAww5pNsihIadSO/NCzN8iEmhZaw58Ai47rKsD9dvzc2pGL752felz8NrldHhxlvym9Qx4M9P4JiLzQzYi8IqCcW4fffxw3Iq+HWlaGu9O3kccKqOylggxm0CgYEA3U56l5JGf0PmQsZk2lx7t25mGouezhyFywOsEGCI43mxaS53/UlweSSQUIuBQ5M/of5G2lTzjoMQcTPTDg0jJyxgCy4SpSxWH/Rp/ghsk4IE/nigQHh936jIfOyOQmzT4NPWotYeR8p6orBUL/WmmP/g98sI0/pmM4pl701A7N8=";
        log.info(StrUtil.format("signature:[{}]",SignatureUtil.generateSignature(privateKey)));
    }

    @Test
    public void signatureParseServiceTest() throws Exception {
        String signature="eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJkYXRlIjoxNjg0NzQwNDA5OTUyLCJub25jZSI6IjE1ODkzNCIsImV4cCI6MTY4NDc0MDcwOX0.B4bQrtVkSILjgxiEUsbbLJO-s_h4Qwv1sjZwSwxJxLQVlLeBZYtc4O2B-Lfg5-Gny8-IR9WCyNnxwCo_Xx6JH5d_Z2LIrXVk3cS8D0lItwa8NFOarNcmViYP1hNuJ5DpXHGuFNt07dR6IGJxE3DHGFo4xXJT_ocfOc4RII9U8AeVHajfwIFO4jNvNibXXxU2rHEOp18ZmA5E2fdYJQY3aQO5NsxpBP0Y6L0bISYpOr3UJ3y_6h78PpMLhQsJ0z4QqAzHO7TLb6Z5lKAoJ3VPjKFoRxUYKprsjDF1vYysJ4i12FdRKZZoEjmCxY2Ue1vaRiEATJDfu5HxXIA-JE2nZg";

        AuthSignatureInfo authSignatureInfo = signatureParseService.signatureParse("dev",signature);
        log.info(JSON.toJSONString(authSignatureInfo));
    }

}
