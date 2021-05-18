package cn.xuchao.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xuchao
 */
@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;

    private String nickname;
}
