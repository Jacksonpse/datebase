package com.example.safefdu.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.safefdu.dao.ClassDao;
import com.example.safefdu.entity.Class;
import com.example.safefdu.service.ClassService;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassDao,Class> implements ClassService {
}
