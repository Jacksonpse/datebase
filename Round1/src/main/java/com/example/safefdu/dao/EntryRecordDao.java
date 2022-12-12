package com.example.safefdu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.safefdu.entity.EntryRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EntryRecordDao extends BaseMapper<EntryRecord> {
}
