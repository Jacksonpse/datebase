package com.example.safefdu.Exception;

import lombok.Getter;

//Service层的异常类
@Getter
public class ServiceException extends RuntimeException{
    private String code;

    public ServiceException(String code, String msg) {
         super(msg);
        this.code = code;
    }

}
