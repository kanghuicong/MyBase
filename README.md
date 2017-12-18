# MyBase
  本代码库主要注重于代码的封装，优雅的书写每一行代码（持续更新中，先把已经写好的上传）
# 1、类的封装
  一系列baseAcivity，baseFragment等等....整合子类的重复代码
# 2、自定义控件
  目前只有3个实用的自定义控件MyDialog，MyItem，MyTitle
# 3、网络请求封装
  基于okHttp、rxAndroid与retrofit2写的网络请求，参考了网上代码，在此基础上再进行封装。只需调用FunData方法传入请求参数，就在回调接口获得json数据
# 4、收集的各种工具类
  收集的工具类，忘了是哪位大神的了......已打包成utils.jar
# 5、注解
  自定义注解(学习中)，InjectUtils-->setContentView方法,setOnClickListener方法
# 6、广播
  目前只写一个实时监控网络变化的广播NetBroadcastReceiver
# 7、图片选择
  主要类库是借用了https://github.com/sd6352051/android-image-picker
		在此基础上修改了一些东西，并再次封装,只需花十秒钟即可搞定图片选择。
# 8、权限申请
