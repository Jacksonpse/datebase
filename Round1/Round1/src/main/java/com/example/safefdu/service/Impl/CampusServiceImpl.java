package com.example.safefdu.service.Impl;

import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.safefdu.dao.CampusDao;
import com.example.safefdu.entity.Campus;
import com.example.safefdu.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;


@Service
public class CampusServiceImpl extends ServiceImpl<CampusDao, Campus> implements CampusService {

    @Autowired
    private CampusDao campusDao;
    @Override
    public List<Pair<String, Long>> getCampusByDept(int deptId, Timestamp beginTime) {
        return campusDao.getCampusByDept(deptId,beginTime);
    }
}
