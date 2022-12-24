package com.example.safefdu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.safefdu.entity.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentDao extends BaseMapper<Department> {
}
