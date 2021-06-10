package cn.xuchao.filter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.xuchao.constant.SecurityConstant;
import cn.xuchao.exception.PermissionDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;

/**
 * @author xkhy
 */
@Configuration
@Slf4j
public class AccessGatewayFilter implements GlobalFilter, Ordered {

    private final static String[] IGNORE_URL = {"/srm/auth/oauth/token","/srm/auth/oauth/check_token","/srm/auth/oauth/authorize"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String url = request.getPath().value();
        if (!Arrays.asList(IGNORE_URL).contains(url)) {
            try {
                boolean accessible = accessible(request);
                if (!accessible) {
                    return Mono.error(new PermissionDeniedException());
                }
            } catch (HttpClientErrorException exception) {
                return Mono.error(exception);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 2;
    }

    private boolean accessible(ServerHttpRequest request) throws HttpClientErrorException {
        String token = request.getHeaders().getFirst(SecurityConstant.TOKEN_KEY);
        //todo 修改为feign调用
        URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8001/srm/auth/oauth/check_token").queryParam("token", token).build().encode().toUri();
        HttpEntity<?> entity = new HttpEntity<>(request.getHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        JSONObject resObject = JSONUtil.parseObj(responseEntity.getBody());
        boolean active = (boolean) resObject.get("active");
        if (!active) {
            //todo not active handler
        }
        return resObject.getJSONArray("authorities").stream().anyMatch(authority -> authority.toString().equals(request.getPath().value()));
    }

}
