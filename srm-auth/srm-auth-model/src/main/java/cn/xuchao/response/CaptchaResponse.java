package cn.xuchao.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Administrator
 */
@Data
@Accessors(chain = true)
public class CaptchaResponse implements Serializable {

    private String src;

    private String captchaKey;
}
