package cn.xuchao.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xkhy
 */
@Data
public class Menu implements Serializable {

    private String url;

    private String icon;

    private Integer orderNum;

    private List<Menu> subMenu;

}
