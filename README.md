# MyBase
  本代码库主要注重于代码的封装，优雅的书写每一行代码（说白了就是偷懒，少写点代码hhh）
# 1、类的封装
  一系列baseAcivity，baseFragment等等....整合子类的重复代码
# 2、自定义控件
  写了4个实用的自定义控件MyDialog，MyBottomDialog，MyItem，MyTitle，目前写的都是结合xml文件或Java生成控件的布局，暂时没有涉及到onDraw重绘。
# 3、网络请求封装
  基于okHttp、rxAndroid与retrofit2写的网络请求，已封装，只需调用FunData方法传入请求参数，就在回调接口获得json数据
# 4、收集的各种工具类
  本来是自己写了一些工具类，后来发现了这一堆实用的工具类，忘了是哪位大神的了......已打包成utils.jar
# 5、注解
  自定义注解(学习中)，InjectUtils-->setContentView方法,setOnClickListener方法
# 6、广播
  只写一个实时监控网络变化的广播NetBroadcastReceiver，广播的调用思路基本一致
# 7、图片选择
  主要类库是借用了https://github.com/sd6352051/android-image-picker
		在此基础上修改了一些东西，并再次封装,只需花十秒钟即可搞定图片选择，已重新打包成imagePicker.aar
# 8、头像选择
  图片选择的细化，不过因为图片选择的主要代码是借用他人的，虽然可以将头像选择功能写进去，但改动会比较大，就单独抽出来写,已打包成headerPicker.aar
# 9、权限申请
  模拟器上测试正常。我比较穷，只有一部4.4的Android测试真机，如在其他真机上有问题欢迎告知
# 10、沉浸式状态栏
  沉浸式状态栏其实有很多坑的，网上的大部分都是不完整的，很多情况都没考虑，就比如说：有底部虚拟按键、底部Eedittext弹起键盘。我写的都是自己一步一个坑踩出来的。。。如果还有什么情况我没有想到，欢迎告知~
  ......
  
# 持续更新中.....

