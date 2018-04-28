package com.kang.mybase.custom.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.kang.mybase.R;
import com.kang.mybase.adapter.CalendarAdapter;
import com.kang.mybase.fun.FunCalender;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.kang.utilssdk.SizeUtils.dp2px;

/**
 * Created by kanghuicong on 2018/4/8.
 * E-Mail:515849594@qq.com
 */

public class MyCalendarView extends LinearLayout implements GestureDetector.OnGestureListener {
    String[] weekArr = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};

    FunCalender funCalender;
    private TextView tvYear, tvMonth;
    private ViewFlipper viewFlipper;

    private int currentYear = 0;
    private int currentMonth = 0;
    private int currentWeek;

    private int currentDay = 0;
    private int currentWeekNum;
    private boolean isLeapyear = false; // 是否为闰年
    private int weeksOfMonth = 0;//有几周

    public MyCalendarView(Context context) {
        this(context, null);
    }

    private GestureDetector gestureDetector = null;
    CalendarAdapter calendarAdapter;

    public MyCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        this.setOrientation(VERTICAL);

        LinearLayout layout1 = new LinearLayout(context);
        layout1.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout1.setPadding(dp2px(5), dp2px(5), dp2px(5), dp2px(5));
        layout1.setBackgroundColor(getResources().getColor(R.color.main_color));
        layout1.setOrientation(HORIZONTAL);

        tvMonth = new TextView(context);
        tvMonth.setTextColor(getResources().getColor(R.color.White));
        tvMonth.setTextSize(20);
        layout1.addView(tvMonth);

        tvYear = new TextView(context);
        tvYear.setTextColor(getResources().getColor(R.color.White));
        tvYear.setTextSize(12);
        layout1.addView(tvYear);

        addView(layout1);

        LinearLayout layout2 = new LinearLayout(context);
        layout2.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        layout2.setPadding(0, 0, 0, dp2px(5));
        layout2.setBackgroundColor(getResources().getColor(R.color.main_color));
        layout2.setOrientation(HORIZONTAL);

        for (int i = 0; i < 7; i++) {
            TextView tvDay = new TextView(context);
            tvDay.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1));
            tvDay.setGravity(Gravity.CENTER);
            tvDay.setText(weekArr[i]);
            tvDay.setTextColor(getResources().getColor(R.color.White));
            tvDay.setTextSize(12);
            layout2.addView(tvDay);
        }

        addView(layout2);

        viewFlipper = new ViewFlipper(context);
        viewFlipper.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        addView(viewFlipper);

        /*-------------------------------------------------------------------------------------------------------*/
        initToday();
        gestureDetector = new GestureDetector(this);
        calendarAdapter = new CalendarAdapter(context, getResources(), currentYear,
                currentMonth, currentWeek, currentWeekNum, selectPosition,
                currentWeek == 1 ? true : false);

        addGridView();
        dayNumbers = calendarAdapter.getDayNumbers();
        gridView.setAdapter(calendarAdapter);
        selectPosition = calendarAdapter.getTodayPosition();
        selectToday = calendarAdapter.getTodayPosition();
        gridView.setSelection(selectPosition);
        viewFlipper.addView(gridView, 0);
    }


    private void initToday() {
        String todayDate = "";
        funCalender = new FunCalender();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        todayDate = sdf.format(date);

        currentYear = Integer.parseInt(todayDate.split("-")[0]);
        currentMonth = Integer.parseInt(todayDate.split("-")[1]);
        currentDay = Integer.parseInt(todayDate.split("-")[2]);
        currentWeekNum = getWeeksOfMonth(currentYear, currentMonth);

        int firstDayOfWeek = funCalender.getWeekdayOfMonth(currentYear, currentMonth);
        int todayWeek = 0;
        if (firstDayOfWeek == 1) {
            todayWeek = currentDay / 7 + 1;
        } else {
            if (currentDay <= (7 - firstDayOfWeek + 1)) {
                todayWeek = 1;
            } else {
                if ((currentDay - (7 - firstDayOfWeek + 1)) % 7 == 0) {
                    todayWeek = (currentDay - (7 - firstDayOfWeek + 1)) / 7 + 1;
                } else {
                    todayWeek = (currentDay - (7 - firstDayOfWeek + 1)) / 7 + 2;
                }
            }
        }
        currentWeek = todayWeek;

        tvYear.setText(currentYear + "年");
        tvMonth.setText(currentMonth + "月");
    }

    private int selectPosition = 0;/*所选择的那天的position*/
    private int selectToday = 0;/*今天的position*/
    private GridView gridView = null;
    private String dayNumbers[] = new String[7];
    /*加载页面*/
    private void addGridView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        gridView = new GridView(getContext());
        gridView.setNumColumns(7);
        gridView.setGravity(Gravity.CENTER_VERTICAL);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setVerticalSpacing(1);
        gridView.setHorizontalSpacing(1);
        gridView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                selectPosition = position;
                calendarAdapter.setSeclection(position);
                calendarAdapter.notifyDataSetChanged();

                tvYear.setText(calendarAdapter.getCurrentYear(selectPosition) + "年");
                tvMonth.setText(calendarAdapter.getCurrentMonth(selectPosition) + "月");

                if (iCalendarClick != null)
                    iCalendarClick.click(calendarAdapter.getCurrentYear(selectPosition), calendarAdapter.getCurrentMonth(selectPosition), Integer.parseInt(dayNumbers[selectPosition]));
            }
        });
        gridView.setLayoutParams(params);
    }


    /*滑动监听*/
    int i = 0;
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int gvFlag = 0;

        if (e1.getX() - e2.getX() > 80 && Math.abs(velocityX) > 0) {
            i++;
            if (i == 0) selectPosition = selectToday;
            else selectPosition = 0;
            // 向左滑
            addGridView();
            currentWeek++;
            getCurrent();
            calendarAdapter = new CalendarAdapter(getContext(), getResources(), currentYear,
                    currentMonth, currentWeek, currentWeekNum, selectPosition,
                    currentWeek == 1 ? true : false);

            dayNumbers = calendarAdapter.getDayNumbers();
            gridView.setAdapter(calendarAdapter);

            tvYear.setText(calendarAdapter.getCurrentYear(selectPosition) + "年");
            tvMonth.setText(calendarAdapter.getCurrentMonth(selectPosition) + "月");
            if (iCalendarClick != null)
                iCalendarClick.click(calendarAdapter.getCurrentYear(selectPosition), calendarAdapter.getCurrentMonth(selectPosition), Integer.parseInt(dayNumbers[selectPosition]));
            gvFlag++;
            viewFlipper.addView(gridView, gvFlag);
            calendarAdapter.setSeclection(selectPosition);

            this.viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
                    R.anim.push_left_in));
            this.viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
                    R.anim.push_left_out));
            this.viewFlipper.showNext();
            viewFlipper.removeViewAt(0);
            return true;

        } else if (e1.getX() - e2.getX() < -80 && Math.abs(velocityX) > 0) {
            i--;

            if (i == 0) selectPosition = selectToday;
            else selectPosition = 0;

            addGridView();

            currentWeek--;
            getCurrent();
            calendarAdapter = new CalendarAdapter(getContext(), getResources(), currentYear, currentMonth,
                    currentWeek, currentWeekNum, selectPosition, currentWeek == 1 ? true : false);
            dayNumbers = calendarAdapter.getDayNumbers();
            gridView.setAdapter(calendarAdapter);

            tvYear.setText(calendarAdapter.getCurrentYear(selectPosition) + "年");
            tvMonth.setText(calendarAdapter.getCurrentMonth(selectPosition) + "月");
            if (iCalendarClick != null)
                iCalendarClick.click(calendarAdapter.getCurrentYear(selectPosition), calendarAdapter.getCurrentMonth(selectPosition), Integer.parseInt(dayNumbers[selectPosition]));

            gvFlag++;
            viewFlipper.addView(gridView, gvFlag);
            calendarAdapter.setSeclection(selectPosition);
            this.viewFlipper.setInAnimation(AnimationUtils.loadAnimation(getContext(),
                    R.anim.push_right_in));
            this.viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(getContext(),
                    R.anim.push_right_out));
            this.viewFlipper.showPrevious();
            viewFlipper.removeViewAt(0);
            return true;
        }
        return false;
    }

    /*----------------------------------------------------------------------------------------*/

    /*判断currentYear，currentMonth，currentWeek，currentWeekNum*/
    public void getCurrent() {
            /*下个月第一周*/
        if (currentWeek > currentWeekNum) {
            if (currentMonth + 1 <= 12) {
                currentMonth++;
            } else {
                currentMonth = 1;
                currentYear++;
            }
            currentWeek = 1;
            currentWeekNum = getWeeksOfMonth(currentYear, currentMonth);
            /*划到月末，即将进入下一个月*/
        } else if (currentWeek == currentWeekNum) {
            /*判断最后一天是否为周日*/
            if (funCalender.getWeekDayOfLastMonth(currentYear, currentMonth, funCalender.getDaysOfMonth(isLeapyear, currentMonth)) == 7) {
            } else {
                if (currentMonth + 1 <= 12) {
                    currentMonth++;
                } else {
                    currentMonth = 1;
                    currentYear++;
                }
                currentWeek = 1;
                currentWeekNum = getWeeksOfMonth(currentYear, currentMonth);
            }
            /*划到月初，即将进入上一个月*/
        } else if (currentWeek < 1) {
            if (currentMonth - 1 >= 1) {
                currentMonth--;
            } else {
                currentMonth = 12;
                currentYear--;
            }
            currentWeekNum = getWeeksOfMonth(currentYear, currentMonth);
            if (funCalender.getWeekDayOfLastMonth(currentYear, currentMonth, funCalender.getDaysOfMonth(isLeapyear, currentMonth)) == 7)
                currentWeek = currentWeekNum;
            else currentWeek = currentWeekNum - 1;
        }
    }

    /*判断某年某月所有的星期数*/
    public int getWeeksOfMonth(int year, int month) {
        int preMonthRelax = 0;
        /*判断某月的第一天为星期几*/
        int firstDayOfWeek = funCalender.getWeekdayOfMonth(year, month);
        /*判断是否为闰年*/
        isLeapyear = funCalender.isLeapYear(year);
        /*判断某月的天数*/
        int days = funCalender.getDaysOfMonth(isLeapyear, month);

        if (firstDayOfWeek != 1) {
            preMonthRelax = firstDayOfWeek-1;/*1号为周一*/
        }
        if ((days + preMonthRelax) % 7 == 0) {
            weeksOfMonth = (days + preMonthRelax) / 7;
        } else {
            weeksOfMonth = (days + preMonthRelax) / 7 + 1;
        }
        return weeksOfMonth;
    }


    /*--------------------------------------------------------------------------------------------------------*/

    ICalendarClick iCalendarClick;

    public MyCalendarView setCalendarClick(ICalendarClick iCalendarClick) {
        this.iCalendarClick = iCalendarClick;
        return this;
    }

    public interface ICalendarClick {
        void click(int year, int month, int day);
    }

    /*--------------------------------------------------------------------------------------------------------*/

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
    }
}
