package cn.xuchao.exception;

/**
 * @author xkhy
 */
public class PlatformForbiddenException extends RuntimeException {

    private static final String MESSAGE = "用户没有此平台的权限";

    public PlatformForbiddenException(){
        super(MESSAGE);
    }

}
