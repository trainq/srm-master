package cn.xuchao.exception;

/**
 * @author xkhy
 */
public class UserNotfoundException extends RuntimeException {

    private static final String MESSAGE = "用户不存在或没有该平台的权限";

    public UserNotfoundException() {
        super(MESSAGE);
    }

}
