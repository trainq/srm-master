package cn.xuchao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xuchao
 */
@Data
@AllArgsConstructor
public class RoleMenu implements Serializable {

    private Long roleId;

    private Long menuId;
}
