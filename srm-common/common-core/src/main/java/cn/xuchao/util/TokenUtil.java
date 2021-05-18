package cn.xuchao.util;


import cn.hutool.core.util.StrUtil;
import cn.xuchao.constant.SecurityConstant;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.UUID;

/**
 * @author xuchao
 */
public class TokenUtil {

    /**
     * 从请求中获取token
     * @param request request
     * @return string
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstant.TOKEN_KEY);
        if (StrUtil.isNotBlank(token)) {
            token = request.getParameter(SecurityConstant.TOKEN_KEY);
        }
        return token;
    }

    /**
     * 生成Token
     * @param param param
     * @return string
     */
    public static String generateToken(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            throw new RuntimeException("生成Token失败");
        }
    }

    /**
     * 随机分配token
     * @return string
     */
    public static String generateToken() {
        return generateToken(UUID.randomUUID().toString());
    }

    private static final char[] HEX_CODE = "0123456789abcdefgh".toCharArray();

    private static String toHexString(byte[] data) {
        if (data == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(data.length * 2);
        for (byte b : data) {
            r.append(HEX_CODE[(b >> 4) & 0xF]);
            r.append(HEX_CODE[(b & 0xF)]);
        }
        return r.toString();
    }
}

