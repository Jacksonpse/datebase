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

import java.sql.Timestamp;
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

    @Autowired
    private ExitApplyService exitApplyService;

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

    //获取一年离校总时长
    @GetMapping("/timeOfOut/{id}")
    public ResultBody getTimeOfOut(@PathVariable String id){
        Date date = new Date(System.currentTimeMillis());
        date.setYear(date.getYear() - 1);
        long time = studentService.getTimeOfOut(date.getTime(),id);
        return ResultBody.success(time);
    }

    //获取前n个平均离校时间最长的学生
    @GetMapping("/timeOfOutMaxInSchool/{count}")
    public ResultBody getTimeOfOutInSchool(@PathVariable int count){
        return ResultBody.success(studentService.getTimeOfOutListBySchool(0,count));
    }
    @GetMapping("/timeOfOutMaxInDept/{deptId}/{count}")
    public ResultBody getTimeOfOutInDept(@PathVariable int count,@PathVariable int deptId){
        return ResultBody.success(studentService.getTimeOfOutListByDept(0,deptId,count));
    }
    @GetMapping("/timeOfOutMaxInDept/{classId}/{count}")
    public ResultBody getTimeOfOutInClass(@PathVariable int count,@PathVariable int classId){
        return ResultBody.success(studentService.getTimeOfOutListByDept(0,classId,count));
    }


    //获取所有离校的学生信息
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

    @GetMapping("/out_no_entryapply")
    public ResultBody getOutNoEntryReply(){
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getInOutState,Constants.OUT);
        List<Student> studentList = studentService.list(studentLambdaQueryWrapper);

        //筛选出出校24小时的学生
        for (int i = 0; i < studentList.size(); i++){
            LambdaQueryWrapper<ExitRecord> exitRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
            exitRecordLambdaQueryWrapper.eq(ExitRecord::getStuId,studentList.get(i).getStuId());
            exitRecordLambdaQueryWrapper.orderByDesc(ExitRecord::getTime);
            exitRecordLambdaQueryWrapper.last("limit 0,1");
            Timestamp timestamp = exitRecordService.list(exitRecordLambdaQueryWrapper).get(0).getTime();
            if (System.currentTimeMillis() - timestamp.getTime() < 86400000L)
                studentList.remove(i);
        }

        //筛选出未提交申请的
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : studentList){
            LambdaQueryWrapper<ExitApply> exitApplyLambdaQueryWrapper = new LambdaQueryWrapper<>();
            exitApplyLambdaQueryWrapper.eq(ExitApply::getStuId,student.getStuId());
            exitApplyLambdaQueryWrapper.le(ExitApply::getLeaveDate,new Date(System.currentTimeMillis()));
            exitApplyLambdaQueryWrapper.ge(ExitApply::getReturnDate,new Date(System.currentTimeMillis()));
            if (exitApplyService.list(exitApplyLambdaQueryWrapper).isEmpty()){
                StudentDto studentDto = new StudentDto(student);
                Class cla = classService.getById(student.getClassId());
                String deptName = departmentService.getById(cla.getDeptId()).getName();
                studentDto.setClassName(cla.getName());
                studentDto.setDeptName(deptName);
                studentDtoList.add(studentDto);
            }

        }

        return ResultBody.success(studentDtoList);
    }


    @GetMapping("/in_having_exitapply")
    public ResultBody getInHavingExitApply(){
        //筛选出未出校的学生
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getInOutState,Constants.IN);
        List<Student> studentList = studentService.list(studentLambdaQueryWrapper);

        //筛选出已提交出校申请
        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : studentList){
            LambdaQueryWrapper<ExitApply> exitApplyLambdaQueryWrapper = new LambdaQueryWrapper<>();
            exitApplyLambdaQueryWrapper.eq(ExitApply::getStuId,student.getStuId());
            exitApplyLambdaQueryWrapper.le(ExitApply::getLeaveDate,new Date(System.currentTimeMillis()));
            exitApplyLambdaQueryWrapper.ge(ExitApply::getReturnDate,new Date(System.currentTimeMillis()));
            if (!exitApplyService.list(exitApplyLambdaQueryWrapper).isEmpty()){
                StudentDto studentDto = new StudentDto(student);
                Class cla = classService.getById(student.getClassId());
                String deptName = departmentService.getById(cla.getDeptId()).getName();
                studentDto.setClassName(cla.getName());
                studentDto.setDeptName(deptName);
                studentDtoList.add(studentDto);
            }

        }

        return ResultBody.success(studentDtoList);

    }


    //n天内一直在校未曾出校的学生
    @GetMapping("/in_long/school/{num}")
    public ResultBody getInLongInSchool(@PathVariable int num){
        //筛选出未出校的学生
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getInOutState,Constants.IN);
        List<Student> studentList = studentService.list(studentLambdaQueryWrapper);

        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : studentList){
            LambdaQueryWrapper<ExitRecord> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.ge(ExitRecord::getTime,new Timestamp(System.currentTimeMillis() - 86400000L * num));
            queryWrapper.eq(ExitRecord::getStuId,student.getStuId());
            if (exitRecordService.list(queryWrapper).isEmpty()){
                StudentDto studentDto = new StudentDto(student);
                Class cla = classService.getById(student.getClassId());
                String deptName = departmentService.getById(cla.getDeptId()).getName();
                studentDto.setClassName(cla.getName());
                studentDto.setDeptName(deptName);
                studentDtoList.add(studentDto);
            }
        }

        return ResultBody.success(studentDtoList);
    }

    @GetMapping("/in_long/dept/{deptId}/{num}")
    public ResultBody getInLongInDept(@PathVariable int num,@PathVariable int deptId){
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getInOutState,Constants.IN);
        studentLambdaQueryWrapper.eq(Student::getDeptId,deptId);
        List<Student> studentList = studentService.list(studentLambdaQueryWrapper);

        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : studentList){
            LambdaQueryWrapper<ExitRecord> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.ge(ExitRecord::getTime,new Timestamp(System.currentTimeMillis() - 86400000L * num));
            queryWrapper.eq(ExitRecord::getStuId,student.getStuId());
            if (exitRecordService.list(queryWrapper).isEmpty()){
                StudentDto studentDto = new StudentDto(student);
                Class cla = classService.getById(student.getClassId());
                String deptName = departmentService.getById(cla.getDeptId()).getName();
                studentDto.setClassName(cla.getName());
                studentDto.setDeptName(deptName);
                studentDtoList.add(studentDto);
            }
        }

        return ResultBody.success(studentDtoList);
    }

    @GetMapping("/in_long/class/{classId}/{num}")
    public ResultBody getInLongInClass(@PathVariable int num,@PathVariable int classId){
        LambdaQueryWrapper<Student> studentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        studentLambdaQueryWrapper.eq(Student::getInOutState,Constants.IN);
        studentLambdaQueryWrapper.eq(Student::getClassId,classId);
        List<Student> studentList = studentService.list(studentLambdaQueryWrapper);

        List<StudentDto> studentDtoList = new ArrayList<>();
        for (Student student : studentList){
            LambdaQueryWrapper<ExitRecord> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.ge(ExitRecord::getTime,new Timestamp(System.currentTimeMillis() - 86400000L * num));
            queryWrapper.eq(ExitRecord::getStuId,student.getStuId());
            if (exitRecordService.list(queryWrapper).isEmpty()){
                StudentDto studentDto = new StudentDto(student);
                Class cla = classService.getById(student.getClassId());
                String deptName = departmentService.getById(cla.getDeptId()).getName();
                studentDto.setClassName(cla.getName());
                studentDto.setDeptName(deptName);
                studentDtoList.add(studentDto);
            }
        }

        return ResultBody.success(studentDtoList);
    }




}
