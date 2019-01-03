package com.xzkydz.function.data.widget;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.search.module.ITaskRoot;

public class TaskAdater extends BaseAdapter {

    private Context mContex;
    List<ITaskRoot> lists = new ArrayList<>();
    Resources resources;

    public TaskAdater(Context mContex, List<ITaskRoot> lists) {
        this.mContex = mContex;
        this.lists = lists;
        resources = mContex.getResources();
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContex).inflate(R.layout.item_listview_show_task, parent, false);
            viewHolder.tvListNumber = view.findViewById(R.id.tv_item_task);
            viewHolder.tvName = view.findViewById(R.id.tv_item_task_name);
            viewHolder.tvNumber = view.findViewById(R.id.tv_item_task_number);
            viewHolder.tvPeople = view.findViewById(R.id.tv_item_task_people);
            viewHolder.tvTime = view.findViewById(R.id.tv_item_task_time);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ITaskRoot iTaskRoot = lists.get(position);

        viewHolder.tvListNumber.setBackground(resources.getDrawable(iTaskRoot.getTaskStatue() ? R.drawable.vector_drawable_circle_black:R.drawable.vector_drawable_circle_red));

        viewHolder.tvListNumber.setText(position+1 +"");
        viewHolder.tvName.setText(iTaskRoot.getUnitName());
        viewHolder.tvPeople.setText(iTaskRoot.getPeopleName());
        viewHolder.tvNumber.setText(iTaskRoot.getNumber());
        viewHolder.tvTime.setText(iTaskRoot.getGreateTaskTime());
        return view;
    }

    private class ViewHolder {
        TextView tvListNumber;
        TextView tvName;
        TextView tvNumber;
        TextView tvPeople;
        TextView tvTime;
    }
}
