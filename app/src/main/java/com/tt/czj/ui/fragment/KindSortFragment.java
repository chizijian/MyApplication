package com.tt.czj.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.app.MyApp;
import com.tt.czj.di.component.AppComponent;
import com.tt.czj.di.component.DaggerMainActivityComponent;
import com.tt.czj.di.modules.MainActivityModule;
import com.tt.czj.mvp.models.KindSort;
import com.tt.czj.mvp.presenter.KindSortPresenter;
import com.tt.czj.mvp.views.KindSortView;
import com.tt.czj.ui.activity.KindActivity;
import com.tt.czj.ui.activity.MainActivity;
import com.tt.czj.ui.activity.SearchResultActivity;
import com.tt.czj.ui.adapter.HomeAdapter;
import com.tt.czj.utils.GlobalDefineValues;
import com.tt.czj.utils.UIUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by czj on 2016/4/6 0006.
 */
public class KindSortFragment extends Fragment implements KindSortView{

    /**
     * The constant TAG.
     */
    public static final String TAG = "KindSortFragment";

    /**
     * The Unbinder.
     */
    Unbinder unbinder;

    /**
     * The Kind sort list.
     */
    @BindView(R.id.kind_sort_list)
    RecyclerView kindSortList;
    /**
     * The M phone ll.
     */
    @BindView(R.id.phone_ll)
    LinearLayout mPhoneLl;
    /**
     * The M tablet ll.
     */
    @BindView(R.id.tablet_ll)
    LinearLayout mTabletLl;
    /**
     * The M computer ll.
     */
    @BindView(R.id.computer_ll)
    LinearLayout mComputerLl;
    /**
     * The M camera ll.
     */
    @BindView(R.id.camera_ll)
    LinearLayout mCameraLl;
    /**
     * The M bicycle ll.
     */
    @BindView(R.id.bicycle_ll)
    LinearLayout mBicycleLl;
    /**
     * The M music ll.
     */
    @BindView(R.id.music_ll)
    LinearLayout mMusicLl;
    /**
     * The M book ll.
     */
    @BindView(R.id.book_ll)
    LinearLayout mBookLl;
    /**
     * The M stationary ll.
     */
    @BindView(R.id.stationary_ll)
    LinearLayout mStationaryLl;
    /**
     * The M see all kind.
     */
    @BindView(R.id.see_all_kind)
    TextView mSeeAllKind;
    private Context mContext;
    private View mRootView = null;
    /**
     * The Kind sort presenter.
     */
    @Inject
    KindSortPresenter kindSortPresenter;
    private HomeAdapter myAdspter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.kind_sort_fragment, container, false);
        }
        unbinder=ButterKnife.bind(this, mRootView);
        setupComponent(((MyApp) mContext.getApplicationContext()).getAppComponent());
        initData();
        return mRootView;
    }

    /**
     * Init data.
     */
    public void initData() {
        final Toolbar toolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
        ((MainActivity) mContext).setSupportActionBar(toolbar);
        ((MainActivity) mContext).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setTitle("");
        CollapsingToolbarLayout toolbar_layout = (CollapsingToolbarLayout) mRootView.findViewById(R.id.toolbar_layout);
        toolbar_layout.setTitleEnabled(false);
        toolbar_layout.setTitle("搜索");
        kindSortPresenter.loadKindSortData(mContext);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * On click.
     *
     * @param view the view
     */
    @OnClick({R.id.phone_ll, R.id.tablet_ll, R.id.computer_ll, R.id.camera_ll, R.id.bicycle_ll, R.id.music_ll, R.id.book_ll, R.id.stationary_ll, R.id.see_all_kind})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.phone_ll:
                bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_KIND);
                bundle.putString(GlobalDefineValues.Search_Key_Words,"手机");
                UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                break;
            case R.id.tablet_ll:
                bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_KIND);
                bundle.putString(GlobalDefineValues.Search_Key_Words,"平板");
                UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                break;
            case R.id.computer_ll:
                bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_KIND);
                bundle.putString(GlobalDefineValues.Search_Key_Words,"电脑");
                UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                break;
            case R.id.camera_ll:
                bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_KIND);
                bundle.putString(GlobalDefineValues.Search_Key_Words,"数码");
                UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                break;
            case R.id.bicycle_ll:
                bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_KIND);
                bundle.putString(GlobalDefineValues.Search_Key_Words,"代步");
                UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                break;
            case R.id.music_ll:
                bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_KIND);
                bundle.putString(GlobalDefineValues.Search_Key_Words,"玩具");
                UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                break;
            case R.id.book_ll:
                bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_KIND);
                bundle.putString(GlobalDefineValues.Search_Key_Words,"书籍");
                UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                break;
            case R.id.stationary_ll:
                bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_KIND);
                bundle.putString(GlobalDefineValues.Search_Key_Words,"办公");
                UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                break;
            case R.id.see_all_kind:
                UIUtils.nextPage(mContext, KindActivity.class);
                break;
        }
    }

    @Override
    public void presenKindSorts(List<KindSort> kindSorts) {
        myAdspter = new HomeAdapter(mContext,kindSorts);
        kindSortList.setLayoutManager(new LinearLayoutManager(mContext));
        kindSortList.setAdapter(myAdspter);
        myAdspter.setHomeAdapterOnClickListener(new HomeAdapter.HomeAdapterOnClickListener() {
            @Override
            public void onClick(int item, int pos) {
                Bundle bundle = new Bundle();
                switch (item){
                    case 0:
                        if (pos == 1){
                            bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_SECOND_KIND);
                            bundle.putString(GlobalDefineValues.Search_Key_Words,"小米");
                            UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                        }else if (pos==2){
                            bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_SECOND_KIND);
                            bundle.putString(GlobalDefineValues.Search_Key_Words,"三星");
                            UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                        }else if (pos == 3){
                            bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_SECOND_KIND);
                            bundle.putString(GlobalDefineValues.Search_Key_Words,"华为");
                            UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                        }
                        break;
                    case 1:
                        if (pos == 1){
                            bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_SECOND_KIND);
                            bundle.putString(GlobalDefineValues.Search_Key_Words,"华硕");
                            UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                        }else if (pos==2){
                            bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_SECOND_KIND);
                            bundle.putString(GlobalDefineValues.Search_Key_Words,"联想");
                            UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                        }else if (pos == 3){
                            bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_SECOND_KIND);
                            bundle.putString(GlobalDefineValues.Search_Key_Words,"三星");
                            UIUtils.nextPage(mContext, SearchResultActivity.class,bundle);
                        }
                        break;
                    case 2:
                        if (pos == 1){

                        }else if (pos==2){

                        }else if (pos == 3){

                        }
                        break;
                }
            }
        });
    }

    @Override
    public void presenterKindsError(String string) {

    }
}
