package com.example.safefdu.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SystemTimeUtils {
    public static Integer getSysYear() {
        Calendar date = Calendar.getInstance();
        Integer year = Integer.valueOf(date.get(Calendar.YEAR));
        return year;
    }

    // 1代表春，2代表夏，3代表秋
    public static Integer getSemester(){
        Calendar date = Calendar.getInstance();
        // month莫名奇妙少了1，即1月是0开头,所以作为数组下标刚刚好
        Integer month = date.get(Calendar.MONTH);
        int[] arrayA = new int[]{3,0,1,1,1,1,2,2,3,3,3,3};
        return arrayA[month];
    }
}
