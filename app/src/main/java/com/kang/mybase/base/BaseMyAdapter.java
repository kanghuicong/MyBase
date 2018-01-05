package com.kang.mybase.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.kang.mybase.model.BaseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KangHuiCong on 2018/1/4.
 * E-Mail is 515849594@qq.com
 */

public class BaseMyAdapter<T> extends BaseAdapter {

    private List mTotalList = new ArrayList<>();

    private BaseItem mItem;

    public BaseMyAdapter(BaseItem mItem) {
        this.mItem = mItem;
    }

    @Override
    public int getViewTypeCount() {
        return mItem.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        return mItem.getItemViewType(mTotalList.get(position), position);
    }

    @Override
    public int getCount() {
        return mTotalList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTotalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mItem.creatView(parent, getItemViewType(position));
            mItem.initHolder(convertView, getItemViewType(position));
        }

        mItem.binding(mTotalList.get(position), (BaseItem.BaseHolder) convertView.getTag(), getItemViewType(position));
        return convertView;
    }

    //加载数据
    public void reLoadData(List list) {
        if (list == null) return;
        mTotalList.addAll(list);
        notifyDataSetChanged();
    }
    //刷新数据
    public void reRefreshData(List list) {
        if (list == null) return;
        mTotalList.clear();
        mTotalList.addAll(list);
        notifyDataSetChanged();
    }
    //添加一条数据
    public void reLoadData(T obj, int position) {
        mTotalList.add(position, obj);
        notifyDataSetChanged();
    }

    public List getTotalList() {
        return mTotalList;
    }

}
