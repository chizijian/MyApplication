package com.tt.czj.ui.activity.own;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tt.czj.R;
import com.tt.czj.ui.activity.base.BaseActivity;
import com.tt.czj.ui.adapter.FragmentPagerAdapter.MyFavorFragmentPagerAdapter;
import com.tt.czj.ui.adapter.base.BaseFragmentmentPagerAdapter;
import com.yhy.tpg.widget.TpgView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * The type My favor activity.
 */
public class MyFavorActivity extends BaseActivity {

    /**
     * The My favor back.
     */
    @BindView(R.id.my_favor_back)
    ImageView myFavorBack;
    /**
     * The Goods detail top.
     */
    @BindView(R.id.favor_goods_detail_top)
    RelativeLayout goodsDetailTop;

    @BindView(R.id.favor_TpgView)
    TpgView mTpgView;

    private boolean isActive;
    private BaseFragmentmentPagerAdapter baseFragmentmentPagerAdapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_my_favor;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        baseFragmentmentPagerAdapter = new MyFavorFragmentPagerAdapter(getSupportFragmentManager());
        mTpgView.setAdapter(baseFragmentmentPagerAdapter);
        mTpgView.setTabGravity(TabLayout.GRAVITY_FILL);
        mTpgView.setTabMode(TabLayout.MODE_FIXED);
        mTpgView.setTabIndicatorHeight(1);
        mTpgView.setTabHeight(40);
        mTpgView.setOnPageChangedListener(new TpgView.OnPageChangedListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                baseFragmentmentPagerAdapter.reloadDataForCurrentPager();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void initData() {
        //loadGoodsInfor(this);
    }

    /**
     * On click.
     *
     * @param view the view
     */
    @OnClick({R.id.my_favor_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_favor_back:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        if(!isActive){
            isActive=true;
            Log.e(TAG, "onResume: 恢复");
            mTpgView.setCurrentPager(0);
            baseFragmentmentPagerAdapter.reloadDataForPager(0);
        }
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isActive=true;
        Log.e(TAG, "onResume: 开始");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!isForeground(this)){
            Log.e(TAG, "onResume: 后台");
            isActive=false;
        }
    }
}


