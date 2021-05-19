package cn.xuchao.dto;

import cn.xuchao.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xkhy
 */
@Data
public class SecurityUser implements Serializable {

    private User user;

    private List<String> permission;

}
