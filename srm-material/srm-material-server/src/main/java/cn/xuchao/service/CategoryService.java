package cn.xuchao.service;

import cn.xuchao.entity.Category;
import cn.xuchao.request.CategorySaveRequest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author xuchao
 */
public interface CategoryService extends IService<Category> {

    /**
     * 保存类目
     * @param request request
     * @return /
     */
    Object save(CategorySaveRequest request);

}
