package com.example.safefdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.safefdu.entity.HealthReport;

import java.util.List;

public interface HealthReportService extends IService<HealthReport> {
    public List<HealthReport> listReport(String id,int count);
}
