package com.tt.czj.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.tt.czj.R;
import com.tt.czj.ui.activity.base.BaseActivity;
import com.tt.czj.utils.UIUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;

/**
 * Created by Explorer on 2016/5/11.
 */
public class SettingsActivity extends BaseActivity {

    /**
     * The M back linear layout.
     */
    @BindView(R.id.setting_back_ll)
    LinearLayout mBackLinearLayout;
    /**
     * The M sign out.
     */
    @BindView(R.id.sign_out)
    Button mSignOut;

    @Override
    public int getContentViewId() {
        return R.layout.activity_settings;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
    }

    /**
     * On click.
     *
     * @param view the view
     */
    @OnClick({R.id.setting_back_ll,R.id.sign_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting_back_ll:
                finish();
                break;
            case R.id.sign_out:
                BmobUser.logOut(this);
                UIUtils.nextPage(this,MainActivity.class);
                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
