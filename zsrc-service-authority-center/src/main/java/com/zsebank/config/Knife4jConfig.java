package com.zsebank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Knife4jConfig {
    @Bean(value = "authority-center")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("# Authority Center RESTful APIs")
                        .termsOfServiceUrl("http://localhost:8200/doc.html")
                        .contact(new Contact("jianjiawen", "http://localhost:8200/doc.html", "93108@qq.com"))
                        .version("v1.0")
                        .build())
                //分组名称
                .groupName("v1.0")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.zsebank.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
