package cn.xuchao.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Administrator
 */
@Component
public class MyBatisMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        /*todo 自动填充创建及修改用户 微服务 跨域 session不能用 目前的想法是拦截请求后将用户名放入threadLocal中
         his.strictInsertFill(metaObject, "createUser", ShiroUtil::getCurrentUsername, String.class);
         this.strictInsertFill(metaObject, "updateUser", ShiroUtil::getCurrentUsername, String.class);
         */
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        /*todo 自动填充创建及修改用户
        this.strictUpdateFill(metaObject, "updateUser", ShiroUtil::getCurrentUsername, String.class);
         */
    }

}
