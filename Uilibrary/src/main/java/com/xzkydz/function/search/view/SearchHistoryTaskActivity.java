package com.xzkydz.function.search.view;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.xzkydz.function.search.module.DataManager;
import com.xzkydz.function.search.module.IDataManager;
import com.xzkydz.function.search.wedige.TaskDetailAdapter;
import com.xzkydz.function.search.wedige.TaskNameAdapter;
import com.xzkydz.function.search.wedige.TaskOneDetailAdapter;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.search.module.ITaskRoot;
import com.xzkydz.function.style.AppStyle;

import java.util.ArrayList;
import java.util.List;

public abstract class SearchHistoryTaskActivity extends AppCompatActivity implements ISearchHistoryTaskView {

    private Toolbar toolbar;
    private View bgView;
    //    private LVCircularJump loading;
    private ListView listView;
    private AppBarLayout appBarLayout;
    private Button btnAllTask;
    private Button btnUnfinshTask;
    private Button btnLastTask;
    private EditText editText;

    private List<String> unitNameStrList;
    private List<String> unitNameUnFinishStrList;
    private List<String> filterDateList;
    private List<ITaskRoot> taskList;
    private ITaskRoot resultTask = null; // 最终选中的Task

    private IDataManager dataManager; // 数据类型操作
    private CharacterParser characterParser;
    private int adapterType = 0; // 用于记录Adapter类型，用于初始化ListView的状态 0：不初始化状态  1：测试任务名称  2：同单位名称下的所有测试任务 3：详细测试任务
    private boolean _IsCLickLastTask = false; //是否点击最后一次测试任务按钮(直接点击最后一次测试按钮后的返回键处理和逐步显示的不同)
    private LinearLayout btnLinear;
    private BaseAdapter adapter;  //  向上转型的Adapter
    private TextView tvNoTask;
    private TextView tvTittle;
    private List<ITaskRoot> taskSomeNameLists;
    private TextView tvDetailTaskTittle;
    private LinearLayout llDeatailTaskTittle;
    private View lineSplitOne;
    private View lineSplitTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history_task);
        taskList = getTaskDataList();
        filterDateList = new ArrayList<>();
        this.initView();
        this.setBackUp();
        this.showLoading();
        dataManager = new DataManager();
        unitNameStrList = dataManager.taskNameList(taskList);
        unitNameUnFinishStrList = dataManager.unFinishTaskNameList();

        if (taskList.size()!=0){
            resultTask = taskList.get(taskList.size()-1);
        }

        setToolbarColor();
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        editText.addTextChangedListener(new MyTextWatcher());
        btnAllTask.setOnClickListener(new MyOnclickListenerEvent());
        btnUnfinshTask.setOnClickListener(new MyOnclickListenerEvent());
        btnLastTask.setOnClickListener(new MyOnclickListenerEvent());
        listView.setOnItemClickListener(new MyOnItemClickListenerEvent());
    }

    @Override
    public void initView() {
        btnLinear = findViewById(R.id.ll_search_buton);
        appBarLayout = findViewById(R.id.appbarlayout);
        toolbar = findViewById(R.id.toolbar);
        bgView = findViewById(R.id.bg_view);
        tvTittle = findViewById(R.id.tv_tittlte);
//        loading = findViewById(R.id.loading);
        listView = findViewById(R.id.lv_search);
        editText = findViewById(R.id.et_input_condition);
        btnAllTask = findViewById(R.id.btn_task_all);
        btnUnfinshTask = findViewById(R.id.btn_task_unfinished);
        btnLastTask = findViewById(R.id.btn_task_last);
        tvNoTask = findViewById(R.id.tv_show_null_inf);

        tvDetailTaskTittle = findViewById(R.id.tv_unit_tittle_name);
        llDeatailTaskTittle = findViewById(R.id.ll_gridle_tittle);
        lineSplitOne = findViewById(R.id.line_split_one);
        lineSplitTwo = findViewById(R.id.line_split_two);

        bgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void setBackUp() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void showLoading() {
//        loading.setViewColor(R.color.blue);
//        loading.startAnim(200);
    }

    @Override
    public void hideLoading() {
//        loading.stopAnim();
    }


    @Override
    public void listViewSetAdapter(BaseAdapter adapter) {
        listView.setAdapter(adapter);
    }

    /*
     * create at 2018/5/7
     * description:三个按钮的点击事件
     */
    private class MyOnclickListenerEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.equals(btnAllTask)) {
                adapterType = 1;
                filterDateList.clear();
                tvTittle.setText(R.string.tittle_task_all);
                filterDateList.addAll(unitNameStrList);
            } else if (v.equals(btnUnfinshTask)) {
                adapterType = 1;
                filterDateList.clear();
                tvTittle.setText(R.string.tittle_task_unfinish);
                filterDateList.addAll(unitNameUnFinishStrList);
            } else if (v.equals(btnLastTask)) {
                _IsCLickLastTask = true;
                tvTittle.setText(R.string.tittle_task_last);
                adapterType = 3;
            }
            initListView(adapterType);
            editText.setVisibility(View.INVISIBLE);
        }
    }

    /*
     * create at 2018/5/7
     * description:listview 点击事件
     */
    private class MyOnItemClickListenerEvent implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (adapter instanceof TaskNameAdapter) {
                taskSomeNameLists = dataManager.someUnitNameTask(taskList, filterDateList.get(position));
                adapter = new TaskDetailAdapter(SearchHistoryTaskActivity.this, taskSomeNameLists);
                tvDetailTaskTittle.setVisibility(View.VISIBLE);
                llDeatailTaskTittle.setVisibility(View.VISIBLE);
                lineSplitOne.setVisibility(View.VISIBLE);
                lineSplitTwo.setVisibility(View.VISIBLE);
                tvDetailTaskTittle.setText(filterDateList.get(position));
                adapterType = 2;
                initListView(adapterType);
                return;
            }

            if (adapter instanceof TaskDetailAdapter) {
                resultTask = taskSomeNameLists.get(position);
                tvDetailTaskTittle.setVisibility(View.GONE);
                llDeatailTaskTittle.setVisibility(View.GONE);
                lineSplitOne.setVisibility(View.GONE);
                lineSplitTwo.setVisibility(View.GONE);
                adapterType = 3;
                initListView(adapterType);
                return;
            }
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
            adapterType = 1;
            String filterStr = editText.getText().toString().trim(); //输入框内容
            if (!TextUtils.isEmpty(filterStr)) {
                filterDateList.clear();
                for (String unitStr : unitNameStrList) {
                    if (unitStr.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(unitStr).startsWith(filterStr.toString())) {
                        filterDateList.add(unitStr);
                    }
                }
                initListView(adapterType);
            } else {
                btnLinear.setVisibility(View.VISIBLE);
                listView.setVisibility(View.INVISIBLE);
                tvNoTask.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    /*
     * create at 2018/5/7
     * description:listView 赋值
     */
    private void initListView(int adapterType) {
        btnLinear.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
        switch (adapterType) {
            case 1:
                adapter = new TaskNameAdapter(SearchHistoryTaskActivity.this, filterDateList);
                if (filterDateList.size() == 0) tvNoTask.setVisibility(View.VISIBLE);
                break;

            case 2:
                adapter = new TaskDetailAdapter(SearchHistoryTaskActivity.this, taskSomeNameLists);
                if (taskSomeNameLists.size() == 0) tvNoTask.setVisibility(View.VISIBLE);
                break;

            case 3:
                //  如果最后一次测试任务为空，则将ListView 设为隐藏
                if (resultTask==null){
                    listView.setVisibility(View.INVISIBLE);
                }
                adapter = new TaskOneDetailAdapter(SearchHistoryTaskActivity.this, resultTask, getTestClass(resultTask));
                if (resultTask == null) tvNoTask.setVisibility(View.VISIBLE);
                break;
        }
        listView.setAdapter(adapter);
    }

    /**
     * 设置顶部toolbar的颜色、和状态栏的颜色
     */
    private void setToolbarColor() {
        toolbar.setBackgroundColor(getResources().getColor(AppStyle.appToolbarColor));
        appBarLayout.setBackgroundColor(getResources().getColor(AppStyle.appToolbarColor));
        StatusBarUtil.setColor(SearchHistoryTaskActivity.this, getResources().getColor(AppStyle.appToolbarColor), 0);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            backEvent();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 监控/拦截/屏蔽返回键
            backEvent();
        }
        return false;
    }

    /*
     * description:返回事件
     */
    private void backEvent() {
        tvNoTask.setVisibility(View.INVISIBLE);
        switch (adapterType) {
            case 0:
                finish();
                break;
            case 1:
                adapterType = 0;
                listView.setVisibility(View.INVISIBLE);
                btnLinear.setVisibility(View.VISIBLE);
                editText.setVisibility(View.VISIBLE);
                break;
            case 2:
                adapterType = 1;
                initListView(adapterType);
                tvDetailTaskTittle.setVisibility(View.GONE);
                llDeatailTaskTittle.setVisibility(View.GONE);
                lineSplitOne.setVisibility(View.GONE);
                lineSplitTwo.setVisibility(View.GONE);
                break;
            case 3:
                if (_IsCLickLastTask) {
                    _IsCLickLastTask = false;
                    adapterType = 0;
                    listView.setVisibility(View.INVISIBLE);
                    btnLinear.setVisibility(View.VISIBLE);
                    editText.setVisibility(View.VISIBLE);
                    Log.d("asd", "_IsCLickLastTask: "+_IsCLickLastTask);
                } else {
                    adapterType = 2;
                    initListView(adapterType);
                    tvDetailTaskTittle.setVisibility(View.VISIBLE);
                    llDeatailTaskTittle.setVisibility(View.VISIBLE);
                    lineSplitOne.setVisibility(View.VISIBLE);
                    lineSplitTwo.setVisibility(View.VISIBLE);
                    Log.d("asd", "adapterType: "+adapterType);
                }
                break;
        }
    }

    protected abstract List<ITaskRoot> getTaskDataList();

    protected abstract Class getTestClass(ITaskRoot resultTask);

}
