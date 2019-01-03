# PublicFunction
矿一公共模块库（注意：minSdkVersion 19）
# 效果演示
![jptu](https://github.com/yknn/PublicFunction/blob/master/%E6%88%AA%E5%B1%8F.gif?raw=true)  

# 演示APK

[演示Apk下载](https://github.com/yknn/PublicFunction/blob/master/app/release/app-release.apk)

# 获取库
---
[最新版本](https://jitpack.io/#yknn/PublicFunction)

To get a Git project into your build:  

Step 1. 

Add it in your root build.gradle at the end of repositories:

```groovy
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency

```groovy
dependencies {
	         compile 'com.github.yknn:PublicFunction:v1.5'
	}
```

# 使用方法
---

## 说明

- 项目中使用的切图名称，如果和库中切图名称相同，会自动替换。

## 一、清单文件

### 1、权限
```java
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <!--在sdcard中创建/删除文件的权限 -->
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WAKE_LOCK"/>

    <!--蓝牙权限-->
    <uses-permission android:name="android.permission.BLUETOOTH" />
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />

```
### 2、添加文件传输服务
```java
  <service android:name="com.xzkydz.function.ftp.ftptwo.FTPServerService" />

```

## 二、Application

1. 继承 com.xzkydz.function.app.KyApp;
2. 复写 setAppStyleColor()方法。主要是设置APP的名称与主题颜色。
```java
public class App extends KyApp {
    
    // 设置APP的名称、颜色
    @Override
    public void setAppStyleColor() {
        super.setAppStyleColor();
        //APP 名称
        AppStyle.appNameId = R.string.app_name;
        //APP 主题色
        AppStyle.appToolbarColor = R.color.colorPrimary;
    }
}

```
3. 在清单文件中声明。

## 三、闪屏页
### 1、继承 SplashActivity
```java
public class MySplash extends SplashActivity {

    @Override
    public Class<?> getNavigationActivity() {
        return MyNavigationActivity.class;
    }

    @Override
    public void setShowContent() {
        super.setShowContent();
        setAppName("我是APP名称");
        setLawContent("我是法律内容");
        setVersionName("我是版本号");
    }
}

```
### 2、设置 MySplash 为启动页
```java
 <activity android:name=".MySplashActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
```


## 四、产品信息 、企业信息


### 1、继承 MarkDownActivity 类
```java

/**
 * Date: 2018/3/21  10:18
 * Description: 显示企业信息、产品信息。
 * 企业信息Markdown 文件默认显示在，产品信息需要设置文件的目录。
 */

public class MyMarkDownActivity extends MarkDownActivity {
    @Override
    public String setProductInfFileUrl() {
        return "markdown/ProductInf.md";
    }
}

```
### 2、将第一步继承的类，加入项目的AndroidManifest.xml文件。   


## 五、主界面

### 1.继承 NavigationActivity类 
    
```java

/**
 * 继承 NavigationActivity 类
 */


public class MyNavigationActivity extends NavigationActivity {
    @Override
    public NavigationActivityManagerInf getNavigationActivityManager() {

//        int[] bgArray = {R.drawable.selector_navi_cs,
//                R.drawable.selector_navi_shjgl,
//                R.drawable.selector_navi_bgchx,
//                R.drawable.selector_navi_cshbzh,
//                R.drawable.selector_navi_chgq,
//                R.drawable.selector_navi_tc} ;

        //设置界面样式
        NavigationActivityManagerInf activityManager = new NavigationActivityManager();
        //设置左上角显示名称
        activityManager.setAppName(R.string.app_name);
        //侧边栏是否显示“打印”项
        activityManager.setShowPrints(true);
        //侧边栏显示配置项
        activityManager.setShowConfiguration(true);

//        设置主界面按钮的背景（选择器 ，如果对应切图的命名和库中的切图命名是一样的，则会自动替换项目中的切图）
//        activityManager.setMainButtonBg(bgArray);
//        设置主界面背景图
//        activityManager.setNavigationActivityBg(R.drawable.home_bg);
//        设置侧边栏顶部背景图
//        activityManager.setNavigationViewHeardBg(R.drawable.leftbar_top_bg);

        return activityManager;
    }


    @Override
    public void onTestButtonClickListener() {
        // 测试按钮点击事件
    }


    @Override
    public void onDataManagerClickListener() {
        // 数据管理按钮点击事件
    }

    @Override
    public void onSensorButtonClicklistener() {
        // 传感器按钮点击事件
    }

    @NonNull
    @Override
    public Class<?> getMarkdownActivity() {
        return MyMarkDownActivity.class;
    }

    /**
     * 如果显示“打印”选项，则复写此方法获取按钮的点击事件。如果不显示“打印”按钮，则无需复写此方法。
     */
    @Override
    public void onPrintsItemClickListener() {
        super.onPrintsItemClickListener();
    }
}

```   
   
   
### 2、将第一步继承的类，加入项目的AndroidManifest.xml文件。   

注意主题：
```java
android:theme="@style/AppTheme.NoActionBar"
```


## 六、历史任务搜索功能

`发现BUG，暂时没修改，不影响使用。部分细节需要优化。`   

### 1、第一步

测试任务表的实体类`TaskEnity` 实现接口`ITaskRoot`;

### 2、第二步

创建类`HistoryTaskSearchActivity` 继承 `SearchHistoryTaskActivity`，实现抽象方法`getTaskDataList()` 、`getTestClass()`;

说明:   
1、`getTaskDataList()` 在此方法中返回测试任务表实体类集合。   
2、`getTestClass()` 返回测试界面类。  
```java
public class HistoryTaskSearchActivity extends SearchHistoryTaskActivity {

    //返回所有的任务集合
    @Override
    protected List<ITaskRoot> getTaskDataList() {
        List<ITaskRoot> list = new ArrayList<>();
        List<TaskEnity> taskEnities = MyApp.getDaoInstant().getTaskEnityDao().loadAll();
        list.addAll(taskEnities);
        return list;
    }

    // 返回测试测试界面
    @Override
    protected Class getTestClass() {
        return null;
    }

    // 有可能再暴露一个方法，用于将选中的测试任务再Application中设为全局变量
}
```  
### 3、第三步 

1、清单文件
```java
 <activity
     android:name="com.xzkydz.historyTaskSearch.HistoryTaskSearchActivity"
     android:screenOrientation="portrait"
     android:theme="@style/Transparent" />
```

## 七、电机库选择、新建功能
![jptu](https://github.com/yknn/PublicFunction/blob/master/%E7%94%B5%E6%9C%BA%E5%BA%93.gif?raw=true)  

### 1、第一步
 
 `MotorSelectorActivity` 继承 `com.xzkydz.function.GreatedTaskMotorSelector.view.MotorSelectorActivity`,实现三个抽象函数`getMotorList()`,`insertNewMotor(finalMotorBean motorBean)`,`deleteMotor(IMotorBean iMotorBean)`;
 
 说明：
 1、`getMotorList()` 用于获取数据库所有的电机库实体。   
 2、`insertNewMotor(finalMotorBean motorBean)` 往数据库中插入一条电机信息。
 3、`deleteMotor(IMotorBean iMotorBean)`删除一条电机信息；

```java
public class MyMotorSelectorActivity extends MotorSelectorActivity {

    MyApp myApp = MyApp.getInstance();
    private List<IMotorBean> iMotorBeans;
    //获取所有的电机库
    @Override
    public List<IMotorBean> getMotorList() {
        // 声明iMotorBeans集合，并抛出去
        iMotorBeans = new ArrayList<>();
        // 在线程中对数据库进行读写操作，防止对UI线程造成阻塞，引起卡顿
        new Thread(new Runnable() {
            @Override
            public void run() {
                //从数据库中获取所有的电机，返回电机实体对象的集合

                List<MotorEnity> motorBeanList = myApp.getDaoInstant().getMotorEnityDao().loadAll();
                //对集合进行倒序操作
                Collections.reverse(motorBeanList);
                //为什么这样转换，其中涉及到java接口和实例类之间的关系的相关知识
                iMotorBeans.addAll(motorBeanList);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 隐藏加载框
                        hideDialog();
                        // 刷新ListView
                        initListView(iMotorBeans);
                    }
                });
            }
        }).start();

        return iMotorBeans;
    }

    // 插入一条新的电机数据
    @Override
    public void insertNewMotor(final com.xzkydz.function.InsertMotorLib.MotorBean motorBean) {

        final MotorEnity motorEnity = new MotorEnity();
        motorEnity.setDJ_LIB_NAME(motorBean.getDJ_LIB_NAME());
        motorEnity.setEDDY(motorBean.getEDDY());
        motorEnity.setEDDL(motorBean.getEDDL());
        motorEnity.setEDGL(motorBean.getEDGL());
        motorEnity.setEDXL(motorBean.getEDXL());
        motorEnity.setJS(motorBean.getJS());
        motorEnity.setKZDL(motorBean.getKZDL());
        motorEnity.setKZGL(motorBean.getKZGL());
        motorEnity.setWGJJDL(motorBean.getWGJJDL());
        motorEnity.setIsAdd(true);
        motorEnity.setDJXL("手动录入电机"); // 电机系列

        iMotorBeans.add(0,motorEnity);
        setMotorAllList(iMotorBeans);
        initListView(iMotorBeans);

        new Thread(new Runnable() {
            @Override
            public void run() {
                myApp.getDaoInstant().getMotorEnityDao().insert(motorEnity);
            }
        }).start();
    }

    // 删除一条电机数据
    @Override
    public void deleteMotor(final IMotorBean iMotorBean) {
        iMotorBeans.remove(iMotorBean);
        setMotorAllList(iMotorBeans);
        initListView(iMotorBeans);
        new Thread(new Runnable() {
            @Override
            public void run() {
                myApp.getDaoInstant().getMotorEnityDao().deleteByKey(iMotorBean.getId());
            }
        }).start();
    }
}
```

### 2、第二步
```java
<activity android:name="com.xzkydz.greateTask.view.MotorSelectorActivity"
            android:windowSoftInputMode = "stateAlwaysHidden|adjustPan"
            android:theme="@style/AppTheme.NoActionBar"/>
```


## 附录：   
1、主界面按钮背景 selector  
```java
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
	// 按下的状态  
    <item android:state_pressed="true" android:drawable="@drawable/home_icon_bg_pre"/>
    // 默认状态  
    <item android:drawable="@drawable/home_icon_bg"/>

</selector>
```
2、项目build 文件
```java
apply plugin: 'com.android.application'

android {
    compileSdkVersion 26
    defaultConfig {
        applicationId "com.xzkydz.function.gaspublicfunction"
        minSdkVersion 19
        targetSdkVersion 26
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation fileTree(include: ['*.jar'], dir: 'libs')
    implementation 'com.android.support:appcompat-v7:26.1.0'
    implementation 'com.android.support.constraint:constraint-layout:1.0.2'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'com.android.support.test:runner:1.0.1'
    androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
    implementation project(':Uilibrary')
}
```


