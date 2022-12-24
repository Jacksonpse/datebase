package com.example.safefdu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SimpleQuery;
import com.example.safefdu.Dto.StudentDto;
import com.example.safefdu.annoation.StuIdCheck;
import com.example.safefdu.common.Constants;
import com.example.safefdu.common.ResultBody;
import com.example.safefdu.entity.Class;
import com.example.safefdu.entity.ExitRecord;
import com.example.safefdu.entity.HealthReport;
import com.example.safefdu.entity.Student;
import com.example.safefdu.service.ClassService;
import com.example.safefdu.service.DepartmentService;
import com.example.safefdu.service.HealthReportService;
import com.example.safefdu.service.StudentService;
import com.example.safefdu.utils.SystemTimeUtils;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.scanner.Constant;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

@RestController
@RequestMapping("/healthreport")
public class HealthReportController {
    @Autowired
    private HealthReportService healthReportService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/get/{id}/{count}")
    public ResultBody getHealthReport(@PathVariable String id,@PathVariable int count){

        List<HealthReport> healthReports = healthReportService.listReport(id, count);

        return ResultBody.success(healthReports);

    }

    @GetMapping("/same/{count}")
    public ResultBody getSameStudent(@PathVariable int count){
        LambdaQueryWrapper<HealthReport> queryWrapper = new LambdaQueryWrapper<>();
        /*Timestamp end = toZero(new Timestamp(System.currentTimeMillis()));
        Timestamp begin = toZero(new Timestamp(System.currentTimeMillis() - count * Constants.ONEDAY));
        //queryWrapper.between(HealthReport::getTime,begin,end);
        queryWrapper.orderByDesc(HealthReport::getTime);
        queryWrapper.last("limit 0," + count);
        queryWrapper.groupBy(HealthReport::getStuId);
        queryWrapper.select(HealthReport::getStuId);
        queryWrapper.having("count(*) = {0}",count);

        List<Object> list = healthReportService.listObjs(queryWrapper);*/
        queryWrapper.orderByDesc(HealthReport::getTime);
        Map<String,List<HealthReport>> listMap = SimpleQuery.group(queryWrapper,HealthReport::getStuId);
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (String stuId : listMap.keySet()){
            List<HealthReport> list = listMap.get(stuId);
            boolean s = true;
            for (int i = 1; i < list.size() && i < count; i++){
                if (!same(list.get(i).getTime(),list.get(0).getTime()))
                    s = false;
            }
            if (s){
                Student student = studentService.getById(stuId);
                studentDtoList.add(ToStudentDto(student));
            }
        }

        return ResultBody.success(studentDtoList);
    }


    @PostMapping("/insert")
    public ResultBody insertHealthReport(@RequestBody HealthReport healthReport){
        healthReport.setTime(new Timestamp(System.currentTimeMillis()));
        healthReportService.save(healthReport);
        return ResultBody.success();
    }

    private boolean same(Timestamp timestamp1,Timestamp timestamp2){
        return abs(timestamp1.getTime() - timestamp2.getTime()) < Constants.ONEMINUTE;
    }

    private StudentDto ToStudentDto(Student student){
        StudentDto studentDto = new StudentDto(student);
        Class cla = classService.getById(student.getClassId());
        String deptName = departmentService.getById(cla.getDeptId()).getName();
        studentDto.setClassName(cla.getName());
        studentDto.setDeptName(deptName);

        return studentDto;
    }
}
