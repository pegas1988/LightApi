package com.mike.osdb.lightapi.controller.exception;

public class ApiRequestException extends RuntimeException {
    public ApiRequestException(String s) {
        super(s);
    }
}
