package cn.xuchao.entity;

import cn.xuchao.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xuchao
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class User extends BaseEntity {

    @TableId
    private Long userId;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private String phone;

    private boolean isAdmin;

    @JsonIgnore
    private String salt;

    public static final String USERNAME = "username";

    public static final String PHONE = "phone";

    public static final String EMAIL = "email";

}
