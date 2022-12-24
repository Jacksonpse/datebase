package com.example.safefdu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@TableName("health_report")
@Data
public class HealthReport implements Serializable {
    @MppMultiId
    private String stuId;
    @MppMultiId
    private Timestamp time;
    private int state;
    private int temperature;
    private String location;
}
