package com.kang.mybase.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kang.mybase.R;
import com.kang.mybase.base.BaseMyAdapter2;
import com.kang.mybase.model.RefreshItemBean;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by KangHuiCong on 2018/1/15.
 * E-Mail is 515849594@qq.com
 */

public class RefreshAdapter2 extends BaseMyAdapter2<RefreshItemBean,RefreshAdapter2.Holder>{
    private RefreshItemBean testBean;

    public RefreshAdapter2(Context context) {super(context);}

    @Override
    public int setLayout() {
        return R.layout.item_voice;
    }

    @Override
    public Holder setHolder(View view) {
        return new Holder(view);
    }

    @Override
    public void coverView(int i, View view, ViewGroup viewGroup, Holder holder, List<RefreshItemBean> list) {
        testBean = list.get(i);
        holder.textView.setText(testBean.getName());
    }

    public class Holder extends BaseMyAdapter2.BaseHolder{
        public Holder(View view) {
            super(view);
        }
        @InjectView(R.id.name)
        TextView textView;
    }

}
