package com.example.safefdu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.safefdu.Exception.ServiceException;
import com.example.safefdu.common.Constants;
import com.example.safefdu.dao.ExitApplyDao;
import com.example.safefdu.dao.StudentDao;
import com.example.safefdu.entity.EntryReply;
import com.example.safefdu.entity.ExitApply;
import com.example.safefdu.service.ExitApplyService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class ExitApplyServiceImpl extends ServiceImpl<ExitApplyDao, ExitApply> implements ExitApplyService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private ExitApplyDao exitApplyDao;

    @Override
    public boolean save(ExitApply entity) {
        if (studentDao.selectById(entity.getStuId()) == null)
            throw new ServiceException(Constants.CODE_600,"未找到该学生");

        return super.save(entity);
    }

    @Override
    public List<ExitApply> getExitApply(boolean all, String stuId,long beginTime,int state) {
        System.out.println(new Date(beginTime));
        LambdaQueryWrapper<ExitApply> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.ge(ExitApply::getApplyTime,new Timestamp(beginTime));
        queryWrapper.orderByDesc(ExitApply::getApplyTime);
        if (state == Constants.REFUSE)
            queryWrapper.eq(ExitApply::getCheckStage,Constants.TEACHERREFUSE).or()
                    .eq(ExitApply::getCheckStage,Constants.CLASSREFUSE);
        else if (state != Constants.ALL)
            queryWrapper.eq(ExitApply::getCheckStage,state);
        //queryWrapper.last("limit 0," + count);
        if (!all){
            queryWrapper.eq(ExitApply::getStuId,stuId);
        }
        List<ExitApply> list = exitApplyDao.selectList(queryWrapper);
        list.sort(new Comparator<ExitApply>() {
            @Override
            public int compare(ExitApply o1, ExitApply o2) {
                return (int) (o1.getApplyTime().getTime() - o2.getApplyTime().getTime());
            }
        });

        return list;
    }

}
