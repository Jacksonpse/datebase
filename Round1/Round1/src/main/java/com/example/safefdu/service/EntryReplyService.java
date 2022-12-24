package com.example.safefdu.service;

import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.safefdu.entity.EntryReply;

import java.util.List;

public interface EntryReplyService extends IService<EntryReply> {
    public List<EntryReply> getEntryReply(boolean all,String stuId,long beginTime,int state);

    public List<Pair<String,Long>> getMaxInSchool();

    public List<Pair<String,Long>> getMaxInDept(int id);

    public List<Pair<String,Long>> getMaxInClass(int id);
}
