package com.example.safefdu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.safefdu.Dto.StudentDto;
import com.example.safefdu.Dto.UserDto;
import com.example.safefdu.common.ResultBody;
import com.example.safefdu.entity.*;
import com.example.safefdu.entity.Class;
import com.example.safefdu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.safefdu.common.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ExitRecordService exitRecordService;

    @PostMapping("/login")
    public ResultBody Login(@RequestBody UserDto userDto){
        Student student = studentService.getById(userDto.getId());
        if (student == null) {
            return ResultBody.error(Constants.CODE_600, "不存在该用户");
        } else {
            StudentDto studentDto = new StudentDto(student);
            Class cla = classService.getById(student.getClassId());
            String deptName = departmentService.getById(cla.getDeptId()).getName();
            studentDto.setClassName(cla.getName());
            studentDto.setDeptName(deptName);
            return ResultBody.success(studentDto);
        }

    }

    @PostMapping("/updateinfo")
    public ResultBody UpdateInfo(@RequestBody Student student){
        boolean b = studentService.updateById(student);
        if (b){
            return ResultBody.success();
        }else{
            return ResultBody.error(Constants.CODE_600,"更新未成功");
        }
    }

    @GetMapping("/admission/{id}")
    public ResultBody getAdmission(@PathVariable String id){
        Student student = studentService.getById(id);
        if (student == null)
            return ResultBody.error(Constants.CODE_600,"未找到该学生");
        else
            return ResultBody.success(student.getAdmissionLevel());
    }

    @GetMapping("/timeOfOut")
    public ResultBody getTimeOfOut(){
        Date date = new Date(System.currentTimeMillis());
        date.setYear(date.getYear() - 1);
        int time = studentService.getTimeOfOut(date.getTime());
        return ResultBody.success(time);
    }

    @GetMapping("/out")
    public ResultBody getStudentOut(){
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getInOutState,Constants.OUT);
        List<Student> list = studentService.list(queryWrapper);
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : list){
            StudentDto studentDto = new StudentDto(student);
            Class cla = classService.getById(student.getClassId());
            String deptName = departmentService.getById(cla.getDeptId()).getName();
            studentDto.setClassName(cla.getName());
            studentDto.setDeptName(deptName);
            studentDtoList.add(studentDto);

            LambdaQueryWrapper<ExitRecord> exitRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
            exitRecordLambdaQueryWrapper.eq(ExitRecord::getStuId,student.getStuId());
            exitRecordLambdaQueryWrapper.orderByDesc(ExitRecord::getTime);
            exitRecordLambdaQueryWrapper.last("limit 0,1");
            studentDto.setOutTime(exitRecordService.list(exitRecordLambdaQueryWrapper).get(0).getTime());
        }

        return ResultBody.success(studentDtoList);
    }

    //@GetMapping("")




}
