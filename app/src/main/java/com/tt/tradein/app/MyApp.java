package com.tt.tradein.app;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tt.tradein.di.component.AppComponent;
import com.tt.tradein.di.component.DaggerAppComponent;
import com.tt.tradein.di.modules.AppModule;
import com.tt.tradein.utils.GlobalDefineValues;
import com.umeng.socialize.PlatformConfig;

import c.b.BP;
import cn.bmob.v3.Bmob;

/**
 * Created by czj on 2017/13 0013.
 */
public class MyApp extends Application {
    /**
     * The constant TAG.
     */
    public static final String TAG = "MyApp";
    private AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        initBmob();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        mAppComponent.inject(this);
        initBaiDuMap();
        initShare();
        initImageLoader();
    }

    /**
     * Init share.
     */
    public void initShare(){
        PlatformConfig.setSinaWeibo("3400588978","c685e5d1ba7e8b6dddf82b9e5aea0a44");
        //新浪微博 appkey appsecret
        PlatformConfig.setWeixin("wx5a714face5a6640d", "03ac8c788f750d68a73c4742da77bb23");
        //微信 appid appsecret
    }

    /**
     * Init bmob.
     */
    public void initBmob(){
        Bmob.initialize(this, GlobalDefineValues.BmobApplicationID);
        BP.init(GlobalDefineValues.BmobApplicationID);
    }

    /**
     * Get app component app component.
     *
     * @return the app component
     */
    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    /**
     * Gets application.
     *
     * @param context the context
     * @return the application
     */
    public static MyApp getApplication(Context context) {
        return (MyApp) context.getApplicationContext();
    }

    /**
     * Init bai du map.
     */
    public void initBaiDuMap(){
        SDKInitializer.initialize(getApplicationContext());
    }

    /**
     * Init image loader.
     */
    public void initImageLoader(){
        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .writeDebugLogs() //打印log信息
                .build();
        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);
    }
}
