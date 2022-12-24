package com.example.safefdu.service;

import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.safefdu.entity.Campus;

import java.sql.Timestamp;
import java.util.List;

public interface CampusService extends IService<Campus> {
    public List<Pair<String,Long>> getCampusByDept(int deptId, Timestamp beginTime);
}
