package cn.xuchao.service;

import cn.xuchao.request.LoginRequest;
import cn.xuchao.response.LoginResponse;

/**
 * @author xuchao
 */
public interface AuthService {
    /**
     * 登录
     * @param request request
     * @return token
     */
    LoginResponse login(LoginRequest request);

    /**
     * 登出
     * @param token token
     */
    void logout(String token);
}
