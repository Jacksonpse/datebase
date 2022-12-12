package com.example.safefdu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("campus")
@Data
public class Campus {
    @TableId
    private String name;
    private String location;
}
