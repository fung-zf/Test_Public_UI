package com.xzkydz.function.pdf;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jaeger.library.StatusBarUtil;
import com.shockwave.pdfium.PdfDocument;
import com.shockwave.pdfium.PdfiumCore;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.style.AppStyle;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class PdfListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_list);
        toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.listview);
        setBackUp("检测标准");

        final ListAdapter adapter = new ListAdapter(PdfListActivity.this,getPdfInf());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PdfListActivity.this,PdfActivity.class);
                intent.putExtra("urlFiles",((PdfInfBean)adapter.getItem(position)).getUrlStr());
                intent.putExtra("nameFiles",((PdfInfBean)adapter.getItem(position)).getNameStr());
                startActivity(intent);
            }
        });

    }


    public void setBackUp(String string) {
        toolbar.setTitle(string);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        StatusBarUtil.setColor(PdfListActivity.this, getResources().getColor(AppStyle.appToolbarColor), 0);
        toolbar.setBackgroundColor(getResources().getColor(AppStyle.appToolbarColor));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    abstract public List<PdfInfBean> getPdfInf();

}
