package com.xzkydz.function.menu.aftersale;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.jaeger.library.StatusBarUtil;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.style.AppStyle;

public class AfterSaleActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private int activityThemeColor = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sale);
        initView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }


    private void initView() {

        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.appbarlayout);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        activityThemeColor = setThemeColor(AppStyle.appToolbarColor);
        setToolbarColor(activityThemeColor == 0 ? AppStyle.appToolbarColor : activityThemeColor);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置顶部toolbar的颜色、和状态栏的颜色
     */
    public void setToolbarColor(int colorId){
        toolbar.setBackgroundColor(getResources().getColor(colorId));
        appBarLayout.setBackgroundColor(getResources().getColor(colorId));
        StatusBarUtil.setColor(AfterSaleActivity.this ,getResources().getColor(colorId) ,0);
    }

    /**
     * 设置toolbar 的颜色
     */
    public int setThemeColor(int colorId){
        return colorId;
    }

}
