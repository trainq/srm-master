package cn.xuchao.base;

import lombok.EqualsAndHashCode;
import lombok.Setter;

/**
 * @author xuchao
 */
@EqualsAndHashCode(callSuper = true)
@Setter
public class BaseException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    private String message;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
