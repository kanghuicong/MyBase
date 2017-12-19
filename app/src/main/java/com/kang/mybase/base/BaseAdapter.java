package com.kang.mybase.base;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;


/**
 * Created by KangHuiCong on 2017/12/12.
 * E-Mail is 515849594@qq.com
 */

public abstract class BaseAdapter extends android.widget.BaseAdapter {
    private int count;
    public Context context;
    public List list;

    public BaseAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
        this.count = list.size();
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public abstract View setView(int position, View convertView, ViewGroup viewGroup);

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return setView(i, view, viewGroup);
    }

    public void changeCount(int count) {
        this.count = count;
    }

}
