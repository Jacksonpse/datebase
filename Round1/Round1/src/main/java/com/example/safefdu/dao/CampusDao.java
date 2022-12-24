package com.example.safefdu.dao;

import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.safefdu.entity.Campus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;


@Mapper
public interface CampusDao extends BaseMapper<Campus> {

    @Select("select name,count(*) as num from student as s,entry_record as en full join exit_record as ex " +
            "where s.stu_id = en.stu_id and s.stu_id = ex.stu_id and s.dept_id = #{dept_id} and en.time >= #{begin_time} and ex.time >= #{begin_time} " +
            "group by campus_name order by num desc")
    public List<Pair<String,Long>> getCampusByDept(int deptId, Timestamp beginTime);
}
