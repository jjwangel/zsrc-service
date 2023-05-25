package com.zsebank.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;

/**
 * @author micha
 * HTTP 请求头部携带 Signature 验证过滤器
 * */
@Slf4j
public class HeaderSignatureGatewayFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("-------------HeaderSignatureGatewayFilter-------------");

        // 1、获取Headers中的signature数据
        if (exchange.getRequest().getHeaders().containsKey("signature"))
        {
            String signature = exchange.getRequest().getHeaders().getFirst("signature");
            log.info("signature:[{}]",signature);


            return chain.filter(exchange);
        } else {
            return signatureErrorResponse(exchange.getResponse());
        }

        // 从 HTTP Header 中寻找 key 为 token, value 为 imooc 的键值对
        // String name = exchange.getRequest().getHeaders().getFirst("token");
//        MultiValueMap<String,String> queryParams = exchange.getRequest().getQueryParams();
//
//        log.info("request query params:[{}]",queryParams.getFirst("serviceId"));
//        if ("imooc".equals(name)) {
//
//        }

//        log.error("request not find token");
//        // 标记此次请求没有权限, 并结束这次请求
//        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE + 1;
    }

    private Mono<Void> signatureErrorResponse(ServerHttpResponse httpResponse){
        if (!httpResponse.getHeaders().containsKey("Content-Type")) {
            httpResponse.getHeaders().add("Content-Type", "application/json");
        }
        //此处无法触发全局异常处理，手动返回
        DataBuffer buffer = httpResponse.bufferFactory().wrap(("{\n"
                + "  \"code\": \"-1\","
                + "  \"message\": \"验签失败！\","
                + "  \"data\": []"
                + "}").getBytes(StandardCharsets.UTF_8));
        httpResponse.setStatusCode(HttpStatus.OK);
        return httpResponse.writeWith(Mono.just(buffer));
    }
}
