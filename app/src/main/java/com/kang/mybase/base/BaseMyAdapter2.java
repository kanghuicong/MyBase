package com.kang.mybase.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kang.mybase.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by KangHuiCong on 2018/1/15.
 * E-Mail is 515849594@qq.com
 */

public abstract class BaseMyAdapter2<T, H extends BaseMyAdapter2.BaseHolder> extends BaseAdapter {
    private Context context;
    private List<T> list = new ArrayList<>();
    private H holder = null;

    public BaseMyAdapter2(Context context) {
        this.context = context;
    }

    public abstract int setLayout() ;

    public abstract H setHolder(View view) ;

    public abstract void coverView(int i, View view, ViewGroup viewGroup,H holder,List<T> list);

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(context, setLayout(), null);
            holder = setHolder(view);
            view.setTag(holder);
        }else {
            holder = (H) view.getTag();
        }
        coverView(i, view, viewGroup, holder,list);
        return view;
    }

    public void refreshData(List _list) {
        if (_list == null) return;
        list.clear();
        list.addAll(_list);
        notifyDataSetChanged();
    }

    public void loadData(List _list) {
        if (_list == null) return;
        list.addAll(_list);
        notifyDataSetChanged();
    }



    public void addData(T obj, int position) {
        list.add(position, obj);
        notifyDataSetChanged();
    }

    public class BaseHolder {
        public BaseHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
