package com.xzkydz.function.splash;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.style.AppStyle;

public abstract class SplashActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView tvName;
    private TextView tvVersion;
    private TextView tvLaw;
    private RelativeLayout rootRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initView();
        initEvent();
        startAnim();
        setShowContent();
    }


    private void initView() {
        rootRl = findViewById(R.id.rl_root);
        imageView = findViewById(R.id.imv);
        tvName = findViewById(R.id.tv_name);
        tvVersion = findViewById(R.id.tv_banben);
        tvLaw = findViewById(R.id.tv_law);
    }


    private void initEvent() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            int versionCode = packageInfo.versionCode;//获得版本号
            String versionName = packageInfo.versionName;//获得版本名称
            tvVersion.setText(versionName); //设置版本号
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        tvName.setText(AppStyle.appNameId);
    }


    /**
     * @Title: startAnim
     */
    private void startAnim() {
        AnimationSet set = new AnimationSet(false); // 动画集合
        // 旋转动画
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);// 动画时间
        rotate.setFillAfter(true);// 保持动画状态
        // 缩放动画
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(1000);
        scale.setFillAfter(true);
        // 渐变动画
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(2000);
        alpha.setFillAfter(true);
        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);
        imageView.startAnimation(set);
        // 设置动画监听
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            // 动画执行结束
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashActivity.this, getNavigationActivity()));
                SplashActivity.this.finish();
            }
        });
    }

    public void setShowContent(){

    }

    /*APP名称*/
    public void setAppName(String appNameStr){
        tvName.setText(appNameStr);
    }

    /*版本号*/
    public void setVersionName(String appVersionStr){
        tvVersion.setText(appVersionStr);
    }

    /*法律声明*/
    public void setLawContent(String lawStr){
        tvLaw.setText(lawStr);
    }

    /*背景图片*/
    public void setBgImag(int id){
        rootRl.setBackground(getResources().getDrawable(id));
    }

    public abstract Class<?> getNavigationActivity ();



}
