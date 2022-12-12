package com.example.safefdu.aspect;

import com.example.safefdu.Exception.ServiceException;
import com.example.safefdu.common.Constants;
import com.example.safefdu.service.StudentService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentAspect {

    @Autowired
    StudentService studentService;

    //定义切面表达式
    @Pointcut("@annotation(com.example.safefdu.annoation.StuIdCheck)")
    public void StuIdCheck(){}

    @Around(value = "StuIdCheck()")
    public Object StudentAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        if (studentService.getById((String)args[0]) == null){
            throw new ServiceException(Constants.CODE_600,"未找到该学生");
        }
        return pjp.proceed();
    }
}
