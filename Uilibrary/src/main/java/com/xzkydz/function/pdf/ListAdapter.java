package com.xzkydz.function.pdf;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.utils.FileSizeUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    private Context mContex;
    private List<PdfInfBean> infBeanList;
    private List<PdfInfDetailBean> detailBeans;

    public ListAdapter(Context mContex, List<PdfInfBean> infBeans) {
        this.mContex = mContex;
        if (infBeans!=null){
            this.infBeanList = infBeans;
        }else {
            infBeanList = new ArrayList<>();
        }
        PdfInfBean pf = new PdfInfBean("煤矿安全规程","standard/meikuanganquan.pdf");
        infBeanList.add(0,pf);
    }

    @Override
    public int getCount() {
        return infBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return infBeanList.get(position);
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
            view = LayoutInflater.from(mContex).inflate(R.layout.item_listview_pdf, parent, false);
            viewHolder.rootLayout = view.findViewById(R.id.LinearLayout);
            viewHolder.imageView = view.findViewById(R.id.pdfView);
            viewHolder.tvName = view.findViewById(R.id.tv_name);
            viewHolder.tvSize = view.findViewById(R.id.tv_size);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Double sizeL = 0D;
        InputStream inputStream = mContex.getClass().getClassLoader().getResourceAsStream("assets/" + infBeanList.get(position).getUrlStr());
        try {
            sizeL = FileSizeUtil.FormetFileSize(inputStream.available(), 3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        viewHolder.tvSize.setText(sizeL + "M");
        viewHolder.tvName.setText(infBeanList.get(position).getNameStr());
        viewHolder.rootLayout.setBackgroundColor(mContex.getResources().getColor(position % 2 == 1 ? R.color.white_hint : R.color.white));
        return view;
    }

    private class ViewHolder {
        LinearLayout rootLayout;
        ImageView imageView;
        TextView tvName;
        TextView tvSize;
    }
}
