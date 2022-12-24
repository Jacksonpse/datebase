package com.example.safefdu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("instructor")
@Data
public class Instructor {
    @TableId
    private String wId;
    private String name;
    private String role;
}
