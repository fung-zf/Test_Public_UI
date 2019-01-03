package com.xzkydz.function.menu.setting;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.xzkydz.function.app.KyApp;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.style.AppStyle;

import es.dmoral.toasty.Toasty;

public class ConfigActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private Button btnTest;
    private EditText et0;
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText etPort;
    private SharedPreferences.Editor editor;
    String[] IP_List = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
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

        findViewById(R.id.btn_test_link).setOnClickListener(this);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        et0 = findViewById(R.id.et_0);
        et1 = findViewById(R.id.et_1);
        et2 = findViewById(R.id.et_2);
        et3 = findViewById(R.id.et_3);
        etPort = findViewById(R.id.et_port);

    }


    /**
     * 设置顶部toolbar的颜色、和状态栏的颜色
     */
    public void setToolbarColor(int colorId) {
        toolbar.setBackgroundColor(getResources().getColor(colorId));
        appBarLayout.setBackgroundColor(getResources().getColor(colorId));
        StatusBarUtil.setColor(ConfigActivity.this, getResources().getColor(colorId), 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initEvent() {

        SharedPreferences preferences = getSharedPreferences(getApplicationContext().getPackageName(), MODE_PRIVATE);
        editor = preferences.edit();

        TextChangeListen[] mTextWatcher = new TextChangeListen[4];
        EditText[] editTexts_List = new EditText[4];
        editTexts_List[0] = et0;
        editTexts_List[1] = et1;
        editTexts_List[2] = et2;
        editTexts_List[3] = et3;

        //循环添加监听事件
        for (int i = 0; i < 4; i++) {
            mTextWatcher[i] = new TextChangeListen(editTexts_List[i]);
            editTexts_List[i].addTextChangedListener(mTextWatcher[i]);
        }

        String Data_IP = preferences.getString("ip", "60.168.132.40");
        IP_List = Data_IP.split("\\.");
        et0.setText(IP_List[0]);
        et1.setText(IP_List[1]);
        et2.setText(IP_List[2]);
        et3.setText(IP_List[3]);
        et3.setSelection(et3.getText().length());

        String port = preferences.getString("port","8085");
        etPort.setText(port);
        etPort.setSelection(etPort.getText().length());
    }


    public class TextChangeListen implements TextWatcher {

        public EditText IP_Edit;

        public TextChangeListen(EditText IP_Edit) {
            super();
            this.IP_Edit = IP_Edit;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 3) {
                if (Integer.parseInt(s.toString()) <= 255) {
                    if (this.IP_Edit == et0) {
                        et1.requestFocus();
                        et1.setSelection(et1.getText().length());
                    }
                    if (this.IP_Edit == et1) {
                        et2.requestFocus();
                        et2.setSelection(et2.getText().length());
                    }
                    if (this.IP_Edit == et2) {
                        et3.requestFocus();
                        et3.setSelection(et3.getText().length());
                    }
                } else {
                    Toast.makeText(ConfigActivity.this, "IP格式不正确！", Toast.LENGTH_SHORT).show();
                    this.IP_Edit.setText("0");
                    this.IP_Edit.setSelection(1);
                }
            } else if (s.length() == 0) {
                if (this.IP_Edit == et0) {
//                    et0.setText("0");
                }
                if (this.IP_Edit == et1) {
                    et0.requestFocus();
//                    et1.setText("0");
                    et0.setSelection(et0.getText().length());
                }
                if (this.IP_Edit == et2) {
                    et1.requestFocus();
//                    et2.setText("0");
                    et1.setSelection(et1.getText().length());
                }
                if (this.IP_Edit == et3) {
                    et2.requestFocus();
//                    et3.setText("0");
                    et2.setSelection(et2.getText().length());
                }
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_test_link) {
            String urlStr = "http://" + et0.getText() + "." + et1.getText() + "." + et2.getText() + "." + et3.getText() + ":" + etPort.getText().toString() + "/lims-platform/api/operate.shtml";
            testLink(urlStr);
        } else if (v.getId() == R.id.btn_ok) {
            okSetting();
        }
    }

    private boolean isRight(){
        if (et0.getText().length() == 0 || et1.getText().length() == 0 || et2.getText().length() == 0 || et3.getText().length() == 0) {
            Toast.makeText(this, "IP地址不正确！", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (etPort.getText().length() == 0) {
            Toast.makeText(this, "端口号不正确！！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public void testLink(String urlStr) {
        if (!isRight()){
            Toasty.error(ConfigActivity.this,"地址设置错误",5).show();
            return;
        }else {
            Toasty.error(ConfigActivity.this,"地址设置OK",5).show();
        }
    }


    private void okSetting() {
        if (!isRight()){
            return;
        }
        String IP_result = et0.getText() + "." + et1.getText() + "." + et2.getText() + "." + et3.getText();
        editor.putString("ip", IP_result);
        editor.putString("port", etPort.getText().toString());
        editor.apply();
        Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
        KyApp.myApp.getPara();
    }


}
