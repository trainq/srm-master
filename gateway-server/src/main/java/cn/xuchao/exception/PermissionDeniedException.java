package cn.xuchao.exception;

import cn.xuchao.base.BaseException;
import org.springframework.http.HttpStatus;

/**
 * @author xkhy
 */
public class PermissionDeniedException extends BaseException {

    private static final String MESSAGE = "没有访问权限";

    public PermissionDeniedException() {
        super(HttpStatus.FORBIDDEN.value(), MESSAGE);
    }

}
