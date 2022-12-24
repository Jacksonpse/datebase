package com.example.safefdu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.safefdu.Exception.ServiceException;
import com.example.safefdu.common.Constants;
import com.example.safefdu.common.ResultBody;
import com.example.safefdu.dao.HealthReportDao;
import com.example.safefdu.dao.StudentDao;
import com.example.safefdu.entity.HealthReport;
import com.example.safefdu.service.HealthReportService;
import com.example.safefdu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;

@Service
public class HealthReportServiceImpl extends ServiceImpl<HealthReportDao, HealthReport> implements HealthReportService {
    @Autowired
    private HealthReportDao healthReportDao;

    @Autowired
    private StudentDao studentDao;
    @Override
    public List<HealthReport> listReport(String id, int count) {
        LambdaQueryWrapper<HealthReport> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.le(HealthReport::getTime, System.currentTimeMillis());
        queryWrapper.eq(HealthReport::getStuId,id);
        queryWrapper.orderByDesc(HealthReport::getTime);
        queryWrapper.last("limit 0," + count);
        List<HealthReport> healthReports = healthReportDao.selectList(queryWrapper);
        healthReports.sort(new Comparator<HealthReport>() {
            @Override
            public int compare(HealthReport o1, HealthReport o2) {
                 return (int) (o1.getTime().getTime() - o2.getTime().getTime());
            }
        });
        return healthReports;
    }

    @Override
    public boolean save(HealthReport healthReport) {
        if (studentDao.selectById(healthReport.getStuId()) == null){
            throw new ServiceException(Constants.CODE_600,"未找到该学生");
        }
        Timestamp timestamp = new Timestamp(healthReport.getTime().getTime());
        timestamp.setMinutes(0);
        timestamp.setSeconds(0);
        timestamp.setHours(0);
        timestamp.setNanos(0);
        LambdaQueryWrapper<HealthReport> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HealthReport::getStuId,healthReport.getStuId());
        queryWrapper.between(HealthReport::getTime,timestamp.getTime(),timestamp.getTime() + Constants.ONEDAY);
        //System.out.println(healthReportDao.selectList(queryWrapper).isEmpty());
        //System.out.println(healthReportDao.selectList(queryWrapper));
        if(!healthReportDao.selectList(queryWrapper).isEmpty())
            throw new ServiceException(Constants.CODE_600,"今日已填过健康日报");
        return super.save(healthReport);
    }
}
