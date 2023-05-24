package com.zsebank.filter;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.core.Ordered;

import java.util.List;

/**
 * @author micha
 */
@Component
@Slf4j
public class GlobalCacheRequestBodyFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();

        // 处理参数
        MediaType contentType = headers.getContentType();
        long contentLength = headers.getContentLength();
        if (contentLength > 0 && (MediaType.APPLICATION_JSON.equals(contentType))) {
            return readBody(exchange, chain);
        }

        return chain.filter(exchange);
    }


    private static final List<HttpMessageReader<?>> MESSAGE_READERS = HandlerStrategies.withDefaults().messageReaders();

    /**
     * ReadJsonBody
     * @param exchange exchange
     * @param chain chain
     * @return Mono<Void>
     */
    private Mono<Void> readBody(ServerWebExchange exchange, GatewayFilterChain chain) {

        return DataBufferUtils.join(exchange.getRequest().getBody()).flatMap(dataBuffer -> {
            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            DataBufferUtils.release(dataBuffer);
            Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
                DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
                DataBufferUtils.retain(buffer);
                return Mono.just(buffer);
            });

            ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(exchange.getRequest()) {
                @Override
                public @NotNull Flux<DataBuffer> getBody() {
                    return cachedFlux;
                }
            };

            ServerWebExchange mutatedExchange = exchange.mutate().request(mutatedRequest).build();

            return ServerRequest.create(mutatedExchange, MESSAGE_READERS).bodyToMono(String.class)
                    .doOnNext(objectValue -> log.debug("[GatewayContext]Read JsonBody:{}", objectValue)).then(chain.filter(mutatedExchange));
        });
    }
    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
