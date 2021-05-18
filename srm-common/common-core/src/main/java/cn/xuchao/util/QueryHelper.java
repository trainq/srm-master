package cn.xuchao.util;

import cn.hutool.core.util.StrUtil;
import cn.xuchao.annotation.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.lang.reflect.Field;

/**
 * queryWrapper 不支持join
 *
 * @author Administrator
 */
public class QueryHelper {

    /**
     * 构建单表查询条件
     *
     * @param query query
     * @param <T>   实体类
     * @param <Q>   查询条件类
     * @return QueryWrapper<T>
     */
    public static <T, Q> QueryWrapper<T> generateWrapper(Q query) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        Field[] fields = query.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Query q = field.getAnnotation(Query.class);
            if (q != null) {
                String name = StrUtil.isBlank(q.field()) ? field.getName() : q.field();
                String column = StrUtil.toCamelCase(name);
                Object val = null;
                try {
                    val = field.get(query);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                boolean condition = val != null;
                switch (q.type()) {
                    case EQUAL:
                        queryWrapper.eq(condition, column, val);
                        break;
                    case NOT_EQUAL:
                        queryWrapper.ne(condition, column, val);
                        break;
                    case LIKE:
                        queryWrapper.like(condition, column, val);
                    case IN:
                        queryWrapper.in(condition, column, val);
                        break;
                    default:
                        break;
                }
            }
            field.setAccessible(accessible);
        }

        return queryWrapper;
    }

}
