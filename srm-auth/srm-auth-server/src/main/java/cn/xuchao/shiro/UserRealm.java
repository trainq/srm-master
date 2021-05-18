package cn.xuchao.shiro;

import cn.hutool.json.JSONUtil;
import cn.xuchao.constant.SecurityConstant;
import cn.xuchao.entity.User;
import cn.xuchao.util.RedisUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Resource
    private RedisUtil redisUtil;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println(JSONUtil.toJsonStr(principalCollection.getPrimaryPrincipal()));
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = ((AuthToken) authenticationToken).getToken();
        User user = (User) redisUtil.get(SecurityConstant.ONLINE_PREFIX + token);
        if (user == null) {
            throw new CredentialsException();
        }
        redisUtil.expire(token, 30, TimeUnit.MINUTES);
        return new SimpleAuthenticationInfo(user, token, this.getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AuthToken;
    }

}
