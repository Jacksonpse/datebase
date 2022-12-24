package com.example.safefdu.Dto;

import com.example.safefdu.entity.Student;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class StudentDto{
    private String stuId;
    private String name;
    private String phone;
    private String email;
    private String building;
    private String homeAddr;
    private String idCardType;
    private String idCardNum;
    private int admissionLevel;
    private int inOutState;
    private String campName;
    private String className;
    private String deptName;

    private int classId;

    private int deptId;


    private Timestamp outTime;

    private long entryReplyNum;

    public StudentDto(){

    }
    public StudentDto(Student student){
        stuId = student.getStuId();
        name = student.getName();
        phone = student.getPhone();
        email = student.getEmail();
        building = student.getBuilding();
        homeAddr = student.getHomeAddr();
        idCardNum = student.getIdCardNum();
        idCardType = student.getIdCardType();
        admissionLevel = student.getAdmissionLevel();
        inOutState = student.getInOutState();
        campName = student.getCampName();
        deptId = student.getDeptId();
        classId = student.getClassId();
    }
}
