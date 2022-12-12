package com.example.safefdu.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.safefdu.dao.DepartmentDao;
import com.example.safefdu.dao.EntryRecordDao;
import com.example.safefdu.entity.Department;
import com.example.safefdu.entity.EntryRecord;
import com.example.safefdu.service.EntryRecordService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service
public class EntryRecordServiceImpl extends ServiceImpl<EntryRecordDao, EntryRecord> implements EntryRecordService {
}
