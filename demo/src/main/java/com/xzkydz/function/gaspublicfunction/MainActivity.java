package com.xzkydz.function.gaspublicfunction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.xzkydz.function.motor.module.ConstantData;
import com.xzkydz.function.pdf.PdfActivity;
import com.xzkydz.function.report.QueryReportActivity;
import com.xzkydz.function.utils.DateUtil;
import com.xzkydz.function.utils.DbBackups;
import com.xzkydz.function.utils.DbCopyUtil;
import com.xzkydz.greendao.bean.TaskEnity;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.xzkydz.function.utils.DbBackups.COMMAND_BACKUP;
import static com.xzkydz.function.utils.DbBackups.COMMAND_RESTORE;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.textview)
    TextView textview;
    @BindView(R.id.listview)
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        TaskEnity taskEnity = new TaskEnity();
        taskEnity.setUnitName("徐州12137543453457978974343");
        taskEnity.setGasePumpNumber("第一号");
        taskEnity.setPeopleName("张三、李四、王五");
        taskEnity.set_IsCompleteTask(false);
        taskEnity.setGreateTaskTime(DateUtil.getGreatedTaskTime());
        MyApp.getInstance().getDaoInstant().getTaskEnityDao().insert(taskEnity);


        TaskEnity taskEnity1 = new TaskEnity();
        taskEnity1.setUnitName("哈哈哈");
        taskEnity1.setGasePumpNumber("第一号");
        taskEnity1.setPeopleName("张三、李四");
        taskEnity1.set_IsCompleteTask(true);
        taskEnity1.setGreateTaskTime("2018-06-01 15:12:23");
        MyApp.getInstance().getDaoInstant().getTaskEnityDao().insert(taskEnity1);


        TaskEnity taskEnity2 = new TaskEnity();
        taskEnity2.setUnitName("你好");
        taskEnity2.setGasePumpNumber("第一号");
        taskEnity2.setPeopleName("张三、李四");
        taskEnity2.set_IsCompleteTask(false);
        taskEnity2.setGreateTaskTime("2018-06-01 15:12:23");
        MyApp.getInstance().getDaoInstant().getTaskEnityDao().insert(taskEnity2);


        TaskEnity taskEnity3 = new TaskEnity();
        taskEnity3.setUnitName("你好");
        taskEnity3.setGasePumpNumber("第一号");
        taskEnity3.setPeopleName("张三、李四");
        taskEnity3.set_IsCompleteTask(false);
        taskEnity3.setGreateTaskTime("2018-06-01 15:12:23");
        MyApp.getInstance().getDaoInstant().getTaskEnityDao().insert(taskEnity3);





        String[] item = {"1、闪屏页--> 主界面", "2、电机库选择", "3、历史任务检索界面", "4、测试标准", "5、报告管理、无线传输功能",
                "6、侧边栏说明书", "7、产品信息、企业信息（Markdown文档）",
                "8、测试数据管理","9、拷贝数据库到指定文件夹","10、恢复数据库"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_list, item);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();

                switch (position) {
                    case 0: //主界面
                        startActivity(new Intent(MainActivity.this, MySplash.class));
                        break;
                    case 1: // 电机库的检索
                        startActivityForResult(new Intent(MainActivity.this, MySearchMorotActivtiy.class), ConstantData.Motor_requestCode);
                        break;
                    case 2: // 历史任务的检索
                        startActivityForResult(new Intent(MainActivity.this, MyHistoryTaskSearchActivity.class), ConstantData.Motor_requestCode);
                        break;
                    case 3: //测试标准
                        intent = new Intent(MainActivity.this, MyPdfListActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, PdfActivity.class);
                        intent.putExtra(ConstantPDF.nameFiles, "说明书");
                        intent.putExtra(ConstantPDF.urlFiles, "pdf/说明书.pdf");
                        startActivity(intent);
                        break;
                    case 5: // Markdown 文件
                        intent.setClass(MainActivity.this, MyMarkDownActivity.class);
                        startActivity(intent);
                        break;

                    case 6: // 测试报告管理
                        intent.setClass(MainActivity.this, QueryReportActivity.class);
                        startActivity(intent);
                        break;
                    case 7: // 测试数据管理
                        intent.setClass(MainActivity.this, MyDataManagerActivity.class);
                        startActivity(intent);
                        break;

                    case 8: //数据库拷贝
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("备份数据库")
                                .setMessage("是否备份数据库？")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        new DbBackups(getApplication(),"UI").execute(COMMAND_BACKUP);
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });
                        break;

                    case 9: //数据库恢复

                        new DbBackups(getApplication(),"UI").execute(COMMAND_RESTORE);
                        break;

                }
                ;
            }
        });

    }



    /* 回调方法，从第二个页面回来的时候会执行这个方法*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        int motorResultCode = ConstantData.Motor_resultCode; //
        switch (resultCode) {
            case 22: // 电机库返回码 ConstantData.Motor_resultCode
                String djxhStr = data.getStringExtra(ConstantData.MotorName_resultCode);
                String eddyStr = data.getStringExtra(ConstantData.MotorEddy_resultCode);
                Log.d("asd", "电机型号: " + djxhStr);
                Log.d("asd", "额定电压: " + eddyStr);
                Toast.makeText(this, "电机型号: " + djxhStr + "\n 额定电压：" + eddyStr, Toast.LENGTH_SHORT).show();
                break;

            case 55: // 历史任务返回码  ConstantData.HistoryTask_resultCode
                Long taskId = data.getLongExtra(ConstantData.HistoryTask_ID_resultCode, -1L);
//                Toasty.info(MainActivity.this, taskId.equals(-1L) ? "未选择历史任务" : ("选择的历史任务ID为" + taskId)).show();
                break;
            default:
                break;
        }
    }

}
