package com.example.safefdu.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.safefdu.dao.InstructorDao;
import com.example.safefdu.entity.Instructor;
import com.example.safefdu.service.InstructorService;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl extends ServiceImpl<InstructorDao, Instructor> implements InstructorService {
}
