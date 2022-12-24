package com.time;

import cn.hutool.core.lang.Pair;
import com.example.safefdu.SafefduApplication;
import com.example.safefdu.dao.EntryReplyDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SafefduApplication.class)
public class DaoTest {

    @Autowired
    private EntryReplyDao entryReplyDao;

    @Test
    public void entryReplyTest(){
        //System.out.println(entryReplyDao.getMaxInSchool());
        Pair<String,Integer> pair = new Pair<>("eqwe",2);
        System.out.println(pair.getValue().getClass().toString());
    }

    @Test
    public void t(){
        System.out.println(entryReplyDao.getCountInDept(0));
    }
}
