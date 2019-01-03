package com.xzkydz.function.data.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.ldoublem.loadingviewlib.view.LVPlayBall;
import com.xzkydz.function.data.all.view.AllTaskFragment;
import com.xzkydz.function.data.uncomplete.view.UnCompleteFragment;
import com.xzkydz.function.data.utils.Inf;
import com.xzkydz.function.data.widget.ViewPagerAdapter;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.search.module.ITaskRoot;
import com.xzkydz.function.search.view.CharacterParser;
import com.xzkydz.function.search.view.SearchHistoryTaskActivity;
import com.xzkydz.function.style.AppStyle;
import com.xzkydz.function.view.ClearWriteEditText;
import com.xzkydz.function.view.CustomViewPager;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import es.dmoral.toasty.Toasty;

import static com.xzkydz.function.app.KyApp.allTaskFragment;
import static com.xzkydz.function.app.KyApp.unCompleteFragment;

public abstract class DataMagerActivity extends AppCompatActivity implements IDataManagerView {

    private Toolbar toolbar;
    private CustomViewPager viewPager;
    public RadioGroup radioGroup;
    private RadioButton rbnAll;
    private RadioButton rbnUnFinshed;
    private RadioButton rbnIndata;
    public ClearWriteEditText etInputKey;
    private LVPlayBall loadding;
    private IDataManagerView iView;
    private boolean optionMenuOn = false;  //标示是否要显示optionmenu
    private Menu aMenu;         //获取optionmenu

    public FrameLayout rlRoot;
    public RelativeLayout rlRiLiTittle;
    public TextView tvMonthDay;
    public LinearLayout llDateDetail;
    public TextView tvYear;
    public TextView tvLunar;
    public FrameLayout flCurrent;
    public TextView tvCurrentDay;
    private CharacterParser characterParser;

    public List<ITaskRoot> allTaskList = new ArrayList<>(); //所有测试任务集合
    public List<ITaskRoot> unCompletedTaskList; //未完成测试任务集合
    public List<ITaskRoot> filterTaskList;  //筛选出的任务
    public List<String> allTargetNameStrList; //全部任务
    public List<String> unTargetNameStrList;  //未完成任务


    java.util.Calendar calendar = java.util.Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));    //获取东八区时间
    public String year = calendar.get(java.util.Calendar.YEAR) + "";
    public String month = calendar.get(java.util.Calendar.MONTH) + 1 + "";
    public String day = calendar.get(java.util.Calendar.DAY_OF_MONTH) + "";
    private MyBroadcastReceiver myBroadcastReceiver;
    private int viewpagetThisItem = 0;
    private boolean mSetListener = true; //用于设置Edittext是否响应内容变化事件
    private String allEtcontent = ""; // 记录全部任务的检索条件
    private String unEtcontent = ""; //记录未完成任务的检索条件
    private ITaskRoot deleteTaskRoot; //删除的任务


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datamanager);
        iView = this;
        Log.d("asd_data", "Activity  onCreate: ");
        initView();
        setBackUp();
        iView.showLoading();
        iView.initViewPager();
        initData();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("asd_data", "Activity  onResume: ");
        //绑定广播
        register();
    }

    public void setDataAndPushView(List<ITaskRoot> taskRoots) {
        //所有任务集合
        allTaskList.addAll(taskRoots);
        getDataList(taskRoots);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                year = (allTaskList.size() != 0) ? allTaskList.get(allTaskList.size() - 1).getGreateTaskTime().substring(0, 4) : year;
                month = (allTaskList.size() != 0) ? allTaskList.get(allTaskList.size() - 1).getGreateTaskTime().substring(5, 7) : month;
                day = (allTaskList.size() != 0) ? allTaskList.get(allTaskList.size() - 1).getGreateTaskTime().substring(8, 10) : day;
                iView.hideLoading();
                Log.d("asd_data", "(allTaskFragment==null: " + (allTaskFragment == null));
                allTaskFragment.pushListView(allTaskList);
                if (isShowUnCompleteFragment()) unCompleteFragment.pushListView(unCompletedTaskList);
                allTaskFragment.initCalendarViewData();
            }
        });

    }

    private void initData() {
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        etInputKey.addTextChangedListener(new MyTextWatcher());
        filterTaskList = new ArrayList<>();
        //获取全部数据、并且初始化界面
        getTaskList();
    }


    public void removeDataForList(ITaskRoot deleteTask) {
        allTaskList.remove(deleteTask);
    }


    /**
     * 未完成任务集合、检索单位集合
     */
    public void getDataList(List<ITaskRoot> allTaskList) {

        unCompletedTaskList = (unCompletedTaskList == null ? new ArrayList<ITaskRoot>() : unCompletedTaskList);
        unCompletedTaskList.clear();

        allTargetNameStrList = (allTargetNameStrList == null ? new ArrayList<String>() : allTargetNameStrList);
        unTargetNameStrList = (unTargetNameStrList == null ? new ArrayList<String>() : unTargetNameStrList);
        allTargetNameStrList.clear();
        unTargetNameStrList.clear();

        for (ITaskRoot task : allTaskList) {
            allTargetNameStrList.add(task.getUnitName());
            if (!task.getTaskStatue()) {
                unTargetNameStrList.add(task.getUnitName());
                unCompletedTaskList.add(task);
            }
        }
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewPager);
        radioGroup = findViewById(R.id.radiogroup);
        rbnAll = findViewById(R.id.rbn_task_all);
        rbnUnFinshed = findViewById(R.id.rbn_task_unfinsh);
        rbnIndata = findViewById(R.id.rbn_data_in);
        loadding = findViewById(R.id.loadding);
        rbnIndata.setVisibility(isShowInputFragment() ? View.VISIBLE : View.GONE);
        rbnAll.setVisibility(isShowUnCompleteFragment() ? View.VISIBLE : View.GONE);
        rbnUnFinshed.setVisibility(isShowUnCompleteFragment() ? View.VISIBLE : View.GONE);

        rlRoot = findViewById(R.id.fl_root);
        etInputKey = findViewById(R.id.et_input_key);
        rlRiLiTittle = findViewById(R.id.rl_tittle_rili);
        tvMonthDay = findViewById(R.id.tv_month_day);
        llDateDetail = findViewById(R.id.ll_linear);
        tvYear = findViewById(R.id.tv_year);
        tvLunar = findViewById(R.id.tv_lunar);
        flCurrent = findViewById(R.id.fl_current);
        tvCurrentDay = findViewById(R.id.tv_current_day);
        rlRoot.setBackgroundColor(getResources().getColor(AppStyle.appToolbarColor));
    }

    public void setBackUp() {
        toolbar.setTitle("全部任务");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        StatusBarUtil.setColor(DataMagerActivity.this, getResources().getColor(AppStyle.appToolbarColor), 0);
        toolbar.setBackgroundColor(getResources().getColor(AppStyle.appToolbarColor));
        radioGroup.setBackgroundColor(getResources().getColor(AppStyle.appToolbarColor));
    }

    private void initEvent() {

        etInputKey.setSetNullListener(new ClearWriteEditText.OnSetNullListener() {
            @Override
            public void hideKey() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(etInputKey.getWindowToken(), 0); //强制隐藏键盘
                    }
                }, 20);
            }
        });

        //禁止滑动
        viewPager.setScanScroll(false);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("asd", "position: " + position + "  positionOffset: " + positionOffset + "  positionOffsetPixels: " + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rbnAll.setChecked(true);
                        optionMenuOn = true;
                        etInputKey.setVisibility(allTaskFragment.mIsShowRiLi ? View.GONE : View.VISIBLE);
                        rlRiLiTittle.setVisibility(allTaskFragment.mIsShowRiLi ? View.VISIBLE : View.GONE);
                        toolbar.setTitle("全部任务");
                        break;

                    case 1:
                        rbnUnFinshed.setChecked(true);
                        optionMenuOn = false;
                        etInputKey.setVisibility(View.VISIBLE);
                        rlRiLiTittle.setVisibility(View.GONE);
                        toolbar.setTitle("未完成任务");
                        break;

                    case 2:
                        rbnIndata.setChecked(true);
                        optionMenuOn = false;
                        toolbar.setTitle("录入数据");
                        break;
                }
                checkOptionMenu();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                if (id == R.id.rbn_task_all) {
                    viewpagetThisItem = 0;
                    viewPager.setCurrentItem(0);
                    unEtcontent = etInputKey.getText().toString().trim();
                    mSetListener = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mSetListener = true;
                        }
                    }, 100);
                    etInputKey.setText(allEtcontent);
                    etInputKey.setSelection(allEtcontent.length());
                } else if (id == R.id.rbn_task_unfinsh) {
                    viewpagetThisItem = 1;
                    viewPager.setCurrentItem(1);
                    allEtcontent = etInputKey.getText().toString().trim();
                    mSetListener = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mSetListener = true;
                        }
                    }, 100);
                    etInputKey.setText(unEtcontent);
                    etInputKey.setSelection(unEtcontent.length());

                } else if (id == R.id.rbn_data_in) {
                    if (isShowInputFragment()) {
                        viewpagetThisItem = 2;
                        viewPager.setCurrentItem(2);
                    }
                }
            }
        });

    }


    /**
     * @Description ：  taskEntityList :测试任务集合
     * index ：长按的item  id
     */
    public void initPopupWindow(final ITaskRoot taskRoot, final boolean isAll) {

        View popupWindowView = getLayoutInflater().inflate(R.layout.popupwindow_myself_delete_task, null);
        final PopupWindow popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.AnimationBottomFade);
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAtLocation(getLayoutInflater().inflate(R.layout.activity_datamanager, null), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        backgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new popupDismissListener());
        popupWindowView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        Button ok = (Button) popupWindowView.findViewById(R.id.btn_ok);
        Button cancel = (Button) popupWindowView.findViewById(R.id.btn_cancel);
        //删除数据：数据库删除、删除后listview 刷新
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (deleteTaskAndData(taskRoot)) {
                    Toasty.success(DataMagerActivity.this, "成功删除测试任务和所有测试数据", 5).show();
                    deleteTaskRoot = taskRoot;
                    // 发送广播，更新界面
                    Intent intent = new Intent();
                    intent.setAction(Inf.pushDelete);
                    sendBroadcast(intent);
                }
                popupWindow.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    /**
     * @Description ：底部弹出的poppupwindow
     */
    class popupDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }

    /*
     * create at 2018/5/7
     * description:搜索输入框的输入变化事件
     */
    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if (!mSetListener) return;

            String filterStr = etInputKey.getText().toString().trim(); //输入框内容
            int checked = radioGroup.getCheckedRadioButtonId();
            if (!TextUtils.isEmpty(filterStr)) {
                List<String> filterStringList = new ArrayList<>();
                for (String unitStr : (viewpagetThisItem == 0 ? allTargetNameStrList : unTargetNameStrList)) {
                    if (unitStr.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(unitStr).startsWith(filterStr.toString())) {
                        if (!filterStringList.contains(unitStr)) {
                            filterStringList.add(unitStr);
                        }
                    }
                }
                // 根据刷选出的名称获取任务
                filterTaskList.clear();

                // 初始化ListView
                if (checked == R.id.rbn_task_all) {
                    for (String name : filterStringList) {
                        for (ITaskRoot task : allTaskList) {
                            if (name.equals(task.getUnitName())) {
                                filterTaskList.add(task);
                            }
                        }
                    }
                    allTaskFragment.pushListView(filterTaskList);
                } else if (checked == R.id.rbn_task_unfinsh) {
                    for (String name : filterStringList) {
                        for (ITaskRoot task : unCompletedTaskList) {
                            if (name.equals(task.getUnitName())) {
                                filterTaskList.add(task);
                            }
                        }
                    }
                    if (isShowUnCompleteFragment())
                        unCompleteFragment.pushListView(filterTaskList);
                }
            } else {
                if (checked == R.id.rbn_task_all) {
                    allTaskFragment.pushListView(allTaskList);
                } else if (checked == R.id.rbn_task_unfinsh) {
                    if (isShowUnCompleteFragment())
                        unCompleteFragment.pushListView(unCompletedTaskList);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.data_style_choose, menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        aMenu = menu;
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (null != allTaskFragment && (id == R.id.action_date || id == R.id.action_date_one)) {
            etInputKey.setVisibility(View.GONE);
            allTaskFragment.llFlowLayout.setVisibility(View.GONE);
            rlRiLiTittle.setVisibility(View.VISIBLE);
            allTaskFragment.mCalendarLayout.setVisibility(View.VISIBLE);
            allTaskFragment.initDateListView(year, month, day);
            allTaskFragment.mIsShowRiLi = true;

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etInputKey.getWindowToken(), 0); //强制隐藏键盘
        } else if (null != allTaskFragment && (id == R.id.action_unit || id == R.id.action_unit_one)) {
            etInputKey.setVisibility(View.VISIBLE);
            rlRiLiTittle.setVisibility(View.GONE);
            //取消显示标签了
            allTaskFragment.llFlowLayout.setVisibility(View.GONE);
            allTaskFragment.mCalendarLayout.setVisibility(View.GONE);
            if (etInputKey.getText().toString().length() != 0) {
                allTaskFragment.pushListView(filterTaskList);
            } else {
                allTaskFragment.pushListView(allTaskList);
            }
            allTaskFragment.mIsShowRiLi = false;
        }

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }


    // menu的显示与隐藏
    private void checkOptionMenu() {
        if (null != aMenu) {
            if (optionMenuOn) {
                for (int i = 0; i < aMenu.size(); i++) {
                    aMenu.getItem(i).setVisible(true);
                    aMenu.getItem(i).setEnabled(true);
                }
            } else {
                for (int i = 0; i < aMenu.size(); i++) {
                    aMenu.getItem(i).setVisible(false);
                    aMenu.getItem(i).setEnabled(false);
                }
            }
        }
    }

    @Override
    public void initViewPager() {

        List<Fragment> fragmentList = new ArrayList<>();
        allTaskFragment = new AllTaskFragment();

        fragmentList.add(allTaskFragment);
        if (isShowUnCompleteFragment()) {
            unCompleteFragment = new UnCompleteFragment();
            fragmentList.add(unCompleteFragment);
        }
        if (isShowInputFragment()) {
            fragmentList.add(setInputFragment());
        }
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.setCurrentItem(0);

    }

    @Override
    public void hideLoading() {
        loadding.setVisibility(View.GONE);
        loadding.stopAnim();
    }

    @Override
    public void showLoading() {
        loadding.setViewColor(R.color.green);
        loadding.setBallColor(R.color.red);
        loadding.startAnim(300);
    }

    // 绑定监听
    private void register() {
        IntentFilter filter0 = new IntentFilter(Inf.pushDelete);
        IntentFilter filter1 = new IntentFilter(Inf.pushStatue);
        myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, filter0);
        registerReceiver(myBroadcastReceiver, filter1);
    }


    // 广播
    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals(Inf.pushDelete)) {

                allTaskFragment.thisShowTaskList.remove(deleteTaskRoot);
                allTaskFragment.pushListView();

                if (isShowUnCompleteFragment()) {
                    if (unCompleteFragment.thisShowTaskList.contains(deleteTaskRoot)) {
                        unCompleteFragment.thisShowTaskList.remove(deleteTaskRoot);
                        unCompleteFragment.pushListView();
                    }
                }

                if (allTaskList.contains(deleteTaskRoot)) allTaskList.remove(deleteTaskRoot);
                getDataList(allTaskList);

            } else if (intent.getAction().equals(Inf.pushStatue)) {

            }

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //销毁在onResume()方法中的广播
        unregisterReceiver(myBroadcastReceiver);
    }


    /*是否显示录入测试数据选项*/
    public boolean isShowInputFragment() {
        return false;
    }

    ;

    /*是否显示未完成项*/
    public boolean isShowUnCompleteFragment() {
        return true;
    }

    /*设置输入Fragment*/
    public Fragment setInputFragment() {
        return null;
    }

    ;

    /*返回所有的测试任务集合*/
    public abstract void getTaskList();

    /*返回任务详情界面*/
    public abstract @NonNull
    Class<?> getDataDetailActivity();

    /*设置测试任务为完成状态*/
    public abstract boolean setTaskComplete(ITaskRoot task);

    /*删除测试任务、和测试数据*/
    public abstract boolean deleteTaskAndData(ITaskRoot task);
}
