package com.example.safefdu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.safefdu.common.ResultBody;
import com.example.safefdu.entity.ExitRecord;
import com.example.safefdu.service.ExitRecordService;
import com.example.safefdu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("exitrecord")
public class ExitRecordController {
    @Autowired
    private ExitRecordService exitRecordService;

    @Autowired
    private StudentService studentService;


}
