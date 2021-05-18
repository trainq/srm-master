package cn.xuchao.controller;

import cn.xuchao.base.BaseResponse;
import cn.xuchao.entity.User;
import cn.xuchao.request.FindByUsernameRequest;
import cn.xuchao.response.FindByUsernameResponse;
import cn.xuchao.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xuchao
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/findByUsername")
    public BaseResponse<FindByUsernameResponse> findByUsername(FindByUsernameRequest request) {
        User user = userService.getOne(new QueryWrapper<User>().eq(User.USERNAME, request.getUsername()));
        FindByUsernameResponse response = new FindByUsernameResponse().setUsername(user.getUsername()).setPassword(user.getPassword());
        return BaseResponse.ok(response);
    }

}
