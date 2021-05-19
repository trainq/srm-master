package cn.xuchao.exception;

/**
 * @author xkhy
 */
public class CaptchaIncorrectException extends RuntimeException {
    private static final String MESSAGE = "验证码错误";

    public CaptchaIncorrectException() {
        super(MESSAGE);
    }
}
