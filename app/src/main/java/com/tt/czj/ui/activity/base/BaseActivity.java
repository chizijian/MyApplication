package com.tt.czj.ui.activity.base;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tt.czj.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by czj on 2016/4/1 0001.
 */
public abstract class BaseActivity extends AppCompatActivity{
    /**
     * The constant TAG.
     */
    public static final String TAG = "BaseActivity";


    /**
     * Gets content view id.
     *
     * @return the content view id
     */
    public abstract int getContentViewId();

    /**
     * The Bind.
     */
    Unbinder bind;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(getContentViewId());
        bind=  ButterKnife.bind(this);
        initView(savedInstanceState);
        initTransparentStatusBar();
        initData();
    }

    /**
     * Init transparent status bar.
     */
/*
    * transparent status bar
    * */
    public void initTransparentStatusBar(){
        SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
        systemBarTintManager.setStatusBarTintEnabled(true);
        systemBarTintManager.setNavigationBarTintEnabled(true);
        systemBarTintManager.setTintColor(0);
        final Drawable drawable = ContextCompat.getDrawable(this, R.color.topBarBgColor);
        systemBarTintManager.setStatusBarTintDrawable(drawable);
    }

    /**
     * Init view.
     *
     * @param savedInstanceState the saved instance state
     */
    public abstract void initView( Bundle savedInstanceState);

    /**
     * Init data.
     */
    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();//解除绑定
    }
    /*判断应用是否在前台*/
    public static boolean isForeground(Context context)
    {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
