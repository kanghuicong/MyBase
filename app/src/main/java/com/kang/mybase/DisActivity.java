package com.kang.mybase;

import android.widget.TextView;
import com.kang.mybase.base.BaseActivity;
import com.kang.mybase.model.TestBean;
import com.kang.mybase.util.httpClient.BaseModel;
import com.kang.mybase.util.inject.InjectActivityView;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by KangHuiCong on 2017/12/12.
 * E-Mail is 515849594@qq.com
 */

@InjectActivityView(R.layout.dis_activity)
public class DisActivity extends BaseActivity {
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    @Override
    public void init() {
        //do something......
    }


}
