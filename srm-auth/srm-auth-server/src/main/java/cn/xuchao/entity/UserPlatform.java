package cn.xuchao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户与平台关联
 *
 * @author xkhy
 */
@Data
@TableName("sys_user_platform")
public class UserPlatform implements Serializable {

    private Long userId;

    private Long platformId;

    public static final String USER_ID = "user_id";

    public static final String PLATFORM_ID = "platform_id";

}
