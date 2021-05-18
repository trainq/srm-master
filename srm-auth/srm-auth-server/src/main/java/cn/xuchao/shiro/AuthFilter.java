package cn.xuchao.shiro;

import cn.hutool.json.JSONUtil;
import cn.xuchao.base.BaseResponse;
import cn.xuchao.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
@Component
@Slf4j
public class AuthFilter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        String token = TokenUtil.getToken((HttpServletRequest) servletRequest);
        return new AuthToken(token);
    }

    /**
     * 拒绝所有请求
     *
     * @param request     请求
     * @param response    返回
     * @param mappedValue mappedValue
     * @return boolean
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return ((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name());
    }

    /**
     * 拒绝访问的请求，会调用onAccessDenied方法，onAccessDenied方法先获取 token，再调用executeLogin方法
     *
     * @param servletRequest  请求
     * @param servletResponse 返回
     * @return boolean
     * @throws Exception 异常
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = TokenUtil.getToken((HttpServletRequest) servletRequest);
        if (StringUtils.hasText(token)) {
            return executeLogin(servletRequest, servletResponse);
        }
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setCharacterEncoding("UTF-8");
        BaseResponse<Object> response = BaseResponse.error(HttpStatus.FORBIDDEN.value(), "用户没有权限");
        httpResponse.getWriter().print(JSONUtil.toJsonStr(response));
        return false;
    }

    /**
     * token失效时调用
     *
     * @param token           token
     * @param e               AuthenticationException
     * @param servletRequest  request
     * @param servletResponse response
     * @return boolean
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setCharacterEncoding("UTF-8");
        try {
            BaseResponse<Object> response = BaseResponse.error(HttpStatus.UNAUTHORIZED.value(), "登录已失效");
            httpResponse.getWriter().print(JSONUtil.toJsonStr(response));
        } catch (IOException ioException) {
            log.error("", ioException);
            ioException.printStackTrace();
        }
        return false;
    }

}

