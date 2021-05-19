package cn.xuchao.controller;

import cn.xuchao.base.BaseResponse;
import cn.xuchao.request.CategorySaveRequest;
import cn.xuchao.service.CategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xuchao
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/save")
    public BaseResponse<Object> save(@RequestBody @Validated CategorySaveRequest request) {
        return BaseResponse.ok(categoryService.save(request));
    }

}
