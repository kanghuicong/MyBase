package com.kang.mybase.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.kang.mybase.R;
import com.kang.mybase.base.BaseAdapter;
import java.util.List;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by KangHuiCong on 2017/12/12.
 * E-Mail is 515849594@qq.com
 */

public class DisAdapter extends BaseAdapter {

    public DisAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View setView(int position, View convertView, ViewGroup viewGroup) {

        return null;
    }




    static class ViewHolder{
        @InjectView(R.id.tvTitle)
        TextView tvTitle;
        @InjectView(R.id.tvContent)
        TextView tvContent;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
