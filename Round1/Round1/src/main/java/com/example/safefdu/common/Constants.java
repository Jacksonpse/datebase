package com.example.safefdu.common;

// 自定义全局常量
public class Constants {
    public static final String CODE_200 = "200"; 	// 成功
    public static final String CODE_401 = "401"; 	// 权限不足
    public static final String CODE_400 = "400";  	// 参数错误
    public static final String CODE_500 = "500"; 	// 系统错误
    public static final String CODE_600 = "600"; 	// 其他业务异常
    public static final String CODE_402 = "402";    //异地登陆


    public static final int CLASSACCEPT = 1;
    public static final int CLASSREFUSE = -1;

    public static final int TEACHERREFUSE = -2;

    public static final int TEACHERACCEPT = 2;

    public static final int UNDISPOSED = 0;

    public static final int REFUSE = -3;

    public static final int ALL = 3;


    public static final int IN = 0;
    public static final int OUT = 1;

    public static long ONEDAY = 86400000L;

    public static long ONEMINUTE = 60000L;

    public static long ONEYEAR = 31536000000L;



}
