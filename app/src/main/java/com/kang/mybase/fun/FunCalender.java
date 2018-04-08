package com.kang.mybase.fun;

import java.util.Calendar;

/**
 * Created by kanghuicong on 2018/4/8.
 * E-Mail:515849594@qq.com
 */

public class FunCalender {

    private int daysOfMonth = 0;      //某月的天数
    private int dayOfWeek = 0;        //具体某一天是星期几
    private int eachDayOfWeek = 0;

    // 判断是否为闰年
    public boolean isLeapYear(int year) {
        if (year % 100 == 0 && year % 400 == 0) {
            return true;
        } else if (year % 100 != 0 && year % 4 == 0) {
            return true;
        }
        return false;
    }

    //得到某月有多少天数
    public int getDaysOfMonth(boolean isLeapyear, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysOfMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysOfMonth = 30;
                break;
            case 2:
                if (isLeapyear) {
                    daysOfMonth = 29;
                } else {
                    daysOfMonth = 28;
                }

        }
        return daysOfMonth;
    }

    //指定某年中的某月的第一天是星期几
    public int getWeekdayOfMonth(int year, int month){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, 1);
        dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
        return dayOfWeek==0?7:dayOfWeek;
    }
    public int getWeekDayOfLastMonth(int year,int month,int day){
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day);
        eachDayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
        return eachDayOfWeek==0?7:eachDayOfWeek;
    }

    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public int getMonthDays(int year, int month) {
        if (month > 12) {
            month = 1;
            year += 1;
        } else if (month < 1) {
            month = 12;
            year -= 1;
        }
        int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 0;

        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            arr[1] = 29;
        }

        try {
            days = arr[month - 1];
        } catch (Exception e) {
            e.getStackTrace();
        }
        return days;
    }


}
