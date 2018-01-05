package com.kang.mybase.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.kang.mybase.R;
import com.kang.mybase.base.BaseItem;
import com.kang.mybase.model.FileBean;

import butterknife.InjectView;

/**
 * Created by KangHuiCong on 2018/1/5.
 * E-Mail is 515849594@qq.com
 */

public class VoiceAdapter extends BaseItem{
    FileBean file;
    Holder holder;
    public VoiceAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemLayout(int itemType) {
        return R.layout.item_voice;
    }

    @Override
    public void binding(Object data, BaseHolder baseHolder, int itemType) {
        file = (FileBean) data;
        holder = (Holder) baseHolder;

        holder.name.setText(file.getName());
    }

    @Override
    public BaseHolder getHolder(View convertView, int itemType) {
        return new Holder(convertView);
    }

    class Holder extends BaseHolder {
        public Holder(View convertView) {
            super(convertView);
        }
        @InjectView(R.id.name)
        TextView name;
    }
}
