package com.example.safefdu.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "roleconstants")
public class RoleConstant {
    //用户身份
    public static final String ADMIN = "sup_ins";
    public static final String CLASS_INSTRUCTOR = "cls_ins";
    public static final String DEPT_INSTRUCTOR = "dept_ins";
    public static final String STUDENT = "stu";

    public static final Integer StudentUIDLength=11;   // 学生uid长度
    public static final Integer TeacherUIDLength=8;   // 教师uid长度

    public static  String AdministratorUID = null; // 管理员uid
    public static  String AdministratorStr = null;  // 管理员str

    public static final int IN_STATUS = 1;     //入校状态
    public static final int OUT_STATUS = 2;   //离校状态


    public void setAdministratorUID(String administratorUID) {
        AdministratorUID = administratorUID;
    }

    public void setAdministratorStr(String administratorStr) {
        AdministratorStr = administratorStr;
    }

}
