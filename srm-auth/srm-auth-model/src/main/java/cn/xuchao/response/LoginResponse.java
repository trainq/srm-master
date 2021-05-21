package cn.xuchao.response;

import cn.xuchao.dto.NavMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author xuchao
 */
@Data
@Accessors(chain = true)
public class LoginResponse {

    private String token;

    private String nickname;

    private List<NavMenu> menuList;

}
