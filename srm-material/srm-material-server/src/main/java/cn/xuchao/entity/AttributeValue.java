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
@TableName("srm_attribute_value")
public class AttributeValue extends BaseEntity {

    @TableId
    private Long attributeValueId;

    private Long attributeId;

    private String value;

    private String remark;

}
