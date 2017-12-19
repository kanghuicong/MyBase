package com.kang.mybase.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kang.mybase.R;
import com.kang.mybase.base.BaseAdapter;
import com.kang.mybase.model.FileBean;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by KangHuiCong on 2017/12/19.
 * E-Mail is 515849594@qq.com
 */

public class VoiceAdapter extends BaseAdapter {
    List<FileBean> list;
    FileBean file;
    ViewHolder holder;

    public VoiceAdapter(Context context, List<FileBean> list) {
        super(context, list);
        this.list = list;
    }

    public String getPath(int position) {
        return list.get(position).getPath();
    }

    @Override
    public View setView(int position, View convertView, ViewGroup viewGroup) {
        file = list.get(position);
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_voice, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(file.getName());

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.name)
        TextView name;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
