package com.time;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SimpleQuery;
import com.example.safefdu.SafefduApplication;
import com.example.safefdu.common.Constants;
import com.example.safefdu.dao.HealthReportDao;
import com.example.safefdu.entity.EntryRecord;
import com.example.safefdu.entity.HealthReport;
import com.example.safefdu.service.EntryRecordService;
import com.example.safefdu.service.EntryReplyService;
import com.example.safefdu.service.HealthReportService;
import com.example.safefdu.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = SafefduApplication.class)
public class ServiceTest {
    @Autowired
    private StudentService studentService;

    @Autowired
    private EntryRecordService entryRecordService;

    @Autowired
    private HealthReportService healthReportService;

    @Autowired
    private HealthReportDao healthReportDao;

    @Test
    public void listMapTest(){
        QueryWrapper<EntryRecord> entryRecordQueryWrapper = new QueryWrapper<>();
        entryRecordQueryWrapper.groupBy("stu_id");
        List<Map<String, Object>> in = entryRecordService.listMaps(entryRecordQueryWrapper);
    }

    @Test
    public void t(){
        LambdaQueryWrapper<HealthReport> queryWrapper = new LambdaQueryWrapper<>();
        //queryWrapper.groupBy(HealthReport::getStuId);
        queryWrapper.orderByDesc(HealthReport::getTime);
        Map<String,List<HealthReport>> list = SimpleQuery.group(queryWrapper,HealthReport::getStuId);

        System.out.println(list);
    }

    @Test
    public void t3(){
        LambdaQueryWrapper<HealthReport> queryWrapper = new LambdaQueryWrapper<>();
        Timestamp end = toZero(new Timestamp(System.currentTimeMillis()));
        Timestamp begin = toZero(new Timestamp(System.currentTimeMillis() - 3 * Constants.ONEDAY));
        queryWrapper.between(HealthReport::getTime,begin,end);
        queryWrapper.groupBy(HealthReport::getStuId);
        queryWrapper.select(HealthReport::getStuId);
        queryWrapper.having("count(*) = {0}",3);
        //queryWrapper.having("max(time) < all(select time)",)
        List<Object> list = healthReportService.listObjs(queryWrapper);
    }

    @Test
    public void t4(){
        LambdaQueryWrapper<HealthReport> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(HealthReport::getTime);
        //queryWrapper.last("limit 0,2");
        //queryWrapper.groupBy(HealthReport::getStuId);
        //queryWrapper.select(HealthReport::getStuId);

        Map<String,List<HealthReport>> listMap = SimpleQuery.group(queryWrapper,HealthReport::getStuId);
        System.out.println(listMap);
    }

    @Test
    public void t5(){
        QueryWrapper<HealthReport> queryWrapper = new QueryWrapper<>();
        System.out.println(new Timestamp(System.currentTimeMillis() - 2 * Constants.ONEDAY));
        queryWrapper.eq("stu_id","20302010060");
        queryWrapper.ge("time", new Timestamp(System.currentTimeMillis() - 2 * Constants.ONEDAY));
        queryWrapper.select("time");
        //queryWrapper.last("limit 0," + count);
        System.out.println(healthReportDao.selectList(queryWrapper));
    }

    private Timestamp toZero(Timestamp timestamp){
        timestamp.setMinutes(0);
        timestamp.setSeconds(0);
        timestamp.setHours(0);
        timestamp.setNanos(0);
        return timestamp;
    }
}
