# 题库

### GC是什么？为什么要GC？

    GC是垃圾回收的意思（GarbageCollection），GC可以自动监测对象是否超过作用域而回收内存。
    内存是有限的,手动垃圾回收如果忘记和错误的内存回收会导致程序的不稳定或者崩溃。
    当没有指引的时候，根据GC回收算法，距离上一次调用时间越长越容易被回收

### Switch语句能否作用在byte上，能否作用在long上，能否作用在String上?

    switch能作用在byte、char、short、int,JDK1.7后可以作用在String上

### "=="和equals有什么区别

    ==和equals都可以比较地址,==是运算符，equals属于Object中的方法，方法可以通过重写改变
    改变其行为，如String的equals就是比较字符串内容

### 构造方法能否被重载和重写

    构造方法只能被重载，无法被重写

### 面向对象的特征

    封装、继承、多态和抽象

### 抽象类和接口的区别

    * 抽象类用abstract classs修饰,接口是interface修饰
    * 抽象类可以有任意类型的属性，接口属性只能用static final修饰
    * 抽象类可以有普通方法，接口只有抽象方法
    * 抽象类有构造方法，接口没有。都不能直接实例化，直接借助匿名内部类
    * 抽象类只能单继承，接口可以多实现

### 内部类可以引用它的包含类的成员吗？有没有什么限制？



### RecyclerView和ListView 的区别,性能差异

    ListView提供addHeaderView(View v)、addFooterView(View v)
    ListView继承AbsListView继承AdapterView 并提供setEmptyView(View v)
    ListView适配器直接返回View

    RecyclerView将layout抽象成LayoutManager,LayoutManager负责管理View。
    RecyclerView适配器返回ViewHolder
    RecyclerView支持局部刷新
    RecyclerView内部维护二级缓存,滑出界面的ViewHolder暂时放入Cache结构中,从Cache中移除的
    ViewHolder放入RecyclerViewPool中
    RecyclerViewPool被多个RecyclerView共用。
    RecyclerView可直接放入ScrollView中



### OkHttp实现原理

### Android多线程

### AsyncTask底层实现

### 自定义控件 onMeasure

### 布局绘制

### List Set Map 的区别和实现原理

### 事件分发

### View的绘制流程

### TCP和UDP的区别

### MVP模式和MVP模式的区别

### Socket

### 版本控制工具

### ListView优化 itemView中CheckBox如何复用

### http发送的数据如何加密的

### Activity中两个Fragment如何通信

### app 性能优化

### 进程的声明周期

### app的框架如何搭建,用到的技术点

### 网络请求框架如何封装的

### 收集app的闪退信息

### 服务端图文数据,图片大小数量不一,怎么实现,需要什么字段

### JNI

### NDK
