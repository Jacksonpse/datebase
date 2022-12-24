package com.example.safefdu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.safefdu.entity.ExitApply;

import java.util.List;

public interface ExitApplyService extends IService<ExitApply> {

    public List<ExitApply> getExitApply(boolean all,String stuId,long beginTime,int state);
}
