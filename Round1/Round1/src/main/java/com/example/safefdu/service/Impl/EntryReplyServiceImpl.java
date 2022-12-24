package com.example.safefdu.service.Impl;

import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.safefdu.Exception.ServiceException;
import com.example.safefdu.common.Constants;
import com.example.safefdu.dao.EntryReplyDao;
import com.example.safefdu.dao.StudentDao;
import com.example.safefdu.entity.EntryReply;
import com.example.safefdu.entity.ExitApply;
import com.example.safefdu.entity.Student;
import com.example.safefdu.service.EntryReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class EntryReplyServiceImpl extends ServiceImpl<EntryReplyDao, EntryReply> implements EntryReplyService {
    @Autowired
    private EntryReplyDao entryReplyDao;


    @Autowired
    private StudentDao studentDao;

    @Override
    public boolean save(EntryReply entity) {
        if (studentDao.selectById(entity.getStuId()) == null)
            throw new ServiceException(Constants.CODE_600,"未找到该学生");

        return super.save(entity);
    }

    @Override
    public List<EntryReply> getEntryReply(boolean all, String stuId, long beginTime,int state) {
        LambdaQueryWrapper<EntryReply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(EntryReply::getApplyTime,beginTime);
        queryWrapper.orderByDesc(EntryReply::getApplyTime);
        if (state == Constants.REFUSE)
            queryWrapper.eq(EntryReply::getCheckStage,Constants.TEACHERREFUSE).or()
                    .eq(EntryReply::getCheckStage,Constants.CLASSREFUSE);
        else if (state != Constants.ALL)
            queryWrapper.eq(EntryReply::getCheckStage,state);

        //queryWrapper.last("limit 0," + count);
        if (!all){
            queryWrapper.eq(EntryReply::getStuId,stuId);
        }
        List<EntryReply> list = entryReplyDao.selectList(queryWrapper);
        list.sort(new Comparator<EntryReply>() {
            @Override
            public int compare(EntryReply o1, EntryReply o2) {
                return (int) (o1.getApplyTime().getTime() - o2.getApplyTime().getTime());
            }
        });

        return list;
    }

    @Override
    public List<Pair<String,Long>> getMaxInSchool() {
        List<Pair<String,Long>> countList = entryReplyDao.getCountInSchool();
        int point = -1;
        for (int i = 0;i < countList.size(); i++){
            if (Objects.equals(countList.get(i).getValue(), countList.get(0).getValue()))
                point++;
        }
        return countList.subList(0,point + 1);
    }

    @Override
    public List<Pair<String,Long>> getMaxInDept(int id) {
        List<Pair<String,Long>> countList = entryReplyDao.getCountInDept(id);
        int point = -1;
        for (int i = 0;i < countList.size(); i++){
            if (Objects.equals(countList.get(i).getValue(), countList.get(0).getValue()))
                point++;
        }
        return countList.subList(0,point + 1);
    }

    @Override
    public List<Pair<String,Long>> getMaxInClass(int id) {
        List<Pair<String,Long>> countList = entryReplyDao.getCountInClass(id);
        int point = -1;
        for (int i = 0;i < countList.size(); i++){
            if (Objects.equals(countList.get(i).getValue(), countList.get(0).getValue()))
                point++;
        }
        return countList.subList(0,point + 1);
    }
}
