package com.example.safefdu.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.safefdu.dao.EntryRecordDao;
import com.example.safefdu.dao.ExitRecordDao;
import com.example.safefdu.dao.StudentDao;
import com.example.safefdu.entity.EntryRecord;
import com.example.safefdu.entity.ExitRecord;
import com.example.safefdu.entity.Student;
import com.example.safefdu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {
    @Autowired
    private EntryRecordDao entryRecordDao;
    @Autowired
    private ExitRecordDao exitRecordDao;
    @Override
    public int getTimeOfOut(long beginTime) {
        int time = 0;
        LambdaQueryWrapper<EntryRecord> entryRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<ExitRecord> exitRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
        exitRecordLambdaQueryWrapper.ge(ExitRecord::getTime,new Timestamp(beginTime));
        entryRecordLambdaQueryWrapper.ge(EntryRecord::getTime,new Timestamp(beginTime));
        List<EntryRecord> entryRecords = entryRecordDao.selectList(entryRecordLambdaQueryWrapper);
        List<ExitRecord> exitRecords = exitRecordDao.selectList(exitRecordLambdaQueryWrapper);

        if (entryRecords.isEmpty() || exitRecords.isEmpty())
            return time;

        if (entryRecords.get(0).getTime().getTime() < exitRecords.get(0).getTime().getTime()){
            time += entryRecords.get(0).getTime().getTime() - beginTime;
            for (int i = 1; i < entryRecords.size(); i++){
                time +=  entryRecords.get(i).getTime().getTime() - exitRecords.get(i - 1).getTime().getTime();
            }
        }else{
            for (int i = 0; i < entryRecords.size(); i++){
                time +=  entryRecords.get(i).getTime().getTime() - exitRecords.get(i).getTime().getTime();
            }
        }

        return time;
    }
}
