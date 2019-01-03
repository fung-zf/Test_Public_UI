package com.xzkydz.function.menu.markdown;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jaeger.library.StatusBarUtil;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.style.AppStyle;

import br.tiagohm.markdownview.MarkdownView;
import br.tiagohm.markdownview.css.InternalStyleSheet;
import br.tiagohm.markdownview.css.styles.Github;

public abstract class MarkDownActivity extends AppCompatActivity {

    private int type;
    private MarkdownView mMarkdownView;
    private String productFles = "markdown/ProductInf.md";
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private int appThemeColor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_down);
        initView();
        initEvent();
    }

    private void initView(){
        appBarLayout = findViewById(R.id.appbarlayout);
        toolbar =  findViewById(R.id.toolbar);
        mMarkdownView = findViewById(R.id.markdown_view);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //决定左上角的图标是否可以点击

        appThemeColor = setThemeColor(AppStyle.appToolbarColor);
        setToolbarColor(appThemeColor == 0 ? AppStyle.appToolbarColor : appThemeColor);

        Intent intent = getIntent();
        type = intent.getIntExtra(Tag.intentTag ,-1);
        MarkDownActivity.this.setTitle(type == 1 ? getResources().getString(R.string.main_product_inf) : getResources().getString(R.string.main_company_inf));
    }

    private void initEvent(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                InternalStyleSheet mStyle = new Github();
                mMarkdownView.addStyleSheet(mStyle);
                mStyle.addMedia("screen and (min-width: 320px)");
                mStyle.addRule("h1", "color: green");
                mStyle.endMedia();
                mStyle.addMedia("screen and (min-width: 481px)");
                mStyle.addRule("h1", "color: red");
                mStyle.endMedia();
                mStyle.addMedia("screen and (min-width: 641px)");
                mStyle.addRule("h1", "color: blue");
                mStyle.endMedia();
                mStyle.addMedia("screen and (min-width: 961px)");
                mStyle.addRule("h1", "color: yellow");
                mStyle.endMedia();
                mStyle.addMedia("screen and (min-width: 1025px)");
                mStyle.addRule("h1", "color: gray");
                mStyle.endMedia();
                mStyle.addMedia("screen and (min-width: 1281px)");
                mStyle.addRule("h1", "color: orange");
                mStyle.endMedia();
                mMarkdownView.loadMarkdownFromAsset(type == 1 ? setProductInfFileUrl() : "markdown/EnterpriseInf.md");
            }
        });
        new Handler().postDelayed(thread,400);
    }

    /**
     * 设置顶部toolbar的颜色、和状态栏的颜色
     */
    public void setToolbarColor(int colorId){
        toolbar.setBackgroundColor(getResources().getColor(colorId));
        appBarLayout.setBackgroundColor(getResources().getColor(colorId));
        StatusBarUtil.setColor(MarkDownActivity.this ,getResources().getColor(colorId) ,0);
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
     * 设置toolbar 的颜色
     */
    public int setThemeColor(int colorId){
        return colorId;
    }

    /**
     * 用于设置产品信息的Markdown 文件的路径
     */
    public abstract String setProductInfFileUrl();

}
