package com.example.safefdu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.safefdu.entity.Instructor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InstructorDao extends BaseMapper<Instructor> {
}
