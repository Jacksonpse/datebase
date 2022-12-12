package com.example.safefdu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("class")
@Data
public class Class {
    @TableId
    private int classId;
    private String name;
    private int deptId;
    private String instructorId;
}
