package com.example.safefdu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBody {

    private String code;
    private String msg;
    private Object data;

    public static ResultBody success() {
        return new ResultBody(Constants.CODE_200, "", null);
    }

    public static ResultBody success(Object data) {
        return new ResultBody(Constants.CODE_200, "", data);
    }

    public static ResultBody error(String code, String msg) {
        return new ResultBody(code, msg, null);
    }

    public static ResultBody error(String code, String msg,Object data){ return new ResultBody(code, msg, data); }

    public static ResultBody error() {
        return new ResultBody(Constants.CODE_500, "系统错误", null);
    }


}
