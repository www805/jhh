package com.jiehuihui.gateway.filter;

import com.jiehuihui.gateway.util.VersionJhh;
import com.jiehuihui.gateway.utils.Code;
import com.jiehuihui.gateway.utils.DateUtil;
import com.jiehuihui.gateway.utils.RResult;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

@Component
public class MyGateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        List<String> authorization = request.getHeaders().get("Authorization");

        String path = request.getURI().getPath();

        //设置headers
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
        httpHeaders.add("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");

        if (path.indexOf("/admin") != -1 && null != authorization && authorization.size() > 0) {
            String id = authorization.get(0);
            httpHeaders.add("Authorization", id);
            return chain.filter(exchange);
        }
        if(path.indexOf("/login") != -1){
            return chain.filter(exchange);
        }

        JSONObject object = new JSONObject();
        object.put("version", VersionJhh.v1);
        object.put("actioncode", Code.FAIL.toString());
        object.put("data", "");
        object.put("endtime", DateUtil.getDateAndMinute());
        object.put("message", "请登录后访问");

        byte[] dataBytes=object.toString().getBytes();
        DataBuffer bodyDataBuffer = response.bufferFactory().wrap(dataBytes);
        return response.writeWith(Mono.just(bodyDataBuffer));

            //如果没有携带，生成新的sessionId
//            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
//            return exchange.getResponse().setComplete();


    }

    //优先级，0 最高
    @Override
    public int getOrder() {
        return 0;
    }
}
