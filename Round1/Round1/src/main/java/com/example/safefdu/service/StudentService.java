package com.example.safefdu.service;

import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.safefdu.entity.Student;

import java.util.List;

public interface StudentService extends IService<Student> {
    public long getTimeOfOut(long beginTime,String StuId);

    public List<Pair<Student, Long>> getTimeOfOutListBySchool(long beginTime,int count);

    public List<Pair<Student, Long>> getTimeOfOutListByDept(long beginTime,int deptId,int count);

    public List<Pair<Student, Long>> getTimeOfOutListByClass(long beginTime,int classId,int count);
}
