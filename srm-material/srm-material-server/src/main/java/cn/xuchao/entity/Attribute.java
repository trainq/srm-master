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
@Data
@TableName("srm_attribute")
public class Attribute extends BaseEntity {

    @TableId
    private Long attributeId;

    private String name;

    private String remark;

    private Long categoryId;

}
