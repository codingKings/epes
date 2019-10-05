package com.epes.demo.tool.exception;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 下午2:45:19
* @ClassName 类名称：
* @Description 类描述：
*/
public class NotTableEntityException extends Exception {

    public NotTableEntityException() {
    }

    public NotTableEntityException(String message) {
        super(message);
    }

    public NotTableEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotTableEntityException(Throwable cause) {
        super(cause);
    }

    public NotTableEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
