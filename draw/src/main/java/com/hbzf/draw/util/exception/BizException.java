package com.hbzf.draw.util.exception;

import java.text.MessageFormat;

/**
 * 自定义业务异常类
 *
 * @author luosai
 * @date 2020/05/02
 */
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 构造方法
     *
     * @param errorMessage 如 "a {0} b {1} c"
     * @param arguments    如 "123, Hello"
     */
    public BizException(final String errorMessage, final Object... arguments) {
        super(MessageFormat.format(errorMessage, arguments));
    }

    /**
     * 构造方法
     *
     * @param cause 异常对象
     */
    public BizException(final Throwable cause) {
        super(cause);
    }
}
