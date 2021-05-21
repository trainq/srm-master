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
@TableName("srm_brand")
public class Brand extends BaseEntity {

    @TableId
    private Long brandId;

    private String name;

    private String logo;

    private String remark;

}
