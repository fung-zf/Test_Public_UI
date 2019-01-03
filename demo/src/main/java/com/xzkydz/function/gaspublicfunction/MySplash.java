package com.xzkydz.function.gaspublicfunction;

import com.xzkydz.function.splash.SplashActivity;

/**
 * Date: 2018/3/19  16:37
 * Description:
 */

public class MySplash extends SplashActivity {

    // 返回主界面
    @Override
    public Class<?> getNavigationActivity() {
        return MyNavigationActivity.class;
    }

    // 设置闪屏页显示的内容
    @Override
    public void setShowContent() {
        super.setShowContent();
        setAppName("我是APP名称");
        setLawContent("我是法律内容");
        setVersionName("我是版本号");

//        setBgImag(R.drawable.cgq_float_yc);
    }
}
