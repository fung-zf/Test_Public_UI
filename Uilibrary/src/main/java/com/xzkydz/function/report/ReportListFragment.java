package com.xzkydz.function.report;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.xzkydz.function.mylibrary.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;



public class ReportListFragment extends Fragment {

    List<File> list = new ArrayList<File>();
    public static  String SDCard;
    public static String currDir ;
    private ReportAdapter adapter;
    private QueryReportActivity mActivity;
    private TextView tvNull;
    private ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (QueryReportActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_list, container, false);
        lv = view.findViewById(R.id.lv);
        tvNull = view.findViewById(R.id.tv_null);
        QueryReportActivity mActivity = (QueryReportActivity) getActivity();
        SDCard = Environment.getExternalStorageDirectory().getAbsolutePath() + mActivity.setReportUrl();
        currDir = SDCard;

        iniEvent();
        adapter = new ReportAdapter(mActivity, list);
        lv.setAdapter(adapter);

        getAllFiles();
        return view;
    }

    private void iniEvent() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final String[] items = {"打开", "分享","删除"};
                final AlertDialog.Builder listDialog = new AlertDialog.Builder(mActivity);
                listDialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File file = list.get(i);
                        switch (which) {
                            case 0:
                                try {
                                    Intent intent = getWordFileIntent(SDCard + "/" + file.getName());
                                    startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 1:
                                Intent share = new Intent(Intent.ACTION_SEND);
                                File f = new File(SDCard + "/" + file.getName());
                                share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
                                share.setType("*/*");
                                startActivity(Intent.createChooser(share, "发送"));
                                break;
                            case 2:

                                initPopupWindow(file);
                                break;

                        }
                    }
                });
                listDialog.show();
            }
        });
    }

    /**
     * @Description ：  taskEntityList :测试任务集合
     * index ：长按的item  id
     */
    private void initPopupWindow(final File file) {
        View popupWindowView = getActivity().getLayoutInflater().inflate(R.layout.popupwindow_myself_delete_report, null);
        final PopupWindow popupWindow = new PopupWindow(popupWindowView, ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.AnimationBottomFade);
        ColorDrawable dw = new ColorDrawable(0xffffffff);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.showAtLocation(getActivity().getLayoutInflater().inflate(R.layout.fragment_report_list, null), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        backgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new popupDismissListener());
        popupWindowView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        Button ok = (Button) popupWindowView.findViewById(R.id.btn_ok);
        Button cancel = (Button) popupWindowView.findViewById(R.id.btn_cancel);
        //删除数据：数据库删除、删除后listview 刷新
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file.isFile()) {
                    file.delete();
//                    Toasty.success(mActivity, "成功删除一篇测试报告", 7).show();
                }
                file.exists();
                getAllFiles();
                popupWindow.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

    /**
     * @Description ：底部弹出的poppupwindow
     */
    class popupDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }


    public void getAllFiles() {
        list.clear();
        File file = new File(currDir);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file2 : files) {
                    list.add(file2);
                }
            }
        }
        // 文件排序
        sort();
        adapter.notifyDataSetChanged();
        tvNull.setText(list.size() == 0 ? "未发现测试报告......" : "");
    }

    private void sort() {
        //使用Collection.sort排序，给定一个比较器，使用匿名内部类实现比较器接口
        Collections.sort(list, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isDirectory() || o1.isFile() && o2.isFile()) {
                    return o1.compareTo(o2);
                }
                return o1.isDirectory() ? -1 : 1;
            }
        });
    }

    //打开Word
    public static Intent getWordFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(param));
        intent.setDataAndType(uri, "application/msword");
        return intent;
    }


    public class ReportAdapter extends BaseAdapter {
        Context context;
        List<File> list;

        public ReportAdapter(Context context, List<File> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                //布局实例化
                convertView = View.inflate(context, R.layout.layout, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            // 设置数据
            File file = (File) getItem(position);
            if (file.isDirectory()) {
                viewHolder.img.setImageResource(R.drawable.folder);
            } else {
                if (file.getName().endsWith(".xls")) {
                    viewHolder.img.setImageResource(R.drawable.home_ico_file_xlsx);
                } else if (file.getName().endsWith(".doc")) {
                    viewHolder.img.setImageResource(R.drawable.home_ico_file_docx);
                } else if (file.getName().endsWith(".pdf")) {
                    viewHolder.img.setImageResource(R.drawable.home_ico_file_pdf);
                }
            }
            viewHolder.name.setText(file.getName());
            viewHolder.time.setText(new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(new Date(file.lastModified())));
            return convertView;
        }

        class ViewHolder {
            ImageView img;
            TextView name;
            TextView time;

            public ViewHolder(View convertView) {
                img = (ImageView) convertView.findViewById(R.id.img);
                name = (TextView) convertView.findViewById(R.id.name);
                time = (TextView) convertView.findViewById(R.id.time);
            }
        }
    }

}



