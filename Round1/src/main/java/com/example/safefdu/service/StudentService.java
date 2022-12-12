package com.example.safefdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.safefdu.entity.Student;

public interface StudentService extends IService<Student> {
    public int getTimeOfOut(long beginTime);
}
