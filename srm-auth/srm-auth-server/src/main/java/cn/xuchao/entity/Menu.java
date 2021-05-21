package cn.xuchao.entity;

import cn.xuchao.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xuchao
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
@Data
public class Menu extends BaseEntity {

    @TableId
    private Long menuId;

    private String name;

    private Long parentId;

    private Integer type;

    private String url;

    private String icon;

    private Integer orderNum;

    private Long platformId;

    public static final String TYPE = "type";

}
