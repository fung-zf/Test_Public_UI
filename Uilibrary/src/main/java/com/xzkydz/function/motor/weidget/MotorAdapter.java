package com.xzkydz.function.motor.weidget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xzkydz.function.motor.module.IMotorBean;
import com.xzkydz.function.mylibrary.R;

import java.util.List;

public class MotorAdapter extends BaseAdapter {
    private Context context;
    private List<IMotorBean> motorLibList;

    public MotorAdapter(Context context, List<IMotorBean> motorLibList) {
        this.context = context;
        this.motorLibList = motorLibList;
    }

    @Override
    public int getCount() {
        return motorLibList.size();
    }

    @Override
    public Object getItem(int i) {
        return motorLibList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_listview_motor, viewGroup, false);
            viewHolder.textView0 = view.findViewById(R.id.tv_item0);
            viewHolder.textView1 = view.findViewById(R.id.tv_item1);
            viewHolder.textView2 = view.findViewById(R.id.tv_item2);
            viewHolder.textView3 = view.findViewById(R.id.tv_item3);
            viewHolder.textView4 = view.findViewById(R.id.tv_item4);
            viewHolder.textView5 = view.findViewById(R.id.tv_item5);
            viewHolder.textView6 = view.findViewById(R.id.tv_item6);
            viewHolder.textView7 = view.findViewById(R.id.tv_item7);
            viewHolder.textView8 = view.findViewById(R.id.tv_item8);
            viewHolder.textView9 = view.findViewById(R.id.tv_item9);
            viewHolder.textView10 = view.findViewById(R.id.tv_item10);
            viewHolder.textViewLine = view.findViewById(R.id.tv_item_line);
            viewHolder.relativeLayout = view.findViewById(R.id.relativelayout);
            viewHolder.linearLayout = view.findViewById(R.id.linearlayout);
            viewHolder.linearLayoutTittle = view.findViewById(R.id.ll_tittle);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        if (motorLibList.get(i).getIsAdd()){
            viewHolder.textView0.setTextColor(context.getResources().getColor(R.color.darkolivegreen));
            viewHolder.textView1.setTextColor(context.getResources().getColor(R.color.darkolivegreen));
            viewHolder.textView2.setTextColor(context.getResources().getColor(R.color.darkolivegreen));
            viewHolder.textView3.setTextColor(context.getResources().getColor(R.color.darkolivegreen));
            viewHolder.textView4.setTextColor(context.getResources().getColor(R.color.darkolivegreen));
            viewHolder.textView5.setTextColor(context.getResources().getColor(R.color.darkolivegreen));
            viewHolder.textView6.setTextColor(context.getResources().getColor(R.color.darkolivegreen));
            viewHolder.textView7.setTextColor(context.getResources().getColor(R.color.darkolivegreen));
            viewHolder.textView8.setTextColor(context.getResources().getColor(R.color.darkolivegreen));
            viewHolder.textView9.setTextColor(context.getResources().getColor(R.color.darkolivegreen));
            viewHolder.textView10.setTextColor(context.getResources().getColor(R.color.darkolivegreen));
        }else {
            viewHolder.textView0.setTextColor(context.getResources().getColor(R.color.black_normal));
            viewHolder.textView1.setTextColor(context.getResources().getColor(R.color.black_normal));
            viewHolder.textView2.setTextColor(context.getResources().getColor(R.color.black_normal));
            viewHolder.textView3.setTextColor(context.getResources().getColor(R.color.black_normal));
            viewHolder.textView4.setTextColor(context.getResources().getColor(R.color.black_normal));
            viewHolder.textView5.setTextColor(context.getResources().getColor(R.color.black_normal));
            viewHolder.textView6.setTextColor(context.getResources().getColor(R.color.black_normal));
            viewHolder.textView7.setTextColor(context.getResources().getColor(R.color.black_normal));
            viewHolder.textView8.setTextColor(context.getResources().getColor(R.color.black_normal));
            viewHolder.textView9.setTextColor(context.getResources().getColor(R.color.black_normal));
            viewHolder.textView10.setTextColor(context.getResources().getColor(R.color.black_normal));
        }

        viewHolder.textView0.setText(motorLibList.get(i).getDjxl());
        viewHolder.textView1.setText("型号： "+ motorLibList.get(i).getDjLibName());
        viewHolder.textView2.setText(motorLibList.get(i).getEddy());
        viewHolder.textView3.setText(motorLibList.get(i).getEddl());
        viewHolder.textView4.setText(motorLibList.get(i).getEdgl());
        viewHolder.textView5.setText(motorLibList.get(i).getEdxl());
        viewHolder.textView6.setText(motorLibList.get(i).getKzdl());
        viewHolder.textView7.setText(motorLibList.get(i).getKzgl());
        viewHolder.textView8.setText(motorLibList.get(i).getEdglys());
        viewHolder.textView9.setText(motorLibList.get(i).getJs());
        viewHolder.textView10.setText(motorLibList.get(i).getWgjjdl());
        viewHolder.relativeLayout.setBackgroundColor(i%2==0 ? context.getResources().getColor(R.color.white ): context.getResources().getColor(R.color.ivory ));
        return view;
    }

    private class ViewHolder {
        TextView textView0;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;
        TextView textView8;
        TextView textView9;
        TextView textView10;
        TextView textViewLine;
        RelativeLayout relativeLayout;
        LinearLayout linearLayout;
        LinearLayout linearLayoutTittle;
    }

}
