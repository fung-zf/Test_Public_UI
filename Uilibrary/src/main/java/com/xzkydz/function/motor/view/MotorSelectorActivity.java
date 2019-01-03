package com.xzkydz.function.motor.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.xzkydz.function.motor.module.ConstantData;
import com.xzkydz.function.motor.module.IMotorBean;
import com.xzkydz.function.motor.module.MotorModule;
import com.xzkydz.function.motor.weidget.MotorAdapter;
import com.xzkydz.function.transform.MotorBean;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.style.AppStyle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class MotorSelectorActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText etDjxh;
    private EditText etEdgl;
    private RadioGroup rgpOne;
    private RadioGroup rgpTwo;
    private ListView listView;
    private Button btnAdd;
    private Button btnSearch;
    private Button btnClear;
    private RadioButton rbnIgnore;
    private RadioButton rbn110;
    private RadioButton rbn115;
    private RadioButton rbn220;
    private RadioButton rbn2835;
    private RadioButton rbn380;
    private RadioButton rbn660;
    private RadioButton rbn3000;
    private RadioButton rbn3300;
    private RadioButton rbn6000;
    private RadioButton rbn6600;
    private RadioButton rbn10000;
    private int eddyQuery = -1; //额定电压
    private MotorAdapter motorAdapter;
    private MotorModule motorModule;
    private ProgressDialog progressDialog;
    private List<IMotorBean> motorBeanList = new ArrayList<>();
    private RelativeLayout newMotorRl;
    private EditText etXhNew;
    private EditText etEddyNew;
    private EditText etEddlNew;
    private EditText etEdglNew;
    private EditText etEdxlNew;
    private EditText etjsNew;
    private EditText etKzdlNew;
    private EditText etKzglNew;
    private EditText etWgjjdlNew;
    private Button btnOk;
    private Button btnCancel;
    private ScrollView scrollView;

    private DecimalFormat df2 = new DecimalFormat("#0.00");
    private DecimalFormat df3 = new DecimalFormat("#0.000");
    private boolean _IsShowBuildMotor = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motor_selector);
        motorModule = new MotorModule();
        initView();
        setBackUp();
        initEvent();
        // 显示dialog
        showDialog();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                motorBeanList = getMotorList();
            }
        }, 500);//3秒后执行Runnable中的run方法

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

    }

    private void initView() {
        progressDialog = new ProgressDialog(this);
        toolbar = findViewById(R.id.toolbar);
        etDjxh = findViewById(R.id.et_djxh);
        etEdgl = findViewById(R.id.et_edgl);
        rgpOne = findViewById(R.id.rgp_one);
        rgpTwo = findViewById(R.id.rgp_two);
        listView = findViewById(R.id.listview);
        btnAdd = findViewById(R.id.btn_add);
        btnSearch = findViewById(R.id.btn_search);
        btnClear = findViewById(R.id.btn_clear);
        rbnIgnore = findViewById(R.id.rbn_null);
        rbn110 = findViewById(R.id.rbn_110);
        rbn115 = findViewById(R.id.rbn_115);
        rbn220 = findViewById(R.id.rbn_220);
        rbn2835 = findViewById(R.id.rbn_283);
        rbn380 = findViewById(R.id.rbn_380);
        rbn660 = findViewById(R.id.rbn_660);
        rbn3000 = findViewById(R.id.rbn_3000);
        rbn3300 = findViewById(R.id.rbn_3300);
        rbn6000 = findViewById(R.id.rbn_6000);
        rbn6600 = findViewById(R.id.rbn_6600);
        rbn10000 = findViewById(R.id.rbn_10000);
        newMotorRl = findViewById(R.id.content_dynamo_new);
        etXhNew = findViewById(R.id.et_xh_new);
        etEddyNew = findViewById(R.id.et_eddy_new);
        etEddlNew = findViewById(R.id.et_eddl_new);
        etEdglNew = findViewById(R.id.et_edgl_new);
        etEdxlNew = findViewById(R.id.et_edxl_new);
        etjsNew = findViewById(R.id.et_js_new);
        etKzdlNew = findViewById(R.id.et_kzdl_new);
        etKzglNew = findViewById(R.id.et_kzgl_new);
        etWgjjdlNew = findViewById(R.id.et_wgjjdl_new);
        btnOk = findViewById(R.id.btn_ok);
        btnCancel = findViewById(R.id.btn_cancel);
        scrollView = findViewById(R.id.scroll);
    }

    private void initEvent() {
        rbnIgnore.setOnCheckedChangeListener(new MyonCheckedChanged());
        rbn110.setOnCheckedChangeListener(new MyonCheckedChanged());
        rbn115.setOnCheckedChangeListener(new MyonCheckedChanged());
        rbn220.setOnCheckedChangeListener(new MyonCheckedChanged());
        rbn2835.setOnCheckedChangeListener(new MyonCheckedChanged());
        rbn380.setOnCheckedChangeListener(new MyonCheckedChanged());
        rbn660.setOnCheckedChangeListener(new MyonCheckedChanged());
        rbn3000.setOnCheckedChangeListener(new MyonCheckedChanged());
        rbn3300.setOnCheckedChangeListener(new MyonCheckedChanged());
        rbn6000.setOnCheckedChangeListener(new MyonCheckedChanged());
        rbn6600.setOnCheckedChangeListener(new MyonCheckedChanged());
        rbn10000.setOnCheckedChangeListener(new MyonCheckedChanged());
        btnAdd.setOnClickListener(new MyOnClickListener());
        btnClear.setOnClickListener(new MyOnClickListener());
        btnSearch.setOnClickListener(new MyOnClickListener());
        btnOk.setOnClickListener(new MyOnClickListener());
        btnCancel.setOnClickListener(new MyOnClickListener());


//        级数监听
        etjsNew.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                float jishugonglv = 0;
                float jishudianliu = 0;
                if (charSequence.toString().length() != 0) {
                    switch (Integer.parseInt(charSequence.toString())) {
                        case 1:
                        case 2:
                            jishugonglv = 0.035F;
                            jishudianliu = 0.16F;
                            break;
                        case 3:
                        case 4:
                            jishugonglv = 0.033F;
                            jishudianliu = 0.25F;
                            break;
                        case 5:
                        case 6:
                            jishugonglv = 0.032F;
                            jishudianliu = 0.33F;
                            break;
                        case 7:
                        default:
                            jishugonglv = 0.03F;
                            jishudianliu = 0.4F;
                            break;
                    }
                }

                if (!TextUtils.isEmpty(etEdglNew.getText().toString()) && !TextUtils.isEmpty(etjsNew.getText().toString())) {
                    etKzglNew.setText(df3.format(Integer.parseInt(charSequence.toString()) * jishugonglv));
                }

                if (!TextUtils.isEmpty(etEddlNew.getText().toString()) && !TextUtils.isEmpty(etjsNew.getText().toString())) {
                    etKzdlNew.setText(df3.format(Integer.parseInt(charSequence.toString()) * jishudianliu));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        // 根据电机库是否是添加的，显示不同的Dialog
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showMotorDialog(motorBeanList.get(position));
            }
        });
    }

    /*
     * description:显示搜索结果
     */
    private void showResDataList() {

        String djxhStr = etDjxh.getText().toString().trim().equals("") ? null : etDjxh.getText().toString().trim();
        String edglStr = etEdgl.getText().toString().trim().equals("") ? null : etEdgl.getText().toString().trim();

        motorModule.setAllMotorBean(motorBeanList)
                .setDjxhStr(djxhStr)
                .setEdglStr(edglStr)
                .setEddyStr(eddyQuery + "").getResList();
        List<IMotorBean> resMotorBeanList = motorModule.getResQueryList();
        motorBeanList.clear();
        motorBeanList.addAll(resMotorBeanList);
        initListView(resMotorBeanList);
        Toast.makeText(MotorSelectorActivity.this, resMotorBeanList.size() + "条数据符合条件", Toast.LENGTH_SHORT).show();
    }


    /*
     * description:初始化ListView，设为公共方法，用于在线程中调用
     */
    public void initListView(List<IMotorBean> list) {
        motorAdapter = new MotorAdapter(MotorSelectorActivity.this, list);
        listView.setAdapter(motorAdapter);
    }

    public void setBackUp() {
        toolbar.setTitle(R.string.motor_tittle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        StatusBarUtil.setColor(MotorSelectorActivity.this, getResources().getColor(AppStyle.appToolbarColor), 0);
        toolbar.setBackgroundColor(getResources().getColor(AppStyle.appToolbarColor));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            back();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
     * create at 2018/5/9
     * description:button的点击事件
     */
    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int id = v.getId();

            if (id == R.id.btn_ok) {
                MotorBean motorLib = new MotorBean();
                boolean xhBool = !TextUtils.isEmpty(etXhNew.getText());
                boolean eddyBool = !TextUtils.isEmpty(etEddyNew.getText());
                boolean eddlBool = !TextUtils.isEmpty(etEddlNew.getText());
                boolean edglBool = !TextUtils.isEmpty(etEdglNew.getText());
                boolean edxlBool = !TextUtils.isEmpty(etEdxlNew.getText());
                boolean kzdlBool = !TextUtils.isEmpty(etKzdlNew.getText());
                boolean kzglBool = !TextUtils.isEmpty(etKzglNew.getText());
                boolean jsBool = !TextUtils.isEmpty(etjsNew.getText());

                if (xhBool && eddyBool && eddlBool && edglBool && edxlBool && kzdlBool && kzglBool && jsBool) {
                    newMotorRl.setVisibility(View.GONE);
                    _IsShowBuildMotor = false; // 显示新建电机库
                    motorLib.setDJXL(""); //电机系列
                    motorLib.setDJ_LIB_NAME(etXhNew.getText().toString().toUpperCase().trim());  //电机型号
                    motorLib.setEDDY(etEddyNew.getText().toString().trim());
                    motorLib.setEDDL(etEddlNew.getText().toString().trim());
                    motorLib.setEDGL(etEdglNew.getText().toString().trim());
                    motorLib.setEDXL(etEdxlNew.getText().toString().trim());
                    motorLib.setKZDL(etKzdlNew.getText().toString().trim());
                    motorLib.setKZGL(etKzglNew.getText().toString().trim());
                    motorLib.setEDGLYS(0.8 + "");
                    motorLib.setWGJJDL(etWgjjdlNew.getText().toString().trim());
                    motorLib.setJS(etjsNew.getText().toString().trim());
                    insertNewMotor(motorLib);
                    Toast.makeText(MotorSelectorActivity.this, "创建一条新的电机信息", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MotorSelectorActivity.this, "请填写完整的电机信息", Toast.LENGTH_SHORT).show();
                }
            }

            if (id == R.id.btn_cancel) {
                newMotorRl.setVisibility(View.GONE);
                _IsShowBuildMotor = false; // 显示新建电机库
            }

            if (id == R.id.btn_add) {
                newMotorRl.setVisibility(View.VISIBLE);
                _IsShowBuildMotor = true; // 显示新建电机库
            }

            if (id == R.id.btn_search) {

                String djxhStr = etDjxh.getText().toString().trim().equals("") ? null : etDjxh.getText().toString().trim();
                String edglStr = etEdgl.getText().toString().trim().equals("") ? null : etEdgl.getText().toString().trim();

                if (djxhStr == null && edglStr == null && eddyQuery == -1) {
                    Toast.makeText(MotorSelectorActivity.this, "请输入搜条件...", Toast.LENGTH_SHORT).show();
                } else {
                    //搜索数据显示结果
                    showResDataList();
                }
            }

            if (id == R.id.btn_clear) {
                Toast.makeText(MotorSelectorActivity.this, "搜索条件已清空", Toast.LENGTH_SHORT).show();
                etDjxh.setText("");
                etEdgl.setText("");
                rgpOne.clearCheck();
                rgpTwo.clearCheck();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        motorBeanList = getMotorList();
                        initListView(motorBeanList);
                    }
                }, 500);//3秒后执行Runnable中的run方法
            }
        }
    }

    private class MyonCheckedChanged implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            rgpOne.clearCheck();
            rgpTwo.clearCheck();
            // 库文件中无法直接获取ID，暂时只能用IF
            int id = buttonView.getId();
            if (id == R.id.rbn_null) eddyQuery = -1;
            if (id == R.id.rbn_110) eddyQuery = 110;
            if (id == R.id.rbn_115) eddyQuery = 115;
            if (id == R.id.rbn_220) eddyQuery = 220;
            if (id == R.id.rbn_283) eddyQuery = 283;
            if (id == R.id.rbn_380) eddyQuery = 380;
            if (id == R.id.rbn_660) eddyQuery = 660;
            if (id == R.id.rbn_3000) eddyQuery = 3000;
            if (id == R.id.rbn_3300) eddyQuery = 3300;
            if (id == R.id.rbn_6000) eddyQuery = 6000;
            if (id == R.id.rbn_6600) eddyQuery = 6600;
            if (id == R.id.rbn_10000) eddyQuery = 10000;
        }
    }

    /*
     * description:显示dialog
     */
    private void showDialog() {
        progressDialog.setMessage("正在加载数据库...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    protected void setMotorAllList(List<IMotorBean> motorBeans) {
        motorBeanList = motorBeans;
    }

    /*
     * description:隐藏dialog
     */
    public void hideDialog() {
        progressDialog.dismiss();
    }


    /*
     * description:显示电机库选择dialog
     */
    public void showMotorDialog(final IMotorBean iMotorBean) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(iMotorBean.getDjLibName());
        if (iMotorBean.getIsAdd()) {
            String[] item = {"选择", "删除", "取消"};
            builder.setItems(item, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            resultCode(iMotorBean);
                            break;
                        case 1:
                            deleteMotor(iMotorBean);
                            Toast.makeText(MotorSelectorActivity.this, "成功删除一条电机数据", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        } else {
            String[] item = {"选择", "取消"};
            builder.setItems(item, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            resultCode(iMotorBean);
                            break;
                    }
                }
            });
        }
        builder.show();
    }

    /*
     * description:返回的操作
     */
    private void resultCode(IMotorBean motorBean) {
        Intent mIntent = new Intent();
        mIntent.putExtra(ConstantData.MotorName_resultCode, motorBean.getDjLibName());
        mIntent.putExtra(ConstantData.MotorEddy_resultCode, motorBean.getEddy());
        mIntent.putExtra(ConstantData.MotorEddl_resultCode, motorBean.getEddl());
        mIntent.putExtra(ConstantData.MotorEdgl_resultCode, motorBean.getEdgl());
        mIntent.putExtra(ConstantData.MotorEdxl_resultCode, motorBean.getEdxl());
        mIntent.putExtra(ConstantData.MotorKzdl_resultCode, motorBean.getKzdl());
        mIntent.putExtra(ConstantData.MotorKzgl_resultCode, motorBean.getKzgl());
        mIntent.putExtra(ConstantData.MotorEdglys_resultCode, motorBean.getEdglys());
        mIntent.putExtra(ConstantData.MotorJs_resultCode, motorBean.getJs());
        mIntent.putExtra(ConstantData.MotorWgjjdl_resultCode, motorBean.getWgjjdl());
        // 设置结果，并进行传送
        this.setResult(ConstantData.Motor_resultCode, mIntent);
        finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
        }
        return true;
    }

    /*
     * description:
     */
    private void back() {
        if (_IsShowBuildMotor) {
            _IsShowBuildMotor = false;
            newMotorRl.setVisibility(View.GONE);
        } else {
            Intent mIntent = new Intent();
            this.setResult(ConstantData.Motor_resultCode_NULL, mIntent);
            finish();
        }
    }

    //获取所有的电机数据
    public abstract List<IMotorBean> getMotorList();

    //插入数据库
    public abstract void insertNewMotor(MotorBean motorBean);

    public abstract void deleteMotor(IMotorBean motorBean);

}
