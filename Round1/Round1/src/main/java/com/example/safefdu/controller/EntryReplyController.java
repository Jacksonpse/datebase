package com.example.safefdu.controller;

import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.safefdu.Dto.StudentDto;
import com.example.safefdu.annoation.StuIdCheck;
import com.example.safefdu.common.Constants;
import com.example.safefdu.common.ResultBody;
import com.example.safefdu.entity.Class;
import com.example.safefdu.entity.EntryReply;
import com.example.safefdu.entity.Student;
import com.example.safefdu.service.ClassService;
import com.example.safefdu.service.DepartmentService;
import com.example.safefdu.service.EntryReplyService;
import com.example.safefdu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/entryreply")
public class EntryReplyController {
    @Autowired
    private EntryReplyService entryReplyService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    @Autowired
    private DepartmentService departmentService;

    @StuIdCheck
    @GetMapping("/get/{id}")
    public ResultBody getAll(@PathVariable String id){
        LambdaQueryWrapper<EntryReply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EntryReply::getStuId,id);
        List<EntryReply> list = entryReplyService.list(queryWrapper);

        return ResultBody.success(list);
    }

    @GetMapping("/get/{id}/{count}")
    public ResultBody getAllCount(@PathVariable String id , @PathVariable int count){
        long beginTime = System.currentTimeMillis() - 86400000L * count;
        List<EntryReply> list = entryReplyService.getEntryReply(true,null,beginTime,Constants.ALL);
        return ResultBody.success(list);

    }

    @StuIdCheck
    @GetMapping("/getaccept/{id}")
    public ResultBody getAccept(@PathVariable String id){
        LambdaQueryWrapper<EntryReply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EntryReply::getStuId,id);
        queryWrapper.eq(EntryReply::getCheckStage, Constants.TEACHERACCEPT);
        List<EntryReply> list = entryReplyService.list(queryWrapper);

        return ResultBody.success(list);
    }

    @StuIdCheck
    @GetMapping("/getrefuse/{id}")
    public ResultBody getRefuse(@PathVariable String id){
        LambdaQueryWrapper<EntryReply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EntryReply::getStuId,id).and(wrapper->wrapper.eq(EntryReply::getCheckStage, Constants.CLASSREFUSE)
                .or().eq(EntryReply::getCheckStage,Constants.TEACHERREFUSE));

        List<EntryReply> list = entryReplyService.list(queryWrapper);

        return ResultBody.success(list);
    }

    @StuIdCheck
    @GetMapping("/getundisposed/{id}")
    public ResultBody getUndisposed(@PathVariable String id){
        LambdaQueryWrapper<EntryReply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EntryReply::getStuId,id);
        queryWrapper.eq(EntryReply::getCheckStage, Constants.UNDISPOSED);
        List<EntryReply> list = entryReplyService.list(queryWrapper);
        return ResultBody.success(list);
    }

    @PostMapping("/insert")
    public ResultBody insert(@RequestBody EntryReply entryReply){
        entryReply.setApplyTime(new Timestamp(System.currentTimeMillis()));
        entryReplyService.save(entryReply);
        return ResultBody.success();
    }

    @GetMapping("undisposed/{id}/{n}")
    public ResultBody getUndisposed(@PathVariable String id,@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<EntryReply> list = entryReplyService.getEntryReply(false,id,beginTime,Constants.UNDISPOSED);
        return ResultBody.success(list);
    }

    @GetMapping("/undisposed/{n}")
    public ResultBody getUndisposedList(@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<EntryReply> list = entryReplyService.getEntryReply(true,null,beginTime,Constants.UNDISPOSED);
        return ResultBody.success(list);
    }

    @GetMapping("/accepted/{n}")
    public ResultBody getAcceptedList(@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<EntryReply> list = entryReplyService.getEntryReply(true,null,beginTime,Constants.TEACHERACCEPT);
        return ResultBody.success(list);
    }

    @GetMapping("accepted/{id}/{n}")
    public ResultBody getAccepted(@PathVariable String id,@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<EntryReply> list = entryReplyService.getEntryReply(false,id,beginTime,Constants.TEACHERACCEPT);
        return ResultBody.success(list);
    }

    @GetMapping("/refused/{n}")
    public ResultBody getRefusedList(@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<EntryReply> list = entryReplyService.getEntryReply(true,null,beginTime,Constants.REFUSE);
        return ResultBody.success(list);
    }

    @GetMapping("/refused/{id}/{n}")
    public ResultBody getRefused(@PathVariable String id,@PathVariable int n){
        long beginTime = System.currentTimeMillis() - 86400000L * n;
        List<EntryReply> list = entryReplyService.getEntryReply(false,id,beginTime,Constants.REFUSE);
        return ResultBody.success(list);
    }

    @GetMapping("/max/school")
    public ResultBody getMaxInSchool(){
        List<Pair<String,Long>> stuIdList = entryReplyService.getMaxInSchool();
        List<StudentDto> students = new ArrayList<>();
        for (Pair<String,Long> stuCount : stuIdList){
            Student student = studentService.getById(stuCount.getKey());
            StudentDto studentDto = new StudentDto(student);
            Class cla = classService.getById(student.getClassId());
            String deptName = departmentService.getById(cla.getDeptId()).getName();
            studentDto.setClassName(cla.getName());
            studentDto.setDeptName(deptName);
            //System.out.println(stuCount.getValue());
            studentDto.setEntryReplyNum(stuCount.getValue());
            students.add(studentDto);
        }

        return ResultBody.success(students);
    }

    @GetMapping("/max/dept/{id}")
    public ResultBody getMaxInDept(@PathVariable int id){
        List<Pair<String,Long>> stuIdList = entryReplyService.getMaxInDept(id);
        List<StudentDto> students = new ArrayList<>();
        for (Pair<String,Long> stuCount : stuIdList){
            Student student = studentService.getById(stuCount.getKey());
            StudentDto studentDto = new StudentDto(student);
            Class cla = classService.getById(student.getClassId());
            String deptName = departmentService.getById(cla.getDeptId()).getName();
            studentDto.setClassName(cla.getName());
            studentDto.setDeptName(deptName);
            //System.out.println(stuCount.getValue());
            studentDto.setEntryReplyNum(stuCount.getValue());
            students.add(studentDto);
        }

        return ResultBody.success(students);
    }

    @GetMapping("/max/class/{id}")
    public ResultBody getMaxInClass(@PathVariable int id){
        List<Pair<String,Long>> stuIdList = entryReplyService.getMaxInClass(id);
        List<StudentDto> students = new ArrayList<>();
        for (Pair<String,Long> stuCount : stuIdList){
            Student student = studentService.getById(stuCount.getKey());
            StudentDto studentDto = new StudentDto(student);
            Class cla = classService.getById(student.getClassId());
            String deptName = departmentService.getById(cla.getDeptId()).getName();
            studentDto.setClassName(cla.getName());
            studentDto.setDeptName(deptName);
            //System.out.println(stuCount.getValue());
            studentDto.setEntryReplyNum(stuCount.getValue());
            students.add(studentDto);
        }

        return ResultBody.success(students);
    }

}
