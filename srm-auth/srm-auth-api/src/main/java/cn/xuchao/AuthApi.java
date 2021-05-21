package cn.xuchao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

/**
 * @author xuchao
 */
@FeignClient("srm-auth-server")
public interface AuthApi {

    /**
     * 获取token
     *
     * @param principal  principal
     * @param parameters parameters
     * @return /
     * @throws HttpRequestMethodNotSupportedException e
     */
    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST)
    ResponseEntity<Object> postAccessToken(Principal principal, @RequestParam
            Map<String, String> parameters) throws HttpRequestMethodNotSupportedException;

    /**
     * 检查token
     *
     * @param value value
     * @return /
     */
    @RequestMapping(value = "/oauth/check_token")
    Map<String, ?> checkToken(@RequestParam("token") String value);

}
