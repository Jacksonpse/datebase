package com.example.safefdu.Exception;

import com.example.safefdu.common.ResultBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    /**
     * 将Service异常在该异常类中处理
     * @param se
     * @return ResultBody
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResultBody handle(ServiceException se) {
        return ResultBody.error(se.getCode(), se.getMessage());
    }

}
