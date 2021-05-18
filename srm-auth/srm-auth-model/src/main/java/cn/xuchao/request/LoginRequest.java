package cn.xuchao.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author xuchao
 */
@Data
@Accessors(chain = true)
public class LoginRequest implements Serializable {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String captcha;

    private String captchaKey;

    private Long platFormId;

}
