package cn.xuchao.exception;

import cn.xuchao.base.BaseException;
import org.springframework.http.HttpStatus;

/**
 * @author xkhy
 */
public class TokenExpireException extends BaseException {

    private static final String MESSAGE = "token已失效";

    public TokenExpireException() {
        super(HttpStatus.UNAUTHORIZED.value(), MESSAGE);
    }

}
