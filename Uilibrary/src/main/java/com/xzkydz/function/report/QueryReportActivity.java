package com.xzkydz.function.report;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.jaeger.library.StatusBarUtil;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.style.AppStyle;

import java.util.ArrayList;
import java.util.List;

public abstract class QueryReportActivity extends AppCompatActivity {

    ViewPager mViewPager;
    PagerAdapter mPagerAdapter;

    private TabLayout mTabLayout;
    private AppBarLayout appBarLayout;
    private Toolbar toolbar;
    private int activityThemeColor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_report);
        initView(savedInstanceState);

        toolbar.setTitle("报告管理");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private void initView(Bundle savedInstanceState) {

        appBarLayout = (AppBarLayout)findViewById(R.id.appbar);
        toolbar =  findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout = findViewById(R.id.toolbar_tab);

        activityThemeColor = setThemeColor(AppStyle.appToolbarColor);
        setToolbarColor(activityThemeColor == 0 ? AppStyle.appToolbarColor : activityThemeColor);
        mTabLayout.setBackgroundColor(activityThemeColor == 0 ? getResources().getColor(AppStyle.appToolbarColor) : getResources().getColor(activityThemeColor));


        TransFragment transFragment = new TransFragment();
        ReportListFragment reportListFragment = new ReportListFragment();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(transFragment);
        fragmentList.add(reportListFragment);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager() ,fragmentList);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }


    /**
     * 设置顶部toolbar的颜色、和状态栏的颜色
     */
    public void setToolbarColor(int colorId){
        toolbar.setBackgroundColor(getResources().getColor(colorId));
        appBarLayout.setBackgroundColor(getResources().getColor(colorId));
        StatusBarUtil.setColor(QueryReportActivity.this ,getResources().getColor(colorId) ,0);
    }


    public class PagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;

        public PagerAdapter(FragmentManager fm , List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {

            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

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
     *
     * @return  ceiling报告路径
     */
    public abstract String setReportUrl();
}
