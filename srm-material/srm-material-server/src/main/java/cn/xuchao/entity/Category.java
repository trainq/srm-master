package cn.xuchao.entity;

import cn.xuchao.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xuchao
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("srm_category")
@Accessors(chain = true)
public class Category extends BaseEntity {

    @TableId
    private Long categoryId;

    private Long parentId;

    private Integer level;

    private Boolean isLeaf;

    private String remark;

    public static final String CATEGORY_ID = "category_id";

}
