package com.month.monthtest.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.month.monthtest.R;
import com.month.monthtest.bean.Bean12;


public class MyAdapter3 extends BaseAdapter {


    private Context context;
    private ArrayList<Bean12> list2;

    public MyAdapter3(Context context, ArrayList<Bean12> list2) {
        this.context=context;
        this.list2=list2;
    }

    @Override
    public int getCount() {
        return list2.size();
    }

    @Override
    public Object getItem(int position) {
        return list2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView=LayoutInflater.from(context).inflate(R.layout.list_view2, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(list2.get(position).getName());
        return convertView;
    }


}
