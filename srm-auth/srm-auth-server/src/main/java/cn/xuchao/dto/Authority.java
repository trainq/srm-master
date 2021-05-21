package cn.xuchao.dto;

import cn.xuchao.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author xkhy
 */
@Data
@AllArgsConstructor
public class Authority implements GrantedAuthority {

    private Menu menu;

    @Override
    public String getAuthority() {
        return this.menu.getUrl();
    }

}
