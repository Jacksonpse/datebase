package com.example.safefdu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@TableName("student")
@Data
public class Student {
    @TableId
    private String stuId;
    private String name;
    private String phone;
    private String email;
    private String building;
    private String homeAddr;
    private String idCardType;
    private String idCardNum;
    private int admissionLevel;
    private int inOutState;
    private String campName;
    private int classId;

    private int deptId;


}
