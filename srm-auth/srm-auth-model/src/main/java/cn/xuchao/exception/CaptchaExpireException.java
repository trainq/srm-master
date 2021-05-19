package cn.xuchao.exception;

/**
 * @author xkhy
 */
public class CaptchaExpireException extends RuntimeException {
    private static final String MESSAGE = "验证码已过期";

    public CaptchaExpireException() {
        super(MESSAGE);
    }
}
