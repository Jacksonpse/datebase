package com.example.safefdu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@TableName("exit_apply")
@Data
public class ExitApply implements Serializable {
    @MppMultiId
    private String stuId;
    @MppMultiId
    private Timestamp applyTime;
    private String reason;
    private String dest;
    private Date leaveDate;
    private Date returnDate;
    private int checkStage;
    private String denyComment;
}
