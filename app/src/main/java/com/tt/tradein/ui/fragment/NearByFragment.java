package com.tt.tradein.ui.fragment;

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

import com.baidu.location.LocationClient;
import com.tt.tradein.R;
import com.tt.tradein.ui.activity.SearchActivity;
import com.tt.tradein.ui.adapter.MyFragmentPagerAdapter;
import com.tt.tradein.utils.UIUtils;
import com.tt.tradein.widget.CustomExpandableListView;
import com.yhy.tpg.widget.TpgView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;

/**
 * Created by czj on 2016/4/6 0006.
 */
public class NearByFragment extends Fragment{
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

    @BindView(R.id.current_xiaoqu)
    Spinner mCurrentXiaoqu;
    /**
     * The M near by goods list.
     */
    @BindView(R.id.near_by_goods_list)
    CustomExpandableListView mNearByGoodsList;
    @BindView(R.id.NearBy_TpgView)
    TpgView mNearByTpgView;


    private Context mContext;
    private View mRootView = null;
    /**
     * The M loc client.
     */
// 定位相关
    LocationClient mLocClient;
    /**
     * The My listener.
     */
   //public MyLocationListenner myListener = new MyLocationListenner();

    private MyFragmentPagerAdapter fragmentAdapter;

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
        fragmentAdapter=new MyFragmentPagerAdapter(getFragmentManager(),this);
        mNearByTpgView.setAdapter(fragmentAdapter);
        mNearByTpgView.setTabGravity(TabLayout.GRAVITY_FILL);
        mNearByTpgView.setTabMode(TabLayout.MODE_FIXED);
        mNearByTpgView.setTabIndicatorHeight(2);
        mNearByTpgView.setOnPageChangedListener(new TpgView.OnPageChangedListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                fragmentAdapter.getItem(position).reloadDate(mCurrentXiaoqu.getSelectedItem().toString());
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
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            Log.e("REFRESH", " shuaxinshuju");
           // initData();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        mLocClient.stop();
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

                break;
            case R.id.near_by_search_imageview:
                UIUtils.nextPage(mContext, SearchActivity.class);
                break;
        }
    }

    @OnItemSelected(R.id.current_xiaoqu)
    void onItemSelected() {
        Log.e(TAG, "onItemSelected: " + mCurrentXiaoqu.getSelectedItem().toString());
        if (!mCurrentXiaoqu.getSelectedItem().toString().equals("请选择")) {
            if(fragmentAdapter!=null)
                fragmentAdapter.reloadDataForCurrentPager(mCurrentXiaoqu.getSelectedItem().toString());
        }
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

    public NearBySecondHandFrangment getSecondFrangment() {
        return NearBySecondHandFrangment.newInstance(mCurrentXiaoqu.getSelectedItem().toString());
    }

    public NearByQiuGouFragment getQiugouFragment() {
       return NearByQiuGouFragment.newInstance(mCurrentXiaoqu.getSelectedItem().toString());
    }
}
