package com.kang.mybase.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    //返回Item的布局Id;
    public abstract int getItemLayout(int itemType);

    //该方法给控件设置数据，首先获得holder对象，holder =convertview.gettag();
    public abstract void binding(T data, BaseHolder baseHolder, int itemType);

    //该方法初始化item的控件,把holder绑定到convertview
    public void initHolder(View convertView, int itemType) {
        convertView.setTag(getHolder(convertView, itemType));
    }

    //创建holder的方法
    public abstract BaseHolder getHolder(View convertView, int itemType);

    //type的种类,重写此方法 把super删掉
    public int getViewTypeCount() {
        return 1;
    }

    //多种itemType根据此方法返回类型区分,重写此方法 把super删掉\
    public int getItemViewType(T data, int position) {
        return 0;
    }

    //创建itemView的方法
    public View creatView(ViewGroup parent, int itemType) {
        return LayoutInflater.from(parent.getContext()).inflate(getItemLayout(itemType), parent, false);
    }

    public Context getContext() {
        return mContext;
    }

    public static abstract class BaseHolder {
        public BaseHolder(View convertView) {
            //ButterKnife插件
            ButterKnife.inject(this, convertView);
        }
    }

}

