package cn.xuchao.exception;

/**
 * @author xkhy
 */
public class PasswordIncorrectException extends RuntimeException {

    private static final String MESSAGE = "密码不正确";

    public PasswordIncorrectException() {
        super(MESSAGE);
    }

}
