package cn.xuchao.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xuchao
 */
@Data
@Accessors(chain = true)
public class FindByUsernameResponse implements Serializable {

    private String username;

    private String password;

}
