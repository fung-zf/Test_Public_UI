package com.xzkydz.function.data.all.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;
import com.haibin.calendarview.YearRecyclerView;
import com.xzkydz.function.data.view.DataMagerActivity;
import com.xzkydz.function.data.widget.TaskAdater;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.search.module.ITaskRoot;
import com.xzkydz.function.style.AppStyle;
import com.xzkydz.function.utils.SharedPreferencesUtils;
import com.xzkydz.function.view.MyListView;
import com.xzkydz.function.view.MyScrollView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import es.dmoral.toasty.Toasty;

import static com.xzkydz.function.app.KyApp.unCompleteFragment;


public class AllTaskFragment extends Fragment implements
        CalendarView.OnDateSelectedListener,
        CalendarView.OnMonthChangeListener,
        CalendarView.OnYearChangeListener,
        CalendarView.OnDateLongClickListener {

    TextView mTextMonthDay;
    TextView mTextYear;
    TextView mTextLunar;
    TextView mTextCurrentDay;
    CalendarView mCalendarView;
    RelativeLayout mRelativeTool;
    private int mYear;
    public CalendarLayout mCalendarLayout;
    private ListView listView;
    private DataMagerActivity mActivity;
    public LinearLayout llFlowLayout;
    private boolean mIsShowYear = false; //指示显示、年、月
    public boolean mIsShowRiLi = false;  //默认显示全部任务列表
    private boolean isFirstShow = true;
    public List<ITaskRoot> thisShowTaskList = new ArrayList<>(); // 用于放置当前ListView填充数据的引用
    public  TaskAdater taskAdater;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (DataMagerActivity) getActivity();
        Log.d("asd_data", "onAttach: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("asd_data", "onDetach: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_task, null);

        Log.d("asd_data", "onCreateView: ");

        initView(view);
        taskAdater = new TaskAdater(mActivity, thisShowTaskList);
        listView.setAdapter(taskAdater);
        initCalendarView();
        initEvent();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("asd_data", "onViewCreated: ");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("asd_data", "onResume");
    }

    private void initEvent() {

        // 测试
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mActivity.etInputKey.getWindowToken(), 0); //强制隐藏键盘

                String[] items = {"查看测试数据", "删除测试任务"};
                String[] items2 = {"查看测试数据", "任务状态设为已完成", "删除测试任务"};
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                //设置标题
                builder.setTitle("");
                /*设置图标*/
                builder.setIcon(R.drawable.vector_drawable_icon);
                if (thisShowTaskList.get(position).getTaskStatue()) {
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int j) {
                            switch (j) {
                                case 0:
                                    Intent intent = new Intent();
                                    intent.putExtra("TASKID", thisShowTaskList.get(position).getTaskId());
                                    intent.setClass(mActivity, mActivity.getDataDetailActivity());
                                    startActivity(intent);
                                    break;
                                case 1:
                                    mActivity.initPopupWindow(thisShowTaskList.get(position), true);
                                    break;
                            }
                        }
                    });
                } else {
                    builder.setItems(items2, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int j) {
                            switch (j) {
                                case 0:
                                    Intent intent = new Intent();
                                    intent.putExtra("TASKID", thisShowTaskList.get(position).getTaskId());
                                    intent.setClass(mActivity, mActivity.getDataDetailActivity());
                                    startActivity(intent);
                                    break;
                                case 1:
                                    if (mActivity.setTaskComplete(thisShowTaskList.get(position))) {
                                        mActivity.getDataList(mActivity.allTaskList);
                                        pushListView();

                                        if (mActivity.isShowUnCompleteFragment()){
                                            unCompleteFragment.thisShowTaskList.remove(thisShowTaskList.get(position));
                                            unCompleteFragment.pushListView();
                                        }

                                        Toasty.success(mActivity, "任务状态设为已完成。", 3).show();
                                    }
                                    break;
                                case 2:
                                    mActivity.initPopupWindow(thisShowTaskList.get(position), true);
                                    break;
                            }
                        }
                    });
                }

                builder.create();
                builder.show();
            }
        });

        mTextMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsShowYear) {
                    mIsShowYear = true;
                    mCalendarView.showYearSelectLayout(mYear);
                    mTextLunar.setVisibility(View.GONE);
                    mTextYear.setVisibility(View.GONE);
                    mTextMonthDay.setText(String.valueOf(mYear));
                    mActivity.radioGroup.setVisibility(View.GONE);
                } else {
                    mIsShowYear = false;
                    mCalendarView.closeYearSelectLayout();
                    mTextLunar.setVisibility(View.VISIBLE);
                    mTextYear.setVisibility(View.VISIBLE);
                    mActivity.radioGroup.setVisibility(View.VISIBLE);
                }

            }
        });

        mActivity.flCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCalendarView.scrollToCurrent();
                if (mIsShowYear) {
                    mIsShowYear = false;
                    mCalendarView.closeYearSelectLayout();
                    listView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    protected void initView(View view) {
        listView = view.findViewById(R.id.listview);
        llFlowLayout = view.findViewById(R.id.rl_flowlayout);
        mTextMonthDay = mActivity.tvMonthDay;
        mTextYear = mActivity.tvYear;
        mTextLunar = mActivity.tvLunar;
        mRelativeTool = mActivity.rlRiLiTittle;
        mCalendarView = view.findViewById(R.id.calendarView);
        mTextCurrentDay = mActivity.tvCurrentDay;
        mCalendarLayout = view.findViewById(R.id.calendarLayout);
    }

    private void initCalendarView() {
        mCalendarView.setOnYearChangeListener(this);
        mCalendarView.setOnDateSelectedListener(this);
        mCalendarView.setOnMonthChangeListener(this);
        mCalendarView.setOnDateLongClickListener(this, true);
        mCalendarView.mSelectLayout.setOnMonthSelectedListener(new YearRecyclerView.OnMonthSelectedListener() {
            @Override
            public void onMonthSelected(int year, int month) {
                mCalendarView.setMonth(year, month);
                mActivity.radioGroup.setVisibility(View.VISIBLE);
            }
        });
        mTextYear.setText(String.valueOf(mCalendarView.getCurYear()));
        mYear = mCalendarView.getCurYear();
        mTextMonthDay.setText(mCalendarView.getCurMonth() + "月" + mCalendarView.getCurDay() + "日");
        mTextLunar.setText("今日");
//        mTextCurrentDay.setText(String.valueOf(mCalendarView.getCurDay()));
        mTextCurrentDay.setText("今");
        mRelativeTool.setBackgroundColor(getResources().getColor(AppStyle.appToolbarColor));
    }

    /**
     * 根据日期初始化Listview
     *
     * @param year
     * @param month
     * @param day
     */
    public void initDateListView(String year, String month, String day) {

        if (!isFirstShow) {
            thisShowTaskList.clear();
            //2018-05-15
            month = (month.length() == 1) ? (0 + month) : month;
            day = (day.length() == 1) ? (0 + day) : day;
            String conditionStr = year + "-" + month + "-" + day;
            for (ITaskRoot task : mActivity.allTaskList) {
                String data = task.getGreateTaskTime();
                if (data.substring(0, 10).equals(conditionStr)) {
                    thisShowTaskList.add(task);
                }
            }
            taskAdater.notifyDataSetChanged();
        }
        isFirstShow = false;
    }

    /**
     * 全部数据
     */
    public void pushListView(List<ITaskRoot> taskRoots) {
        thisShowTaskList.clear();
        thisShowTaskList.addAll(taskRoots);
        Log.d("asd_data", "pushListView: ");
        Log.d("asd_data", "taskAdater==null: " + (taskAdater==null));
        taskAdater.notifyDataSetChanged();
    }

    public void pushListView() {
        taskAdater.notifyDataSetChanged();
    }


    /*在日历表中显示有测试任务的日期*/
    public void initCalendarViewData() {
        final List<Calendar> schemes = new ArrayList<>();
        for (ITaskRoot res : mActivity.allTaskList) {
            String timeStr = res.getGreateTaskTime();
            int year = dateValue(timeStr, 0);
            int month = dateValue(timeStr, 1);
            int day = dateValue(timeStr, 2);
            schemes.add(getSchemeCalendar(year, month, day, 0xFF40db25, "T"));
        }
        mCalendarView.setSchemeDate(schemes);
    }

    private Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        Calendar calendar = new Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDateSelected(Calendar calendar, boolean isClick) {
        mTextLunar.setVisibility(View.VISIBLE);
        mTextYear.setVisibility(View.VISIBLE);
        mTextMonthDay.setText(calendar.getMonth() + "月" + calendar.getDay() + "日");
        mTextYear.setText(String.valueOf(calendar.getYear()));
        mTextLunar.setText(calendar.getLunar());
        mYear = calendar.getYear();

        mActivity.year = String.valueOf(calendar.getYear());
        mActivity.month = String.valueOf(calendar.getMonth());
        mActivity.day = String.valueOf(calendar.getDay());

        initDateListView(mActivity.year, mActivity.month, mActivity.day);
    }

    @Override
    public void onDateLongClick(Calendar calendar) {

    }


    @Override
    public void onMonthChange(int year, int month) {
        mActivity.radioGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void onYearChange(int year) {
        mTextMonthDay.setText(String.valueOf(year));
    }

    /**
     * "yyyy-MM-dd HH:mm:ss" 从类似字符串中获取年、月 、日
     *
     * @param dateStr
     * @param type
     * @return
     */
    private int dateValue(String dateStr, int type) {
        int res = 0;
        switch (type) {
            case 0: // 年
                res = Integer.parseInt(dateStr.substring(0, 4));
                break;
            case 1:// 月\
                res = Integer.parseInt(dateStr.substring(5, 7));
                break;
            case 2: // 日
                res = Integer.parseInt(dateStr.substring(8, 10));
                break;
        }
        return res;
    }

}
