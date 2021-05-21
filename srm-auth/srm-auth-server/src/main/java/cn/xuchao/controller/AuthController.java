package cn.xuchao.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import cn.xuchao.base.BaseResponse;
import cn.xuchao.response.CaptchaResponse;
import cn.xuchao.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@RestController
@Slf4j
public class AuthController {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("/getCaptcha")
    public BaseResponse<Object> getCaptcha() {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116, 36, 6, 10);
        String code = lineCaptcha.getCode();
        String captchaKey = UUID.fastUUID().toString(true);
        redisUtil.set(captchaKey, code, 10, TimeUnit.MINUTES);
        CaptchaResponse captchaResponse = new CaptchaResponse().setSrc(lineCaptcha.getImageBase64Data()).setCaptchaKey(captchaKey);
        return BaseResponse.ok(captchaResponse);
    }

}
