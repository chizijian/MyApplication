package com.tt.tradein.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.tt.tradein.R;
import com.tt.tradein.app.MyApp;
import com.tt.tradein.di.component.AppComponent;
import com.tt.tradein.di.component.DaggerMainActivityComponent;
import com.tt.tradein.di.modules.MainActivityModule;
import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.User;
import com.tt.tradein.mvp.presenter.NearByPresenter;
import com.tt.tradein.mvp.views.NearByView;
import com.tt.tradein.ui.activity.GoodsDetailActivity;
import com.tt.tradein.ui.activity.SearchActivity;
import com.tt.tradein.ui.adapter.ExpandableListViewAdapter;
import com.tt.tradein.ui.adapter.MyFragmentPagerAdapter;
import com.tt.tradein.utils.UIUtils;
import com.tt.tradein.widget.CustomExpandableListView;
import com.yhy.tpg.widget.TpgView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.Unbinder;

/**
 * Created by czj on 2016/4/6 0006.
 */
public class NearByFragment extends Fragment implements NearByView {
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
  /*  @BindView(R.id.nearby_tablelayout)
    android.support.design.widget.TabLayout mNearbyTablelayout;
    @BindView(R.id.nearby_viewPager)
    ViewPager mNearbyViewPager;*/


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
    public MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    //private String mCurrentPrince = "武汉";
    private String mCurrentPrince = "南湖";
    private ExpandableListViewAdapter adapter;

    private MyFragmentPagerAdapter fragmentAdapter;
    private List<Goods> goodses;

    private String mCurrentXiqoqu;
    /**
     * The Presenter.
     */
    @Inject
    NearByPresenter presenter;
    private View view;

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
        setupComponent(((MyApp) mContext.getApplicationContext()).getAppComponent());
       // fragmentAdapter = new MyFragmentPagerAdapter(this.getFragmentManager(), mContext, this);
     /*   mNearbyViewPager.setAdapter(fragmentAdapter);
        mNearbyTablelayout.setupWithViewPager(mNearbyViewPager);
        mNearbyTablelayout.getTabAt(0).select();*/
        mNearByGoodsList.setFocusable(false);
        initBaiduLocation();
    }

    /**
     * Sets component.
     *
     * @param appComponent the app component
     */
    protected void setupComponent(AppComponent appComponent) {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
    }

    /**
     * Init baidu location.
     */
    public void initBaiduLocation() {
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
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            Log.e("REFRESH", " shuaxinshuju");
            presenter.loadGoodsInfor(mContext, mCurrentPrince, false);
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
    @OnClick({R.id.near_by_item_mode, R.id.near_by_search_imageview, R.id.nearby_tablelayout, R.id.nearby_viewPager, R.id.NearBy_TpgView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.near_by_item_mode:

                break;
            case R.id.near_by_search_imageview:
                UIUtils.nextPage(mContext, SearchActivity.class);
                break;
            case R.id.nearby_tablelayout:
                break;
            case R.id.nearby_viewPager:
                break;
            case R.id.NearBy_TpgView:
                break;
        }
    }

    @OnItemSelected(R.id.current_xiaoqu)
    void onItemSelected(int position) {
        Log.e(TAG, "onItemSelected: " + mCurrentXiaoqu.getSelectedItem().toString());
        mCurrentXiqoqu = mCurrentXiaoqu.getSelectedItem().toString();
       /* if (mNearbyTablelayout != null && fragmentAdapter != null)
            if (mNearbyTablelayout.getSelectedTabPosition() == 0) {
                NearBySecondHandFrangment fragment = (NearBySecondHandFrangment) fragmentAdapter.getItem(mNearbyTablelayout.getSelectedTabPosition());
                if (fragment != null)
                    fragment.Refresh(mCurrentXiqoqu, mContext);
            }*/
    }

    @Override
    public void parseUser(final List<User> users, final List<Goods> goods) {
        adapter = new ExpandableListViewAdapter(mContext, goods, users);
        mNearByGoodsList.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            mNearByGoodsList.expandGroup(i);
        }
        mNearByGoodsList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(mContext, "" + goods.get(groupPosition).getPrice(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Goods", goods.get(groupPosition));
                bundle.putSerializable("User", users.get(groupPosition));
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
        });
        mNearByGoodsList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext, "" + goods.get(groupPosition).getPrice(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Goods", goods.get(groupPosition));
                bundle.putSerializable("User", users.get(groupPosition));
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public void onLoadGoodsInforSuccess(List<Goods> goods) {
        this.goodses = goods;
        presenter.parseGoodsUser(mContext, goods);
    }

    @Override
    public void onLoadGoodsError(String str) {

    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            StringBuffer sb = new StringBuffer(256);

            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果

            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            mCurrentAddress.setText(location.getAddrStr());
            mCurrentAddress.postInvalidate();
            //mCurrentPrince = location.getCity();
            mCurrentPrince = mCurrentXiaoqu.getSelectedItem().toString();
            presenter.loadGoodsInfor(mContext, mCurrentPrince, false);
        }

        /**
         * On receive poi.
         *
         * @param poiLocation the poi location
         */
        public void onReceivePoi(BDLocation poiLocation) {

        }
    }

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
