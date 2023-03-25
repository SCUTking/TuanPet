package com.tuanpet.gateway.Filters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuanpet.common.utils.JwtUtil;
import com.tuanpet.common.utils.RedisConstants;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * 使用网关进行token校验拦截
 * 注意这里需要创建这个对象所以需要依赖注入创建
 *
 */
@Component
@Slf4j
public class TokenFilter implements GlobalFilter, Ordered {
    /**
     * 制定好放行的路径,不用进行token检查的
     * */

    public static final List<String> ALLOW_URL = Arrays.asList("/user/login","/association/save","" +
            "/association/update","/association/deleteById");

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /**
         * 一般和前端约定好 一般把token放在请求头里面 一般key为Authorization （授权的意思） value 为bearer token
         * 拿到请求url判断是否为登录url是就放行，不是继续操作
         * 拿到请求头
         * 拿到token
         * 校验
         * 放行/拦截
         * */
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        log.info("path:"+path);
        //不能用**表示全部后台管理的路径，用path.contains("/api/")区分
        //放行后台管理系统的请求
        if(ALLOW_URL.contains(path)||path.contains("/api/")){
            //放行
            return chain.filter(exchange);
        }
        //检查
        HttpHeaders headers = request.getHeaders();
        List<String> authorization = headers.get("Authorization");
        if(!CollectionUtils.isEmpty(authorization)){
            //如果authorization集合不为空
            //获取第一个为token
            String token = authorization.get(0);
            //判断给token里面的值是不是空字符串
            if(StringUtils.hasText(token)){
                //不为空
                //约定好的有前缀的bearer token
                String realToken = token.replaceFirst("bearer ","");
                //判断realToken是否为空以及redis数据库是否包含
                //解析token，获取key
                String openId = JwtUtil.getOpenIdByToken(realToken);
                String redisKey=RedisConstants.LOGIN_USER_KEY+openId;

                if(StringUtils.hasText(realToken)&&redisTemplate.hasKey(redisKey)){
                    return chain.filter(exchange);
                }
            }
        }
        //如果不符合就不给访问
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("content-type","application/json;charset=utf-8");
        HashMap<String,Object> map = new HashMap<>(4);
        //返回401
        map.put("code", HttpStatus.UNAUTHORIZED.value());
        map.put("msg","未授权");
        ObjectMapper objectMapper = new ObjectMapper();
        byte [] bytes = new byte[0];
        try {
            bytes=objectMapper.writeValueAsBytes(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        DataBuffer wrap = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(wrap));
    }

    @Override
    public int getOrder() {
        //配置优先级 越小越先执行
        return -1;
    }

}

