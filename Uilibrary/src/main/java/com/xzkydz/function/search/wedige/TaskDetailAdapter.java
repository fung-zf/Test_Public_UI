package com.xzkydz.function.search.wedige;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.search.module.ITaskRoot;

import java.util.List;

public class TaskDetailAdapter extends BaseAdapter {


    private Context context;
    private List<ITaskRoot> list;

    public TaskDetailAdapter(Context context, List<ITaskRoot> list) {
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
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_listview_task_search_detail, null);
            holder.tvTestFunction = convertView.findViewById(R.id.tv_detail_function);
            holder.tvNumber = convertView.findViewById(R.id.tv_detail_number);
            holder.tvPeople = convertView.findViewById(R.id.tv_detail_people);
            holder.tvTime = convertView.findViewById(R.id.tv_detail_time);
            holder.tvStatue = convertView.findViewById(R.id.tv_detail_statue);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String functionStr = list.get(position).getTestFunction();
        String numberStr = list.get(position).getNumber();
        String peopleStr = list.get(position).getPeopleName();
        String timeStr = list.get(position).getGreateTaskTime();
        boolean _IsCompletet = list.get(position).getTaskStatue();

        holder.tvTestFunction.setText(functionStr);
        holder.tvNumber.setText(numberStr);
        holder.tvStatue.setText(_IsCompletet ? "已完成": "未完成");
        holder.tvPeople.setText(peopleStr);
        holder.tvTime.setText(timeStr);

        Resources resources = context.getResources();
        int greenColor  = resources.getColor(R.color.limegreen);
        int greenRed  = resources.getColor(R.color.indianred);
        holder.tvStatue.setTextColor(list.get(position).getTaskStatue() ? greenColor : greenRed);
        return convertView;
    }

    class ViewHolder {
        TextView tvTestFunction;
        TextView tvNumber;
        TextView tvPeople;
        TextView tvTime;
        TextView tvStatue;
    }
    // listviwe 的数据适配器
}

