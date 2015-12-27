package org.dimhat.example.exception;

/**
 * 业务异常
 * @author dimhat
 * @date 2015年12月27日 下午11:44:27
 * @version 1.0
 */
public class MyAppException extends RuntimeException {

    public MyAppException() {
    }

    public MyAppException(String message) {
        super(message);
    }
}
