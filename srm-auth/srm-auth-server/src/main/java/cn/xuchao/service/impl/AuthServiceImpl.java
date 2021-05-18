package cn.xuchao.service.impl;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.xuchao.base.BaseException;
import cn.xuchao.constant.SecurityConstant;
import cn.xuchao.entity.User;
import cn.xuchao.request.LoginRequest;
import cn.xuchao.response.LoginResponse;
import cn.xuchao.service.AuthService;
import cn.xuchao.service.UserService;
import cn.xuchao.util.RedisUtil;
import cn.xuchao.util.TokenUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * @author xuchao
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserService userService;

    @Override
    public LoginResponse login(LoginRequest request) {
        String storeCaptcha = (String) redisUtil.get(request.getCaptchaKey());
        if (storeCaptcha == null) {
            throw new BaseException("验证码已过期");
        } else if (!storeCaptcha.equals(request.getCaptcha())) {
            throw new BaseException("验证码不正确");
        }
        /*清除验证码*/
        redisUtil.delete(request.getCaptchaKey());
        String username = request.getUsername();
        String password = request.getPassword();
        User dbUser = userService.getOne(new QueryWrapper<User>().eq(User.USERNAME, username).eq(User.PLATFORM_ID, request.getPlatFormId()));
        if (dbUser == null) {
            throw new UnknownAccountException();
        }
        byte[] key = Base64.getDecoder().decode(dbUser.getSalt());
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);
        if (!aes.decryptStr(dbUser.getPassword()).equals(password)) {
            throw new BaseException("密码不正确");
        }
        /*踢出之前登录的用户*/
        for (String redisKey : redisUtil.scan(SecurityConstant.ONLINE_PREFIX + "*")) {
            User user = (User) redisUtil.get(redisKey);
            if (username.equals(user.getUsername())) {
                redisUtil.delete(redisKey);
            }
        }
        String token = TokenUtil.generateToken();
        redisUtil.set(SecurityConstant.ONLINE_PREFIX + token, dbUser, 30, TimeUnit.MINUTES);
        return new LoginResponse(token, dbUser.getNickname());
    }

    @Override
    public void logout(String token) {

    }

}
