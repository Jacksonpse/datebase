package com.time;

import com.example.safefdu.SafefduApplication;
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
        System.out.println(new Timestamp(System.currentTimeMillis()));
    }
}
