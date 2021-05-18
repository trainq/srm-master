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
public class FindByUsernameRequest implements Serializable {

    @NotBlank(message = "用户名不能为空")
    private String username;

}
