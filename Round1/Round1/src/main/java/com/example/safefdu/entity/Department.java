package com.example.safefdu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("department")
@Data
public class Department {
    @TableId
    private int dId;
    private String name;
    private String campusName;
    private String instructorId;
}
