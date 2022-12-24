package com.example.safefdu.controller;

import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.safefdu.common.Constants;
import com.example.safefdu.common.ResultBody;
import com.example.safefdu.entity.EntryRecord;
import com.example.safefdu.entity.ExitRecord;
import com.example.safefdu.entity.Instructor;
import com.example.safefdu.entity.Student;
import com.example.safefdu.service.CampusService;
import com.example.safefdu.service.EntryRecordService;
import com.example.safefdu.service.ExitRecordService;
import com.example.safefdu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/campus")
public class CampusController{
    @Autowired
    private CampusService campusService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExitRecordService exitRecordService;

    @Autowired
    private EntryRecordService entryRecordService;

    @GetMapping("/max/{id}/{num}")
    public ResultBody getMaxCamp(@PathVariable int id,@PathVariable int num){
        /*//筛选出该专业的学生
        List<String> stuList = new ArrayList<>();
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getCampName,name);
        List<Student> students = studentService.list(studentLambdaQueryWrapper);
        for (Student student : students){
            stuList.add(student.getStuId());
        }

        //得到每个校区的出记录数量
        QueryWrapper<ExitRecord> exitRecordQueryWrapper = new QueryWrapper<>();
        exitRecordQueryWrapper.select("stu_id,count(*)");
        exitRecordQueryWrapper.groupBy("stu_id");
        exitRecordQueryWrapper.ge("time",new Timestamp(System.currentTimeMillis() - num * Constants.ONEDAY));
        exitRecordQueryWrapper.in("stu_id",stuList);
        List<Map<String, Object>> out = exitRecordService.listMaps(exitRecordQueryWrapper);

        //得到每个校区的入记录数量
        QueryWrapper<EntryRecord> entryRecordQueryWrapper = new QueryWrapper<>();
        entryRecordQueryWrapper.select("stu_id,count(*)");
        entryRecordQueryWrapper.groupBy("stu_id");
        entryRecordQueryWrapper.ge("time",new Timestamp(System.currentTimeMillis() - num * Constants.ONEDAY));
        entryRecordQueryWrapper.in("stu_id",stuList);
        List<Map<String, Object>> in = entryRecordService.listMaps(entryRecordQueryWrapper);

        Map<String,Long> campList = new HashMap<>();
        for (Map<String,Object> map : out){
            if (campList.containsKey(map.))
        }*/
        long beginTime = System.currentTimeMillis() - num * Constants.ONEDAY;
        List<Pair<String, Long>> list = campusService.getCampusByDept(id, new Timestamp(beginTime));

        if (list.isEmpty())
            return ResultBody.success();

        return ResultBody.success(list.get(0));
    }

}
