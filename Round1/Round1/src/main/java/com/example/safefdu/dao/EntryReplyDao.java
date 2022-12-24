package com.example.safefdu.dao;

import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.safefdu.entity.EntryReply;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EntryReplyDao extends MppBaseMapper<EntryReply> {
    @Select("select stu_id,count(apply_time) as num from entry_reply group by stu_id order by num DESC")
    public List<Pair<String,Long>> getCountInSchool();

    @Select("select stu_id,count(apply_time) as num from entry_reply where stu_id in (select stu_id from student where dept_id = #{id}) group by stu_id order by num DESC")
    public List<Pair<String,Long>> getCountInDept(int id);

    @Select("select stu_id,count(apply_time) as num from entry_reply where stu_id in (select stu_id from student where class_id = #{id}) group by stu_id order by num DESC")
    public List<Pair<String,Long>> getCountInClass(int id);

}
