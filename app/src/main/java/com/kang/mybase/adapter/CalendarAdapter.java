package com.kang.mybase.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kang.mybase.R;
import com.kang.mybase.fun.FunCalender;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.kang.utilssdk.SizeUtils.dp2px;

/**
 * Created by kanghuicong on 2018/4/8.
 * E-Mail:515849594@qq.com
 */

public class CalendarAdapter extends BaseAdapter {
    private boolean isLeapyear = false; // 是否为闰年
    private int daysOfMonth = 0; // 某月的天数
    private int dayOfWeek = 0; // 具体某一天是星期几
    private int nextDayOfWeek = 0;
    private int lastDayOfWeek = 0;
    private int lastDaysOfMonth = 0; // 上一个月的总天数
    private Context context;
    private FunCalender sc = null;
    private Resources res = null;
    private String[] dayNumber = new String[7];
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
    private int currentFlag = -1; // 用于标记当天
    // 系统当前时间
    private String sysDate = "";
    private String sys_year = "";
    private String sys_month = "";
    private String sys_day = "";
    private String currentYear = "";
    private String currentMonth = "";
    private String currentWeek = "";
    private String currentDay = "";
    private int default_postion;
    private int clickTemp = -1;
    private int week_c = 0;
    private int month = 0;
    private boolean isStart;

    // 标识选择的Item
    public void setSeclection(int position) {
        clickTemp = position;
    }

    public CalendarAdapter() {
        Date date = new Date();
        sysDate = sdf.format(date); // 当期日期
        sys_year = sysDate.split("-")[0];
        sys_month = sysDate.split("-")[1];
        sys_day = sysDate.split("-")[2];
        month = Integer.parseInt(sys_month);
    }

    public CalendarAdapter(Context context, Resources rs, int year_c, int month_c,
                       int week_c, int week_num, int default_postion, boolean isStart) {
        this();
        this.context = context;
        this.res = rs;
        this.default_postion = default_postion;
        this.week_c = week_c;
        this.isStart = isStart;
        sc = new FunCalender();

        lastDayOfWeek = sc.getWeekDayOfLastMonth(year_c, month_c,
                sc.getDaysOfMonth(sc.isLeapYear(year_c), month_c));

        currentYear = String.valueOf(year_c);
        // 得到当前的年份
        currentMonth = String.valueOf(month_c); // 得到本月

        currentDay = String.valueOf(sys_day); // 得到当前日期是哪天
        getCalendar(Integer.parseInt(currentYear),
                Integer.parseInt(currentMonth));
        currentWeek = String.valueOf(week_c);
        getWeek(Integer.parseInt(currentYear), Integer.parseInt(currentMonth),
                Integer.parseInt(currentWeek));

    }

    public int getTodayPosition() {
        int todayWeek = sc.getWeekDayOfLastMonth(Integer.parseInt(sys_year),
                Integer.parseInt(sys_month), Integer.parseInt(sys_day));
        clickTemp = todayWeek-1;
        return todayWeek-1;
    }

    public int getCurrentMonth(int position) {
        int thisDayOfWeek = sc.getWeekdayOfMonth(Integer.parseInt(currentYear),
                Integer.parseInt(currentMonth));
        if (isStart) {
            if (thisDayOfWeek != 1) {
                if (position+1 < thisDayOfWeek) {
                    return Integer.parseInt(currentMonth) - 1 == 0 ? 12
                            : Integer.parseInt(currentMonth) - 1;
                } else {
                    return Integer.parseInt(currentMonth);
                }
            } else {
                return Integer.parseInt(currentMonth);
            }
        } else {
            return Integer.parseInt(currentMonth);
        }

    }

    public int getCurrentYear(int position) {
        int thisDayOfWeek = sc.getWeekdayOfMonth(Integer.parseInt(currentYear),
                Integer.parseInt(currentMonth));
        if (isStart) {
            if (thisDayOfWeek != 1) {
                if (position+1 < thisDayOfWeek) {
                    return Integer.parseInt(currentMonth) - 1 == 0 ? Integer
                            .parseInt(currentYear) - 1 : Integer
                            .parseInt(currentYear);
                } else {
                    return Integer.parseInt(currentYear);
                }
            } else {
                return Integer.parseInt(currentYear);
            }
        } else {
            return Integer.parseInt(currentYear);
        }
    }

    public void getCalendar(int year, int month) {
        isLeapyear = sc.isLeapYear(year); // 是否为闰年
        daysOfMonth = sc.getDaysOfMonth(isLeapyear, month); // 某月的总天数
        dayOfWeek = sc.getWeekdayOfMonth(year, month); // 某月第一天为星期几
        lastDaysOfMonth = sc.getDaysOfMonth(isLeapyear, month - 1);
        nextDayOfWeek = sc.getDaysOfMonth(isLeapyear, month + 1);
    }

    public void getWeek(int year, int month, int week) {
        int n = 0;
        for (int i = 0; i < dayNumber.length; i++) {
            if (dayOfWeek == 1) {
                dayNumber[i] = String.valueOf((i + 1) + 7 * (week - 1));
            } else {
                if (week == 1) {
                    if (i < dayOfWeek-1) {
                        dayNumber[i] = String.valueOf(lastDaysOfMonth
                                - (dayOfWeek - (i+2)));
                    } else {
                        dayNumber[i] = String.valueOf(i - dayOfWeek + 2);
                    }
                } else {
                    if (((7 - dayOfWeek + 2 + i) + 7 * (week - 2))<=daysOfMonth)
                        dayNumber[i] = String.valueOf((7 - dayOfWeek + 2 + i) + 7
                                * (week - 2));
                    else {
                        n++;
                        dayNumber[i]=n+"";
                    }
                }
            }
        }
    }

    public String[] getDayNumbers() {
        return dayNumber;
    }

    @Override
    public int getCount() {return dayNumber.length;}

    @Override
    public Object getItem(int position) {return position;}

    @Override
    public long getItemId(int position) {return position;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LinearLayout layout = new LinearLayout(context);
            layout.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, dp2px(30)));
            layout.setGravity(Gravity.CENTER);

            TextView tvCalendar = new TextView(context);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(dp2px(20), dp2px(20));
            lp.setMargins(0, dp2px(10), 0, dp2px(10));
            tvCalendar.setLayoutParams(lp);
            tvCalendar.setGravity(Gravity.CENTER);
            tvCalendar.setTextSize(12);

            layout.addView(tvCalendar);
            convertView = layout;
        }

        TextView tvCalendar = (TextView) (((LinearLayout) convertView).getChildAt(0));
        tvCalendar.setText(dayNumber[position]);
        if (clickTemp == position) {
            tvCalendar.setSelected(true);
            tvCalendar.setTextColor(Color.WHITE);
            tvCalendar.setBackgroundResource(R.drawable.calendar_background);
        } else {
            tvCalendar.setSelected(false);
            if (position==5||position==6)
                tvCalendar.setTextColor(context.getResources().getColor(R.color.content_text));
            else tvCalendar.setTextColor(Color.BLACK);
            tvCalendar.setBackgroundColor(Color.TRANSPARENT);
        }
        return convertView;
    }

}
