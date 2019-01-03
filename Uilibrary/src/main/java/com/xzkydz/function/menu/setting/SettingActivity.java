package com.xzkydz.function.menu.setting;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.style.AppStyle;

public class SettingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private TextView tvVersion;
    private Button updataBtn;
    private Button netBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initEvent();
    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.appbarlayout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setToolbarColor(AppStyle.appToolbarColor);

        tvVersion = findViewById(R.id.tv_version);
        updataBtn = findViewById(R.id.btn_updata);
        netBtn = findViewById(R.id.btn_net);

        updataBtn.setOnClickListener(new MyOnclickListener());
        netBtn.setOnClickListener(new MyOnclickListener());
    }

    private void initEvent() {

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            int versionCode = packageInfo.versionCode;//获得版本号
            String versionName = packageInfo.versionName;//获得版本名称
            tvVersion.setText("版本名称："+versionName + "\n版本编号：" + versionCode ); //设置版本号
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 点击事件
     *
     */
    private class MyOnclickListener implements View.OnClickListener {
        
        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.btn_updata){
                Toast.makeText(SettingActivity.this,"检查更新",Toast.LENGTH_SHORT).show();
            }

            if (v.getId() == R.id.btn_net){
//                Intent intent =  new Intent(Settings.ACTION_SETTINGS);
//                startActivity(intent);
                //设置IP

                Intent intent = new Intent();
                intent.setClass(SettingActivity.this,ConfigActivity.class);
                startActivity(intent);
            }
        }
    }


    /**
     * 设置顶部toolbar的颜色、和状态栏的颜色
     */
    public void setToolbarColor(int colorId){
        toolbar.setBackgroundColor(getResources().getColor(colorId));
        appBarLayout.setBackgroundColor(getResources().getColor(colorId));
        StatusBarUtil.setColor(SettingActivity.this ,getResources().getColor(colorId) ,0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }





}
