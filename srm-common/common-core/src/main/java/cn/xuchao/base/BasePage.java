package cn.xuchao.base;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xuchao
 */
@Data
public class BasePage implements Serializable {

    private Integer page = 1;

    private Integer size = 10;

    public <T> Page<T> getPageInfo() {
        return new Page<>(this.page, this.size);
    }

}
