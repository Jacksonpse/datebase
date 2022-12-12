package com.example.safefdu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.safefdu.entity.EntryReply;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EntryReplyDao extends MppBaseMapper<EntryReply> {
}
