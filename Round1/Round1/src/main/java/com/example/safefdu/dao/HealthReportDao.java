package com.example.safefdu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.safefdu.entity.HealthReport;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HealthReportDao extends MppBaseMapper<HealthReport> {

    @Select("")
    public List<String> getSame();
}
