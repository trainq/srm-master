package cn.xuchao.service.impl;

import cn.xuchao.dto.Authority;
import cn.xuchao.dto.SecurityUser;
import cn.xuchao.entity.User;
import cn.xuchao.mapper.UserMapper;
import cn.xuchao.service.MenuService;
import cn.xuchao.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuchao
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Resource
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User dbUser = getOne(new QueryWrapper<User>().eq(User.USERNAME, username));
        if (dbUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //todo 获取用户权限
        List<Authority> authorities = menuService.list().stream().map(Authority::new).collect(Collectors.toList());
        return new SecurityUser()
                .setUserId(dbUser.getUserId())
                .setUsername(dbUser.getUsername())
                .setPassword(dbUser.getPassword())
                .setAuthorities(authorities);
    }

}
