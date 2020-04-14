package com.zz.exception;

/**
 * @author 小蜜蜂
 */
public class DataValidationException extends RuntimeException {
    public DataValidationException(String msg) {
        super(msg);
    }
}
