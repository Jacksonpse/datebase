package com.example.safefdu.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.safefdu.dao.DepartmentDao;
import com.example.safefdu.entity.Department;
import com.example.safefdu.service.DepartmentService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentDao, Department> implements DepartmentService {
}
