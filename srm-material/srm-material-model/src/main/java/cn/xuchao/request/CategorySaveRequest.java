package cn.xuchao.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author xuchao
 */
@Data
public class CategorySaveRequest implements Serializable {

    @NotBlank
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("父类id")
    private Long parentId;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("是否为终节点")
    private Boolean isLeaf = false;

}
