package com.example.safefdu.utils;


import com.example.safefdu.common.ResultBody;

public class ControllerUtils {
    public static ResultBody JudgeByFlag(boolean flag, String code, String msg){
        if(flag)
            return ResultBody.success();
        else
            return ResultBody.error(code,msg);
    }
}
