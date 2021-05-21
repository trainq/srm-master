package cn.xuchao.exception;

import cn.xuchao.base.BaseException;

/**
 * @author xkhy
 */
public class ParentNotExistException extends BaseException {

    private static final String MESSAGE = "未查询到父类目";

    public ParentNotExistException() {
        super(MESSAGE);
    }

}