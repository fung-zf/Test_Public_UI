package com.xzkydz.function.main;

import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.xzkydz.function.main.adapter.LeftLvAdapter;
import com.xzkydz.function.main.style.NavigationActivityManagerInf;
import com.xzkydz.function.main.style.NavigationActivityStyle;
import com.xzkydz.function.report.QueryReportActivity;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.menu.aftersale.AfterSaleActivity;
import com.xzkydz.function.menu.markdown.Tag;
import com.xzkydz.function.menu.setting.SettingActivity;
import com.xzkydz.function.style.AppStyle;
import com.xzkydz.function.utils.TAG;
import com.xzkydz.function.utils.Tools;

import java.io.File;


public abstract class NavigationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Button csBtn;
    private Button shjglBtn;
    private Button bgcxBtn;
    private Button csbzhBtn;
    private Button cgqBtn;
    private Button backBtn;
    private boolean isQuit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        setSupportActionBar(toolbar);
        setStyleActivity();
        initView();
        intEvent();
        StatusBarUtil.setColorForDrawerLayout(NavigationActivity.this, drawerLayout,0);
    }

    /**
     * 设置Activity 样式
     */
    private void setStyleActivity(){
        NavigationActivityManagerInf activityManager = getNavigationActivityManager();
        if (activityManager!=null){
            AppStyle.appNameId = activityManager.getAppName();
            NavigationActivityStyle.isShowAllConfig = activityManager.getShowConfigAllItem();
            NavigationActivityStyle.isShowPrints = activityManager.getShowPrints();
            NavigationActivityStyle.isShowConfiguration = activityManager.getShowConfiguration();
            NavigationActivityStyle.navigationHeardBGId = activityManager.getNavigationViewHeardBg();
            NavigationActivityStyle.navigationActivityBgID = activityManager.getNavigationActivityBg();
            NavigationActivityStyle.bgArray = activityManager.getMainButtonBg();
            NavigationActivityStyle.leftHeadTextColor = activityManager.getLeftHeadTextColor();
            NavigationActivityStyle.leftItemTextColor = activityManager.getLeftItemTextColor();
        }
    }

    /**
     * 初始化UI
     */
    private void initView(){
        toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.listview);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        csBtn = findViewById(R.id.btn_cs);
        shjglBtn = findViewById(R.id.btn_sjgl);
        bgcxBtn = findViewById(R.id.btn_csbg);
        csbzhBtn = findViewById(R.id.btn_csbz);
        cgqBtn = findViewById(R.id.btn_cgq);
        backBtn = findViewById(R.id.btn_back);

        LinearLayout linearLayout = (LinearLayout) drawerLayout.findViewById(R.id.ll_nav_header);
        TextView tvNameGs = (TextView)linearLayout.findViewById(R.id.tv_name_gs);
        TextView tvUrl = (TextView)linearLayout.findViewById(R.id.tv_url);

        //侧边栏顶部的字体颜色
        tvNameGs.setTextColor(getResources().getColor(NavigationActivityStyle.leftHeadTextColor));
        tvUrl.setTextColor(getResources().getColor(NavigationActivityStyle.leftHeadTextColor));

        //侧边栏顶部背景图
        linearLayout.setBackground(getResources().getDrawable(NavigationActivityStyle.navigationHeardBGId));
        //主界面背景图
        findViewById(R.id.content).setBackground(getResources().getDrawable(NavigationActivityStyle.navigationActivityBgID));
        //主界面底部公司logo图
        findViewById(R.id.imageView).setBackgroundResource(NavigationActivityStyle.navigationActivityBottomImage);
        //设置App名称
        toolbar.setTitle(getResources().getString(AppStyle.appNameId));
        //设置Button背景图
        int[] bgArray = NavigationActivityStyle.bgArray;

        if (bgArray.length>0 && bgArray[0] != 0){
            csBtn.setBackgroundResource(bgArray[0]);
        }

        if (bgArray.length>1 && bgArray[1] != 0){
            shjglBtn.setBackgroundResource(bgArray[1]);
        }

        if (bgArray.length>2 && bgArray[2] != 2){
            bgcxBtn.setBackgroundResource(bgArray[2]);
        }

        if (bgArray.length>3 && bgArray[3] != 3){
            csbzhBtn.setBackgroundResource(bgArray[3]);
        }

        if (bgArray.length>4 && bgArray[4] != 4){
            cgqBtn.setBackgroundResource(bgArray[4]);
        }

        if (bgArray.length>5 && bgArray[5] != 5){
            backBtn.setBackgroundResource(bgArray[5]);
        }

    }


    private void intEvent() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // u设置Adapter，并且实现侧边栏ListView Item 中 RelativeLayout布局的点击事件
        listView.setAdapter(new LeftLvAdapter(NavigationActivity.this ,NavigationActivityStyle.isShowAllConfig, NavigationActivityStyle.isShowPrints , NavigationActivityStyle.isShowConfiguration) {
            @Override
            public void onItemRelativeLayoutClickListener(int stringID) {

                drawerLayout.closeDrawers();

                if (stringID == R.string.main_instructions_text){
                    onSpecificationClickListenter();
                }else if (stringID == R.string.main_product_inf){
                    startActivity(NavigationActivity.this,getMarkdownActivity(),1);
                }else if (stringID == R.string.main_after_sales){
                    startActivity(NavigationActivity.this, AfterSaleActivity.class,2);
                }else if (stringID == R.string.main_backups){
                    Toast.makeText(getApplicationContext(),getResources().getString(stringID),Toast.LENGTH_SHORT).show();
                }else if (stringID == R.string.main_company_inf){
                    startActivity(NavigationActivity.this,getMarkdownActivity(),4);
                }else if (stringID == R.string.main_settings){
                    startActivity(NavigationActivity.this, SettingActivity.class,4);
                }else if (stringID == R.string.main_prints){
                    // 打印item点击事件
                    onPrintsItemClickListener();
                }else if (stringID == R.string.main_configuration){
                    // 配置item点击事件
                    onConfigurationItemClickListener();
                }

            }
        });

        //  主界面的Button的点击事件

        csBtn.setOnClickListener(new MyOnClickListener());
        shjglBtn.setOnClickListener(new MyOnClickListener());
        bgcxBtn.setOnClickListener(new MyOnClickListener());
        cgqBtn.setOnClickListener(new MyOnClickListener());
        csbzhBtn.setOnClickListener(new MyOnClickListener());
        backBtn.setOnClickListener(new MyOnClickListener());
    }

    /**
     * 打开Wps方法
     */
    private void openWps(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                Tools.CopyAssets(NavigationActivity.this,"doc", Environment.getExternalStorageDirectory()+ "/" + getResources().getString(R.string.app_name) + "/说明书/");
                try  {
                    Intent intent = getWordFileIntent(Environment.getExternalStorageDirectory()+"/" + getResources().getString(R.string.app_name) + "/说明书/" +"说明书.docx");
                    startActivity(intent);
                } catch (ActivityNotFoundException e)    {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    /**
     * 跳转Activity
     */
    public   void  startActivity(@NonNull Context packageContext, @NonNull Class<?> cls , int itemNameId){
        Intent intent = new Intent();
        intent.setClass(packageContext, cls);
        intent.putExtra(Tag.intentTag,itemNameId);
        //根据版本设置跳转方式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(NavigationActivity.this).toBundle());
        } else {
            startActivity(intent);
        }
    }


    /**
     * 按钮点击事件
     */
    private class MyOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            int tagID = view.getId();

            //测试
            if (tagID == R.id.btn_cs){
                onTestButtonClickListener();
            }

            //数据管理
            if (tagID == R.id.btn_sjgl){
                onDataManagerClickListener();
            }


            //测试报告
            if (tagID == R.id.btn_csbg){
//                startActivity(NavigationActivity.this, QueryReportActivity.class,0);
                onQueryReportClicklistener();
            }

            // 传感器
            if (tagID == R.id.btn_cgq){
                onSensorButtonClicklistener();
            }

            // 测试标准
            if (tagID == R.id.btn_csbz){
                OnTestStandarClicklistener();
            }

            // 返回键
            if (tagID == R.id.btn_back){
                onBackAPP();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            onBackAPP();
        }
    }

    //打开Word
    public static Intent getWordFileIntent( String param )
    {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param ));
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }



    public void onBackAPP() {
        if (!isQuit) {
            Toast.makeText(NavigationActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            isQuit = true;
            //这段代码意思是,在两秒钟之后isQuit会变成false
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        isQuit = false;
                    }
                }
            }).start();
        } else {
            System.exit(0);
        }
    }




    /**
     * 打印Item的点击事件
     */
    public void onPrintsItemClickListener(){
        Log.d(TAG.Tag, "onPrintsItemClickListener: ");
    }

    /**
     * 配置Item的点击事件
     */
    public void onConfigurationItemClickListener(){
        Log.d(TAG.Tag, "onConfigurationItemClickListener: ");
    }

    /**
     * 侧边栏Item的说明书点击事件
     */
    public void onSpecificationClickListenter(){

    };

    /**
     *  初始化Activity 样式
     * @return 设置
     */
    public abstract NavigationActivityManagerInf getNavigationActivityManager();

    /**
     * 测试按钮点击事件
     */
    public abstract void onTestButtonClickListener();

    /**
     * 数据管理按钮点击事件
     */
    public abstract void onDataManagerClickListener();

    /**
     * 传感器按钮点击事件
     */
    public abstract void onSensorButtonClicklistener();

    /**
     * 查询按钮点击事件
     */
    public abstract void onQueryReportClicklistener();

    /**
     * 测试标准点击事件
     */
    public abstract void OnTestStandarClicklistener();

    /**
     * 侧边栏 --> 产品信息 ： 点击事件
     */
    public abstract @NonNull Class<?> getMarkdownActivity();

}
