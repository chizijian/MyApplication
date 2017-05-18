package com.tt.czj.ui.fragment.nearby_fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.ui.activity.KindActivity;
import com.tt.czj.ui.activity.SearchActivity;
import com.tt.czj.ui.adapter.FragmentPagerAdapter.MyNearByFragmentPagerAdapter;
import com.tt.czj.ui.adapter.base.BaseFragmentmentPagerAdapter;
import com.tt.czj.ui.fragment.base.BaseNearByFragment;
import com.tt.czj.utils.UIUtils;
import com.yhy.tpg.widget.TpgView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;

/**
 * Created by czj on 2016/4/6 0006.
 */
public class NearByFragment extends Fragment {
    /**
     * The constant TAG.
     */
    public static final String TAG = "NearByFragment";

    /**
     * The Unbinder.
     */
    Unbinder unbinder;

    /**
     * The M near by item mode.
     */
    @BindView(R.id.near_by_item_mode)
    ImageView mNearByItemMode;
    /**
     * The M near by search imageview.
     */
    @BindView(R.id.near_by_search_imageview)
    ImageView mNearBySearchImageview;
    /**
     * The M current address.
     */
    @BindView(R.id.current_address)
    TextView mCurrentAddress;

    /**
     * The M current xiaoqu.
     */
    @BindView(R.id.current_xiaoqu)
    Spinner mCurrentXiaoqu;

    /**
     * The M near by tpg view.
     */
    @BindView(R.id.NearBy_TpgView)
    TpgView mNearByTpgView;


    private Context mContext;
    private View mRootView = null;

    private static BaseNearByFragment baseNearByFragment;

    /**
     * The M loc client.
     */
// 定位相关
    // LocationClient mLocClient;
    /**
     * The My listener.
     */
    //public MyLocationListenner myListener = new MyLocationListenner();

    private BaseFragmentmentPagerAdapter fragmentAdapter;
    private static String xiaoqu;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.near_by_fragment, container, false);
        }
        unbinder = ButterKnife.bind(this, mRootView);
        initData();
        return mRootView;
    }

    /**
     * Init data.
     */
    public void initData() {
        fragmentAdapter = new MyNearByFragmentPagerAdapter(getChildFragmentManager());//不能用getFragmentManager(),会导致子fragment不显示
        mNearByTpgView.setAdapter(fragmentAdapter);
        mNearByTpgView.setTabGravity(TabLayout.GRAVITY_FILL);
        mNearByTpgView.setTabMode(TabLayout.MODE_FIXED);
        mNearByTpgView.setTabIndicatorHeight(1);
        mNearByTpgView.setCurrentPager(0);
        mNearByTpgView.setOnPageChangedListener(new TpgView.OnPageChangedListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fragmentAdapter.reloadDataForCurrentPager(mCurrentXiaoqu.getSelectedItem().toString());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //initBaiduLocation();


    }


    /**
     * Init baidu location.
     */
   /* public void initBaiduLocation() {
        // 定位初始化
        mLocClient = new LocationClient(mContext);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan();设置请求定位的间隔时间
        option.setIsNeedAddress(true);  //开启位置信息包括city
        mLocClient.setLocOption(option);
        mLocClient.start();
    }*/
    @Override
    public void onDestroyView() {
        //mLocClient.stop();
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * On click.
     *
     * @param view the view
     */
    @OnClick({R.id.near_by_item_mode, R.id.near_by_search_imageview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.near_by_item_mode:
                UIUtils.nextPage(mContext, KindActivity.class);
                break;
            case R.id.near_by_search_imageview:
                UIUtils.nextPage(mContext, SearchActivity.class);
                break;
        }
    }

    /**
     * On item selected.
     */
    @OnItemSelected(R.id.current_xiaoqu)
    void onItemSelected() {
        Log.e(TAG, "onItemSelected: " + mCurrentXiaoqu.getSelectedItem().toString());
        xiaoqu = mCurrentXiaoqu.getSelectedItem().toString();
        fragmentAdapter.reloadDataForCurrentPager(xiaoqu);
    }

    /**
     * 定位SDK监听函数
     *//*
    private class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuilder sb = new StringBuilder(256);

            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
            } else {
                if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                } else {
                    if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果

                    } else {
                        if (location.getLocType() == BDLocation.TypeServerError) {
                            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                            sb.append("网络不同导致定位失败，请检查网络是否通畅");
                        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                        }
                    }
                }
            }
            mCurrentAddress.setText(location.getAddrStr());
            mCurrentAddress.postInvalidate();
            //mCurrentPrince = location.getCity();
            String mCurrentPrince = mCurrentXiaoqu.getSelectedItem().toString();
            presenter.loadGoodsInfor(mContext, mCurrentPrince, false);
        }

    }*/
    @Override
    public void onDestroy() {
        // 退出时销毁定位
        super.onDestroy();
    }

    public static BaseNearByFragment getInstance(int type) {
        switch (type) {
            case 0:
                Log.e(TAG, "getInstance: 二手");
                if (baseNearByFragment == null||baseNearByFragment instanceof NearByQiuGouFragment)
                    baseNearByFragment = NearBySecondHandFrangment.newInstance(xiaoqu);

                break;
            case 1:
                Log.e(TAG, "getInstance: 求购");
                if (baseNearByFragment == null||baseNearByFragment instanceof NearBySecondHandFrangment)
                    baseNearByFragment = NearByQiuGouFragment.newInstance(xiaoqu);
                break;
        }

        return baseNearByFragment;
    }

}
