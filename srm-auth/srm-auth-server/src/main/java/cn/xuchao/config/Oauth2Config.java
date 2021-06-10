package cn.xuchao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author xkhy
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2Config extends AuthorizationServerConfigurerAdapter {

    @Resource
    private UserDetailsService userServiceImpl;

    @Resource
    private DataSource dataSource;

    @Resource
    private AuthorizationCodeServices authorizationCodeServices;

    @Resource
    private AuthenticationManager authenticationManager;

    @Bean
    public TokenStore jdbcTokenStore(DataSource dataSource) {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public ClientDetailsService jdbcClientDetailsService(DataSource dataSource) {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    public OAuth2RequestFactory oAuth2RequestFactory() {
        return new DefaultOAuth2RequestFactory(jdbcClientDetailsService(dataSource));
    }

    @Bean
    public UserApprovalHandler userApprovalHandler() {
        OauthUserApprovalHandler userApprovalHandler = new OauthUserApprovalHandler();
        userApprovalHandler.setTokenStore(jdbcTokenStore(dataSource));
        userApprovalHandler.setClientDetailsService(jdbcClientDetailsService(dataSource));
        userApprovalHandler.setRequestFactory(oAuth2RequestFactory());
        return userApprovalHandler;
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpointsConfigurer) {
        endpointsConfigurer
                .authorizationCodeServices(authorizationCodeServices)
                .userApprovalHandler(userApprovalHandler())
                .authenticationManager(authenticationManager)
                //设置用户验证服务
                .userDetailsService(userServiceImpl)
                //指定token的存储方式
                .tokenStore(jdbcTokenStore(dataSource));
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService(dataSource));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer securityConfigurer) {
        //允许客户端访问oauth2授权接口
        securityConfigurer.allowFormAuthenticationForClients();
        //允许已授权用户访问checkToken 和 获取token 的接口
        securityConfigurer.checkTokenAccess("isAuthenticated()");
        securityConfigurer.tokenKeyAccess("isAuthenticated()");
    }

}
