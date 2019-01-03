package com.xzkydz.function.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.xzkydz.function.main.bean.LeftItemBean;
import com.xzkydz.function.main.style.NavigationActivityStyle;
import com.xzkydz.function.mylibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 侧边栏，listView 适配器
 */

public abstract class LeftLvAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private Context context;
    private List<LeftItemBean> list = new ArrayList<>();

    public LeftLvAdapter(Context context ,boolean isShowAllConfig , boolean isPrints ,boolean isShowConfiguration){
        this.context = context;

        this.inflater = LayoutInflater.from(context);
        list.add(new LeftItemBean(context ,R.string.main_product, R.string.main_instructions_text,R.drawable.leftbar_icon_smwd ,R.drawable.icon_arrow,true ,true));
        list.add(new LeftItemBean(context ,R.string.main_product,R.string.main_product_inf,R.drawable.leftbar_icon_cpxx ,R.drawable.icon_arrow,false ,true));
        list.add(new LeftItemBean(context ,R.string.main_product,R.string.main_after_sales,R.drawable.leftbar_icon_shxx ,R.drawable.icon_arrow,false ,false));
        if (isShowAllConfig){
            list.add(new LeftItemBean(context ,R.string.main_configuration,R.string.main_backups,R.drawable.leftbar_icon_bf ,R.drawable.icon_arrow,true ,isPrints));
            if (isPrints){
                list.add(new LeftItemBean(context ,R.string.main_configuration,R.string.main_prints,R.drawable.leftbar_icon_bf ,R.drawable.icon_arrow,false ,true));
            }
            if (isShowConfiguration){
                list.add(new LeftItemBean(context ,R.string.main_configuration,R.string.main_configuration,R.drawable.leftbar_icon_bf ,R.drawable.icon_arrow,false ,false));
            }
        }
        list.add(new LeftItemBean(context ,R.string.main_others,R.string.main_company_inf,R.drawable.leftbar_icon_qyxx ,R.drawable.icon_arrow,true ,true));
        list.add(new LeftItemBean(context ,R.string.main_others,R.string.main_settings,R.drawable.leftbar_icon_sz ,R.drawable.icon_arrow,false ,false));
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_listview_main_left, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tittleTv.setText(list.get(position).getTittleStr());
        holder.iconImv.setImageResource(list.get(position).getIconId());
        holder.nameTv.setText(list.get(position).getNameStr());
        holder.tittleTv.setVisibility(list.get(position).isShowTittle() ? View.VISIBLE : View.GONE);
        holder.splitTv.setVisibility(list.get(position).isShowSplite() ? View.VISIBLE : View.GONE);
        holder.nameTv.setTextColor(context.getResources().getColor(NavigationActivityStyle.leftItemTextColor));

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemRelativeLayoutClickListener(list.get(position).getNameStr());
            }
        });

        return convertView;
    }


    class ViewHolder {
        LinearLayout linearLayoutMain ;
        RelativeLayout relativeLayout;
        TextView tittleTv;
        TextView nameTv;
        ImageView iconImv;
        ImageView arrowImv;
        View splitTv;

        public ViewHolder(View convertView) {
            linearLayoutMain = (LinearLayout)convertView.findViewById(R.id.ll_main_left_listview_item);
            relativeLayout = (RelativeLayout) convertView.findViewById(R.id.rl);
            tittleTv = (TextView)convertView.findViewById(R.id.tv_item_tittle);
            nameTv = (TextView)convertView.findViewById(R.id.tv_item_name);
            iconImv = (ImageView)convertView.findViewById(R.id.imv_item_icon);
            arrowImv = (ImageView)convertView.findViewById(R.id.imv_arrow);
            splitTv = (View) convertView.findViewById(R.id.tv_split);
        }
    }

    /**
     * Item 相对布局点击事件的回调
     * @param stringID : Item 的名称
     */
    public abstract void onItemRelativeLayoutClickListener(int stringID);


}
