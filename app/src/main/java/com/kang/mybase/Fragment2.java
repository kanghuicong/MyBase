package com.kang.mybase;

import android.view.View;
import android.widget.CompoundButton;

import com.kang.mybase.base.BaseFragment;
import com.kang.mybase.custom.view.MySuperItem;

import butterknife.InjectView;

import static com.kang.utilssdk.ToastUtils.showShort;

/**
 * Created by KangHuiCong on 2017/12/11.
 * E-Mail is 515849594@qq.com
 */
public class Fragment2 extends BaseFragment {
    @InjectView(R.id.item_header1)
    MySuperItem itemHeader1;
    @InjectView(R.id.item_header2)
    MySuperItem itemHeader2;
    @InjectView(R.id.item_water)
    MySuperItem itemWater;
    @InjectView(R.id.item_game)
    MySuperItem itemGame;
    @InjectView(R.id.item_traffic)
    MySuperItem itemTraffic;
    @InjectView(R.id.item_other)
    MySuperItem itemOther;
    @InjectView(R.id.item_other1)
    MySuperItem itemOther1;
    @InjectView(R.id.item_switch)
    MySuperItem itemSwitch;
    @Override
    public int setLayout() {
        return R.layout.fragment2;
    }

    @Override
    public void init() {
        setData();
        setClick();
    }

    private void setData() {
        itemHeader1.setLeftImageL("http://p0.so.qhmsg.com/t014156c14c469bae95.jpg",true)
                .setTopLeftText("孙悟空")
                .setBottomLeftText("地球")
                .setBottomLeftTextColor(R.color.colorAccent)
                .setBottomLeftTextSize(12)
                .setBottomRightText("超级赛亚人")
                .setBottomRightTextSize(12);
        itemHeader2.setLeftText("孙悟饭")
                .setRightImageL("http://p0.so.qhmsg.com/t014156c14c469bae95.jpg",false);
        itemGame.setRightText("100.00")
                .setRightTextColor(R.color.colorAccent);
        itemTraffic.setRightText("有未交罚款")
                .setRightTextColor(R.color.Red)
                .setRightTextSize(12);
        itemOther.setTopLeftText("标题")
                .setTopLeftTextSize(16)
                .setBottomLeftText("1111111111111111111111111111111111111111111111111111111111111");
        itemOther1.setTopLeftText("标题")
                .setTopRightText("标题")
                .setBottomLeftText("标题")
                .setBottomRightText("标题")
                .setLeftText("123");
    }

    private void setClick() {
        itemHeader1.setOnClickItem(new MySuperItem.IItemClick() {
            @Override
            public void onClick(View view) {
                showShort("clickItem");
            }
        }).setOnClickLeftImgL(new MySuperItem.IItemClick() {
            @Override
            public void onClick(View view) {
                showShort("clickHeader");
            }
        });

        itemWater.setOnClickBottomLeftText(new MySuperItem.IItemClick() {
            @Override
            public void onClick(View view) {
                showShort("clickText");
            }
        }).setOnClickCheckBox(new MySuperItem.IChangeClick() {
            @Override
            public void onClick(CompoundButton buttonView, boolean isChecked) {
                showShort(isChecked+"");
            }
        });

        itemSwitch.setOnClickSwitch(new MySuperItem.IChangeClick() {
            @Override
            public void onClick(CompoundButton buttonView, boolean isChecked) {
                showShort(isChecked+"");
            }
        });
    }
    @Override
    public void success(Object baseModelList,String type) {

    }

    @Override
    public void failure(String error_code, String error_msg) {

    }

}
