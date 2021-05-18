package cn.xuchao.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xuchao
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private String message;

    public BaseException(String message) {
        super(message);
    }

}
