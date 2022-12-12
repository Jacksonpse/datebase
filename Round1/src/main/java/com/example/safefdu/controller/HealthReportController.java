package com.example.safefdu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.safefdu.annoation.StuIdCheck;
import com.example.safefdu.common.Constants;
import com.example.safefdu.common.ResultBody;
import com.example.safefdu.entity.HealthReport;
import com.example.safefdu.service.HealthReportService;
import com.example.safefdu.service.StudentService;
import com.example.safefdu.utils.SystemTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.scanner.Constant;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/healthreport")
public class HealthReportController {
    @Autowired
    private HealthReportService healthReportService;

    @GetMapping("/get/{id}/{count}")
    public ResultBody getHealthReport(@PathVariable String id,@PathVariable int count){

        List<HealthReport> healthReports = healthReportService.listReport(id, count);

        return ResultBody.success(healthReports);

    }

    @PostMapping("/insert")
    public ResultBody insertHealthReport(@RequestBody HealthReport healthReport){
        healthReport.setTime(new Date(System.currentTimeMillis()));
        healthReportService.save(healthReport);
        return ResultBody.success();
    }
}
