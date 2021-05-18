package cn.xuchao.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xuchao
 */
@Data
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

    private static final Integer SUCCESS_CODE = 200;

    private static final Integer FAIL_CODE = 500;

    private Integer code;

    private String message;

    private T data;

    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<>(SUCCESS_CODE, null, data);
    }

    public static <T> BaseResponse<T> ok() {
        return new BaseResponse<>(SUCCESS_CODE, null, null);
    }

    public static <T> BaseResponse<T> error(Integer code, String message) {
        return new BaseResponse<>(code, message, null);
    }

    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<>(FAIL_CODE, message, null);
    }

}
