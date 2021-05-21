package cn.xuchao.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author xkhy
 */
@Data
@Accessors(chain = true)
public class NavMenu implements Serializable {

    private Long menuId;

    private String name;

    private Long parentId;

    private Integer type;

    private String url;

    private String icon;

    private Integer orderNum;

    private List<NavMenu> children;

}
