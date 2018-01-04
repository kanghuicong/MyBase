package com.kang.mybase.model;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.kang.mybase.R;
import com.kang.mybase.base.BaseItem;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by KangHuiCong on 2018/1/4.
 * E-Mail is 515849594@qq.com
 */

public class TestBean3 extends BaseItem {
    Holder holder;

    public TestBean3(Context context) {
        super(context);
    }

    @Override
    public int getItemLayout(int itemType) {
        return R.layout.item_voice;
    }

    @Override
    public void binding(Object data, BaseHolder baseHolder, int itemType) {
        TestBean testBean = (TestBean) data;
        holder = (Holder) baseHolder;
        holder.textView.setText(testBean.getName());
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
        TextView textView;
    }


}
