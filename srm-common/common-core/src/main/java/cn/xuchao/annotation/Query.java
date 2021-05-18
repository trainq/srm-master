package cn.xuchao.annotation;

import cn.xuchao.enums.QueryType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 构建单表查询用
 *
 * @author xuchao
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Query {

    /** 属性名称 */
    String field() default "";

    /** 查询类型 */
    QueryType type() default QueryType.EQUAL;

}
