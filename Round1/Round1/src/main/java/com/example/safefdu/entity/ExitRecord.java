package com.example.safefdu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@TableName("exit_record")
@Data
public class ExitRecord implements Serializable {
    @TableId
    private int exId;
    private Timestamp time;
    private String stuId;
    private String campusName;
}
