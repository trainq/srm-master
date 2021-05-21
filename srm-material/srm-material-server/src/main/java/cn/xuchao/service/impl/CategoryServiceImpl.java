package cn.xuchao.service.impl;

import cn.xuchao.entity.Category;
import cn.xuchao.exception.ParentNotExistException;
import cn.xuchao.mapper.CategoryMapper;
import cn.xuchao.request.CategorySaveRequest;
import cn.xuchao.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xuchao
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public Object save(CategorySaveRequest request) {
        Category category = new Category();
        /*父类目*/
        if (request.getParentId() == null) {
            category.setParentId(0L)
                    .setLevel(1);
        } else {
            Category parent = getOne(new QueryWrapper<Category>().eq(Category.CATEGORY_ID, request.getParentId()));
            if (parent == null) {
                throw new ParentNotExistException();
            }
            category.setParentId(parent.getCategoryId())
                    .setLevel(parent.getLevel() + 1);
        }
        /*其他*/
        category.setName(request.getName()).setRemark(request.getRemark()).setIsLeaf(request.getIsLeaf());
        return save(category);
    }

}
