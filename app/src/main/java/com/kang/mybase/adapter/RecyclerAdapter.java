package com.kang.mybase.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kang.mybase.R;
import com.kang.utilssdk.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kanghuicong on 2018/5/7.
 * E-Mail:515849594@qq.com
 */

public class RecyclerAdapter extends RecyclerView.Adapter {

    List<String> list;
    Context context;
    private OnItemClickListener mOnItemClickListener = null;

    public RecyclerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler, parent, false);
        Holder holder = new Holder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(v,(int)v.getTag());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Holder mHolder = (Holder) holder;
        mHolder.itemView.setTag(position);
        mHolder.tv.setText(list.get(position));

        mHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void deleteItem(int position) {
        if (list == null || list.isEmpty()) {
            return;
        }
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,list.size());
    }


    class Holder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_header)
        ImageView ivHeader;
        @InjectView(R.id.tv_name)
        TextView tv;
        @InjectView(R.id.delete)
        TextView delete;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
