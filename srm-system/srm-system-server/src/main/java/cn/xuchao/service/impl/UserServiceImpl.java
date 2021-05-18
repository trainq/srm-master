package cn.xuchao.service.impl;

import cn.xuchao.entity.User;
import cn.xuchao.mapper.UserMapper;
import cn.xuchao.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xuchao
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
