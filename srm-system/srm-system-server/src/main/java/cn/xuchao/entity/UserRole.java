package cn.xuchao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xuchao
 */
@Data
@AllArgsConstructor
public class UserRole implements Serializable {

    private Long userId;

    private Long roleId;

    public static final String USER_ID = "user_id";

}
