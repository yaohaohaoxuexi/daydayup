package com.month.monthtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.month.monthtest.R;
import com.month.monthtest.bean.BeanOffice;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<BeanOffice> list1;

    public MyAdapter(Context context, ArrayList<BeanOffice> list1) {
        this.context=context;
        this.list1=list1;
    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Override
    public Object getItem(int position) {
        return list1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView=LayoutInflater.from(context).inflate(R.layout.list_view, null);
        }
        ImageView headimg = (ImageView) convertView.findViewById(R.id.headimg);
        TextView auth_type = (TextView) convertView.findViewById(R.id.auth_type);
        TextView good_at = (TextView) convertView.findViewById(R.id.good_at);
        TextView hos_name = (TextView) convertView.findViewById(R.id.hos_name);
        TextView jobs = (TextView) convertView.findViewById(R.id.jobs);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView office_name = (TextView) convertView.findViewById(R.id.office_name);

        auth_type.setText(list1.get(position).getAuth_type());
        good_at.setText(list1.get(position).getGood_at());
        hos_name.setText(list1.get(position).getHos_name());
        jobs.setText(list1.get(position).getJobs());
        name.setText(list1.get(position).getName());
        office_name.setText(list1.get(position).getOffice_name());

        ImageLoader imageLoader=ImageLoader.getInstance();
        imageLoader.displayImage(list1.get(position).getHeadimg(), headimg);
        return convertView;
    }

}

