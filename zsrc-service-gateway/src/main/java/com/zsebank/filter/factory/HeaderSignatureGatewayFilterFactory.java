package com.zsebank.filter.factory;

import com.zsebank.filter.HeaderSignatureGatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @author micha
 */
@Component
public class HeaderSignatureGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return new HeaderSignatureGatewayFilter();
    }
}
