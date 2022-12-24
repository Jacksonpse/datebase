package com.example.safefdu.service.Impl;

import cn.hutool.core.lang.Pair;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {
    @Autowired
    private EntryRecordDao entryRecordDao;
    @Autowired
    private ExitRecordDao exitRecordDao;

    @Autowired
    private StudentDao studentDao;
    @Override
    public long getTimeOfOut(long beginTime, String stuId) {
        int time = 0;
        LambdaQueryWrapper<EntryRecord> entryRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<ExitRecord> exitRecordLambdaQueryWrapper = new LambdaQueryWrapper<>();

        exitRecordLambdaQueryWrapper.eq(ExitRecord::getStuId,stuId);
        entryRecordLambdaQueryWrapper.eq(EntryRecord::getStuId,stuId);
        if (beginTime > 0) {
            exitRecordLambdaQueryWrapper.ge(ExitRecord::getTime, new Timestamp(beginTime));
            entryRecordLambdaQueryWrapper.ge(EntryRecord::getTime, new Timestamp(beginTime));
        }
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

    @Override
    public List<Pair<Student, Long>> getTimeOfOutListBySchool(long beginTime,int count) {
        List<Student> students = studentDao.selectList(null);
        List<Pair<Student, Long>> list = new ArrayList<>();
        for (Student student : students){
            Pair<Student,Long> pair = new Pair<>(student,getTimeOfOut(beginTime,student.getStuId()));
            list.add(pair);
        }
        list.sort(new Comparator<Pair<Student, Long>>() {
            @Override
            public int compare(Pair<Student, Long> o1, Pair<Student, Long> o2) {
                return (int) (o1.getValue() - o2.getValue());
            }
        });
        return list.subList(0,count);
    }

    @Override
    public List<Pair<Student, Long>> getTimeOfOutListByDept(long beginTime, int deptId,int count) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getDeptId,deptId);
        List<Student> students = studentDao.selectList(queryWrapper);
        List<Pair<Student, Long>> list = new ArrayList<>();
        for (Student student : students){
            Pair<Student,Long> pair = new Pair<>(student,getTimeOfOut(beginTime,student.getStuId()));
            list.add(pair);
        }
        list.sort(new Comparator<Pair<Student, Long>>() {
            @Override
            public int compare(Pair<Student, Long> o1, Pair<Student, Long> o2) {
                return (int) (o1.getValue() - o2.getValue());
            }
        });
        return list.subList(0,count);
    }

    @Override
    public List<Pair<Student, Long>> getTimeOfOutListByClass(long beginTime, int classId,int count) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Student::getClassId,classId);
        List<Student> students = studentDao.selectList(queryWrapper);
        List<Pair<Student, Long>> list = new ArrayList<>();
        for (Student student : students){
            Pair<Student,Long> pair = new Pair<>(student,getTimeOfOut(beginTime,student.getStuId()));
            list.add(pair);
        }
        list.sort(new Comparator<Pair<Student, Long>>() {
            @Override
            public int compare(Pair<Student, Long> o1, Pair<Student, Long> o2) {
                return (int) (o1.getValue() - o2.getValue());
            }
        });
        return list.subList(0,count);
    }
}
