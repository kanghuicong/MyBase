package com.kang.mybase.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.ReferenceQueue;

import butterknife.ButterKnife;

/**
 * Created by KangHuiCong on 2018/1/4.
 * E-Mail is 515849594@qq.com
 */

public abstract class BaseItem<T> extends BaseBean {
    private Context mContext;

    public BaseItem(Context context) {
        this.mContext = context;
    }

    public abstract Object getItemLayout(int itemType);

    public abstract void binding(T data, BaseHolder baseHolder, int itemType);

    public void initHolder(View convertView, int itemType) {
        convertView.setTag(getHolder(convertView, itemType));
    }

    public abstract BaseHolder getHolder(View convertView, int itemType);

    public int getViewTypeCount() {
        return 1;
    }

    public int getItemViewType(T data, int position) {
        return 0;
    }

    public View createView(ViewGroup parent, int itemType) {
        if (getItemLayout(itemType) instanceof View)
            return (View)getItemLayout(itemType);
        return LayoutInflater.from(parent.getContext()).inflate((int)getItemLayout(itemType), parent, false);
    }

    public Context getContext() {
        return mContext;
    }

    public static abstract class BaseHolder {
        public BaseHolder(View convertView) {
            ButterKnife.inject(this, convertView);
        }
    }

}

