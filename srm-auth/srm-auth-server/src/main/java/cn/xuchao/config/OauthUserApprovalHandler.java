package cn.xuchao.config;

import cn.xuchao.dto.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;

/**
 * @author xkhy
 */
public class OauthUserApprovalHandler extends TokenStoreUserApprovalHandler {

    @Override
    public boolean isApproved(AuthorizationRequest request, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Long userId = securityUser.getUserId();
        //todo 用户 客户端对应表 查看是否有权限
        return true ;
    }

}
