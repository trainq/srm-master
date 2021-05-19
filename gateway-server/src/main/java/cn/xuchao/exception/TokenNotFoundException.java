package cn.xuchao.exception;

import cn.xuchao.base.BaseException;
import org.springframework.http.HttpStatus;

/**
 * @author xkhy
 */
public class TokenNotFoundException extends BaseException {

    public static final String MESSAGE = "请求未携带token";

    public TokenNotFoundException() {
        super(HttpStatus.UNAUTHORIZED.value(), MESSAGE);
    }

}
