package com.time;

import com.example.safefdu.SafefduApplication;
import com.example.safefdu.common.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;
import java.util.Date;

@SpringBootTest(classes = SafefduApplication.class)
@ContextConfiguration
public class TimeTest {
    @org.junit.jupiter.api.Test
    public void test(){
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        date.setYear(date.getYear() - 1);
        System.out.println(new Date(time));
        System.out.println(date);
    }

    @Test
    public void ts(){
        long t = System.currentTimeMillis();
        System.out.println(new Date(t));
        System.out.println(new Date(t - 86400000));
    }

    @Test
    public void t(){
        Timestamp timestamp = new Timestamp(2022,12,17,20,31,0,0);
        //86400000L
        Timestamp t = new Timestamp(2022,12,16,20,31,0,0);
        System.out.println(timestamp.getTime() - t.getTime());
    }

    @Test
    public void tss(){
        long t = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(t);
        timestamp.setMinutes(0);
        timestamp.setSeconds(0);
        timestamp.setHours(0);
        timestamp.setNanos(0);
        System.out.println(timestamp.getTime());
        System.out.println(new Timestamp(timestamp.getTime() + Constants.ONEDAY).getTime());
    }
}
