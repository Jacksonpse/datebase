package com.example.safefdu.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.safefdu.dao.CampusDao;
import com.example.safefdu.entity.Campus;
import com.example.safefdu.service.CampusService;
import org.springframework.stereotype.Service;


@Service
public class CampusServiceImpl extends ServiceImpl<CampusDao, Campus> implements CampusService {
}
