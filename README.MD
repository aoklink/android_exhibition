##adapter delegate（基于delegate）
  *提取数据层
  *拆分业务层
  

        * Id 资源原则上以驼峰法命名，View 组件的资源 id 需要以 View 的缩写作为 前缀
 * 组件规范
    * 如非必须，避免使用嵌套的 Fragment
    * 不要在 Activity#onDestroy()内执行释放资源的工作，一些工作线程的 销毁和停止，因为 onDestroy()执行的时机可能较晚。可根据实际需要，在 Activity#onPause()/onStop()中结合 isFinishing()的判断来执行
    * 当前Activity的onPause方法执行结束后才会执行下一个Activity的onCreate 方法，所以在 onPause 方法中不适合做耗时较长的工作，这会影响到页面之间的跳 转效率。 
    * 不要在 Android 的 Application 对象中缓存数据 
    * 禁止在设计布局时多次设置子 view 和父 view 中为同样的背景造成页面过 度绘制，将不需要显示的布局进行及时隐藏。 
 * UI规范
    * 禁止在非 ui 线程进行 view相关操作
 * 图片动画规范
    * 统一使用webp格式
    * 在 Activity.onPause()或 Activity.onStop()回调中，关闭当前activity 正在执行的的动画
 * 实践参考
    * Fragment, FragmentManager, FragmentTransaction, FragmentPagerAdapter, DialogFragment,统一使用supportV4包中的相关类型
      * import android.support.v4.app.Fragment;
      * import android.support.v4.app.FragmentManager;
      * import android.support.v4.app.FragmentTransaction;
      * import android.support.v4.app.FragmentPagerAdapter;  
      
## 代码结构说明
* 项目模块
	* APP模块：项目主模块

* 支持Module
	* mvp: 基于kotlin封装mvp框架
	* framework: 
      * 项目基础模块
      * 提供网络处理
      * 图片处理
      * rx封装
	  * adapterDelegate封装
	  * 数据库(用户相关的数据都存数据库、软件相关的设置存SP)
	  * 视频播放
	  * APP模块依赖

	
## 操作说明
* 关于Gradle脚本说明
    * 全局配置在gradle目录中配置
        * config.gradle 全局项目属性配置
        * constants.gradle 全局版本，依赖包管理
        * dependencies.gradle 全局依赖配置
      
* ObjectBox使用说明
    * 获取
      DaoManager.getInstance().queryByKey(Constants.TOKEN).getValue())
     说明：如果你要获取的值是String类型：getValue()、Long类型：getLong，以此类推
    * 存储
     DaoManager.getInstance().putOrUpdateItem(new ConfigBean(Constants.TOKEN, "..."))
    * 批量存
     DaoManager.getInstance().putOrUpdateItems(List<T> )  
      
      