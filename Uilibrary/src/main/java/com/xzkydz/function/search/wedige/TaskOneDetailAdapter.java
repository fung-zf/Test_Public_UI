package com.xzkydz.function.search.wedige;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xzkydz.function.motor.module.ConstantData;
import com.xzkydz.function.search.module.ITaskRoot;
import com.xzkydz.function.search.module.ItemBean;
import com.xzkydz.function.search.view.SearchHistoryTaskActivity;
import com.xzkydz.function.mylibrary.R;

import java.util.ArrayList;
import java.util.List;

public class TaskOneDetailAdapter extends BaseAdapter {

    private SearchHistoryTaskActivity context;
    private ITaskRoot taskRoot;
    private List<ItemBean> itemBeanList;
    private Class testClass;

    public TaskOneDetailAdapter(SearchHistoryTaskActivity context, ITaskRoot taskRoot ,Class testClass) {
        this.context = context;
        this.testClass = testClass;
        this.taskRoot = taskRoot;
        itemBeanList = new ArrayList<>();
        Resources resources = context.getResources();
        if (taskRoot != null) {
            itemBeanList.add(new ItemBean().setNameStr(resources.getString(R.string.task_unit)).setContentStr(taskRoot.getUnitName()));
            itemBeanList.add(new ItemBean().setNameStr(resources.getString(R.string.task_test_function)).setContentStr(taskRoot.getTestFunction()));
            itemBeanList.add(new ItemBean().setNameStr(resources.getString(R.string.task_number)).setContentStr(taskRoot.getNumber()));
            itemBeanList.add(new ItemBean().setNameStr(resources.getString(R.string.task_test_people)).setContentStr(taskRoot.getPeopleName()));
            itemBeanList.add(new ItemBean().setNameStr(resources.getString(R.string.task_statue)).setContentStr(taskRoot.getTaskStatue() ? resources.getString(R.string.task_complete) : resources.getString(R.string.task_uncomplete)));
            itemBeanList.add(new ItemBean().setNameStr(resources.getString(R.string.task_test_time)).setContentStr(taskRoot.getGreateTaskTime()));
        }
    }

    @Override
    public int getCount() {
        return itemBeanList.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return itemBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_listview_task_search_one_detail, null);
            holder.tvName = convertView.findViewById(R.id.tv_name);
            holder.tvContent = convertView.findViewById(R.id.tv_content);
            holder.btnContinueTest = convertView.findViewById(R.id.btn_continue_test);
            holder.btnReusePares = convertView.findViewById(R.id.btn_resuse_pares);
            holder.linearLayoutBtn = convertView.findViewById(R.id.linearlayout_btn);
            holder.linearLayoutTv = convertView.findViewById(R.id.linearlayout_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        if (position < itemBeanList.size() ){
            holder.tvName.setText(itemBeanList.get(position).getNameStr());
            holder.tvContent.setText(itemBeanList.get(position).getContentStr());
            holder.linearLayoutTv.setVisibility(View.VISIBLE);
            holder.linearLayoutBtn.setVisibility(View.GONE);
            holder.tvContent.setTextColor(position == 4 ? Color.parseColor("#B22222") : Color.parseColor("#8A000000"));
        }else if (itemBeanList.size()!=0){
            holder.linearLayoutTv.setVisibility(View.INVISIBLE);
            holder.linearLayoutBtn.setVisibility(View.VISIBLE);
            if (taskRoot!=null && taskRoot.getTaskStatue()){
                holder.btnContinueTest.setVisibility(View.GONE);
            }

            holder.btnContinueTest.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra(ConstantData.HistoryTask_ID_resultCode,taskRoot.getTaskId() );
                    intent.setClass(context,testClass);
                    context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle());
                    context.finish();
                }
            });

            // 返回历史任务ID
            holder.btnReusePares.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra(ConstantData.HistoryTask_ID_resultCode, taskRoot.getTaskId());
                    context.setResult(ConstantData.HistoryTask_resultCode, intent);
                    context.finish();
                }
            });
        }else {
            holder.linearLayoutTv.setVisibility(View.INVISIBLE);
            holder.linearLayoutBtn.setVisibility(View.INVISIBLE);
        }
        return convertView;
    }

    class ViewHolder {
        LinearLayout linearLayoutTv;
        LinearLayout linearLayoutBtn;
        TextView tvName;
        TextView tvContent;
        Button btnContinueTest;
        Button btnReusePares;
    }
}

