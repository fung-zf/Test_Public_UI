package com.xzkydz.function.pdf;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.jaeger.library.StatusBarUtil;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.style.AppStyle;

import java.io.InputStream;

public class PdfActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private PDFView pdfView;
    private TextView textView;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        toolbar = findViewById(R.id.toolbar);
        pdfView = findViewById(R.id.pdfView);
        textView = findViewById(R.id.textview);
        appBarLayout = findViewById(R.id.appbarlayout);
        Intent intent = getIntent();
        String urlFiles = "assets/" + intent.getStringExtra("urlFiles");
        String nameFiles = intent.getStringExtra("nameFiles");
        initPdf(urlFiles);
        setBackUp(nameFiles);
    }


    public void setBackUp(String string) {
        toolbar.setTitle(string);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        StatusBarUtil.setColor(PdfActivity.this, getResources().getColor(AppStyle.appToolbarColor), 0);
        toolbar.setBackgroundColor(getResources().getColor(AppStyle.appToolbarColor));
    }


    /*初始化PdfVIew*/
    private void initPdf(String urlFiles) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(urlFiles);
        pdfView.fromStream(inputStream)
                .defaultPage(1)/*默认展示第一页*/
                .onPageChange(new MyOnPageChangeListener())//监听页面切换
                .enableSwipe(true)   //是否允许翻页，默认是允许翻页
                .defaultPage(1)
                .enableSwipe(true)
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(0)
                .onLoad(new MyOnLoadCompleteListener())
                .enableAnnotationRendering(false)
                .password(null)
                .scrollHandle(null)
                .load();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private class MyOnPageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageChanged(int page, int pageCount) {
            textView.setText((page + 1) + "/" + pageCount);
        }
    }


    private class MyOnLoadCompleteListener implements OnLoadCompleteListener {
        @Override
        public void loadComplete(int nbPages) {
//            Toast.makeText(PdfActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
        }
    }

}
