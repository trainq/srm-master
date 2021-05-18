package cn.xuchao;

import cn.xuchao.base.BaseResponse;
import cn.xuchao.request.LoginRequest;
import cn.xuchao.response.LoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xuchao
 */
@FeignClient("srm-auth-server")
public interface AuthApi {

    /**
     * 登录请求
     *
     * @param request request
     * @return response
     */
    @PostMapping("/login")
    BaseResponse<LoginResponse> login(LoginRequest request);

}
