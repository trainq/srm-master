package cn.xuchao.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import cn.xuchao.AuthApi;
import cn.xuchao.constant.SecurityConstant;
import cn.xuchao.exception.PermissionDeniedException;
import cn.xuchao.exception.TokenExpireException;
import cn.xuchao.exception.TokenNotFoundException;
import cn.xuchao.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author xuchao
 */
@Component
@Slf4j
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Resource
    private AuthApi authApi;

    @Resource
    private RedisUtil redisUtil;

    private final String[] ignoreUrl = {"/srm/auth/login", "/srm/auth/getCaptcha", "/srm/auth/logout"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        log.info(JSONUtil.toJsonStr(request));
        String rawPath = request.getURI().getRawPath();
        if (Arrays.stream(ignoreUrl).noneMatch(Predicate.isEqual(rawPath))) {
            List<String> tokens = request.getHeaders().get(SecurityConstant.TOKEN_KEY);
            if (CollectionUtil.isEmpty(tokens)) {
                return Mono.error(new TokenNotFoundException());
            }
            String token = tokens.get(0);
            Object o = redisUtil.get(SecurityConstant.ONLINE_PREFIX + token);
            if (o == null) {
                return Mono.error(new TokenExpireException());
            }
            Set<String> urlList = JSONUtil.parseObj(o).getJSONArray("menuList").stream().map(menu -> JSONUtil.parseObj(menu).get("url").toString()).collect(Collectors.toSet());
            if (!urlList.contains(rawPath)) {
                return Mono.error(new PermissionDeniedException());
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
