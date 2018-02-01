# MyBase
  本代码库主要注重于代码的封装，优雅的书写每一行代码（说白了就是偷懒，少写点代码hhh）
# 1、基础类的封装
  12.14---一系列baseAcivity，baseFragment，baseAdapter等等....主要是一个代码习惯问题，减少子类公共代码，让子类看起来不那么臃肿，也方便统一修改。
# 2、自定义控件
### MyDialog
  dialog样式是系统样式，不同手机样式可能不一样  
![MyDialog](https://github.com/kanghuicong/MyBase/blob/master/app/src/main/assets/myDialog.png)  
```java
new MyDialog("设置", "是否前往设置权限", "取消", "确定", new IDialog() {
    @Override
    public void leftClick(DialogInterface dialog) {}
    @Override
    public void rightClick(DialogInterface dialog) {
        getAppDetailsSettings();
    }
}).show(activity.getFragmentManager(), "settingDialog");
```
### MyBottomDialog
  可以动态修改item的个数，点击事件对应相应的position即可  
![MyBottomDialog](https://github.com/kanghuicong/MyBase/blob/master/app/src/main/assets/myBottomDialog.png) 
```java
//调用  
String[] list = {"图库", "拍照"};
new MyBottomDialog(this, list, true,this);
//点击事件回调  
@Override
public void iDialog(int position) {
    //position即list的位置
    switch (position) {
        case 0://调用系统图库
	    intoGallery();
	    break;
	case 1://调用摄像头
	    intoCamera();
	    break;
    }
}
```
### MySuperItem：  
  12.20---编写MySuperItem  
  12.21---（MySuperItem）完成基础布局，添加动态修改  
  12.22---（MySuperItem）完善基础布局，添加CheckBox，头像加载，点击事件回调  
  12.23---（MySuperItem）修改尺寸，完成适配，添加switch  
![MySuperItem](https://github.com/kanghuicong/MyBase/blob/master/app/src/main/assets/mySuperItem.png)    	
```java
提供链式动态修改方法，如下部分方法：
    itemHeader1.setLeftImageL("http://p0.so.qhmsg.com/t014156c14c469bae95.jpg",true)
            .setTopLeftText("孙悟空")
            .setBottomLeftText("地球")
            .setBottomLeftTextColor(R.color.colorAccent)
            .setBottomLeftTextSize(12)
            .setBottomRightText("超级赛亚人")
            .setBottomRightTextSize(12);
```
### MyRefresh:  
  12.25---自定义刷新加载布局实现，已完成基本实现思路  
  12.26---修改细节，添加各种情况的提示语，目前只针对AbsListView  
  12.26---解决各种特殊情况下的显示异常  
  12.27---添加回弹效果以及数据加载转圈效果  
  01.03---将网络请求封装进来  
  01.04---封装Myadapter  
  01.05---继续封装简化数据请求及UI更新代码  
  01.08---优化回弹效果，主要解决快速重复下拉/上拉时回弹效果、动画显示异常  
  01.11---优化回弹效果，主要解决位于头部时，下拉X距离紧接着上拉X+Y距离，会将底部控件拉出；底部亦然  
  01.12---添加ScrollView；当没有上拉加载需求时，设置app:isNoLoad即可改为上拉空白回弹  
  ![MyRefresh](https://github.com/kanghuicong/MyBase/blob/master/app/src/main/assets/myRefresh.gif)   
```java
    RefreshUtil refreshUtil;
    RefreshAdapter refreshAdapter;
    @Override
    public void init() {
        refreshUtil = new RefreshUtil(myRefresh,listView, this,this);
        refreshAdapter = new RefreshAdapter(activity);
    }
    //网络请求-->retrofit2+okhttp+rxandroid,具体封装请看详细代码
    @Override //下拉刷新接口
    public Observable refreshObservable() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        return getApi().test(map);
    }
    @Override //上拉加载接口
    public Observable loadObservable() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", "value");
        return getApi().test(map);
    }
    @Override //刷新成功回调
    public void refreshSuccess(Object baseModelList) {
        //RefreshAllBean是数据格式，之所以返回类型是Object，是考虑到后台数据格式不统一
	//如果后台返回的格式可以统一则可以继续修改RefreshData中的方法，就不需要强转类型了
        refreshUtil.refreshSuccess(refreshAdapter,((RefreshAllBean) baseModelList).getData());//更新UI
    }

    @Override //加载成功回调
    public void loadSuccess(Object baseModelList) {
        refreshUtil.loadSuccess(((RefreshAllBean) baseModelList).getData());//更新UI
    }
    //数据请求失败的操作因为基本一样，所以都写在了RefreshData，如果需要失败操作可以自己添加接口回调
    //以上代码即可实现所有数据刷新加载及UI更新
```
  与现有的大部分刷新加载框架相比，我处理的手势动作更多，例如正在刷新时是不可以做下拉操作（起码我用过的两个框架都不可以）、不支持空白回弹等等  
### MyLoading：  
  12.27---自定义控件MyLoading，用于数据加载时等待反馈，并结合MyDialog，封装loading加载框  
# 3、网络请求封装
  12.14---基于okHttp、rxAndroid与retrofit2写的网络请求，已封装，只需调用FunData方法传入请求参数，就在回调接口获得json数据，数据固定返回格式为{"code":"0","msg":"msg","data":{}}  
  12.28---优化请求步骤，数据固定返回格式改为{"code":"0","msg":"msg"}，回调接口数据直接强转为model，不需要再将json字符串进行转换转换  
# 4、收集的各种工具类
  12.14---本来是自己写了一些工具类，后来发现了这一堆实用的工具类，忘了是哪位大神的了......已打包成utils.jar
# 5、注解
  12.14---自定义注解(学习中)，InjectUtils-->setContentView方法，setOnClickListener方法
# 6、广播
  12.14---只写一个实时监控网络变化的广播NetBroadcastReceiver，广播的调用思路基本一致
# 7、权限申请
  12.14---模拟器上测试正常。我比较穷，只有一部4.4的Android测试真机，如在其他真机上有问题欢迎告知
# 8、沉浸式状态栏
  12.14---沉浸式状态栏其实有很多坑的，网上的大部分都是不完整的，很多情况都没考虑，就比如说：有底部虚拟按键、底部Eedittext弹起键盘。因为真机的限制，我只在自己这部华为4.4.4的机子以及AS自带模拟器上测试正常，如有特殊情况欢迎告知
# 9、图片选择
  12.15---主要类库是借用了https://github.com/sd6352051/android-image-picker  
		在此基础上修改了一些东西，并再次封装,只需花十秒钟即可搞定图片选择，已重新打包成imagePicker.aar
# 10、头像选择
  12.18---图片选择的细化，不过因为图片选择的主要代码是借用他人的，虽然可以将头像选择功能写进去，但改动会比较大，就单独抽出来写,已打包成headerPicker.aar  
  01.03---进一步封装，代码简化
# 11、音频相关
  12.19---音频的录制，停止，查看，播放（目前暂时没有考虑上传后格式与IOS相适配的问题）
# 12、其他
  01.05---添加代码混淆；反编译（反编译工具及步骤在decompiling文件夹内）  
  02.01---添加ARouter-->页面路由

# 持续更新中.....

