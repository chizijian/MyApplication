package com.tt.czj.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.app.MyApp;
import com.tt.czj.di.component.AppComponent;
import com.tt.czj.di.component.DaggerSearchGoodsComponent;
import com.tt.czj.di.modules.SearchGoodsModule;
import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.presenter.SearchGoodsPresenter;
import com.tt.czj.mvp.views.SearchGoodsView;
import com.tt.czj.ui.activity.base.BaseActivity;
import com.tt.czj.ui.adapter.ExpandableListViewAdapter;
import com.tt.czj.utils.GlobalDefineValues;
import com.tt.czj.widget.CustomExpandableListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by czj on 2016/5/19 0019.
 */
public class SearchResultActivity extends BaseActivity implements SearchGoodsView {
    /**
     * The constant TAG.
     */
    public static final String TAG = "SearchResultActivity";
    /**
     * The Presenter.
     */
    @Inject
    SearchGoodsPresenter presenter;
    /**
     * The M activity search result back.
     */
    @BindView(R.id.activity_search_result_back)
    ImageView mActivitySearchResultBack;
    /**
     * The Activity search result top.
     */
    @BindView(R.id.activity_search_result_top)
    TextView activitySearchResultTop;
    /**
     * The List view.
     */
    @BindView(R.id.activity_search_result_goods_list)
    CustomExpandableListView listView;
    private String key;
    private String type;
    private ExpandableListViewAdapter adapter;
    private List<Goods> goodses;

    @Override
    public int getContentViewId() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setupComponent(((MyApp) getApplicationContext()).getAppComponent());
    }

    @Override
    public void initData() {
        key = getIntent().getStringExtra(GlobalDefineValues.Search_Key_Words);
        type = getIntent().getStringExtra(GlobalDefineValues.REQUEST_SEARCH_RESULT);
        activitySearchResultTop.setText(key);
        if (type.equals(GlobalDefineValues.REQUEST_SEARCH_RESULT_KEY_WORDS)) {
            presenter.queryGoodsByKeyWords(this,key);
        } else if (type.equals(GlobalDefineValues.REQUEST_SEARCH_RESULT_SECOND_KIND)) {
            presenter.queryGoodsBySecondKind(this,key);
        }else if (type.equals(GlobalDefineValues.REQUEST_SEARCH_RESULT_KIND)){
            presenter.queryGoodsBYKind(this,key);
        }
    }

    @Override
    public void showKeyWordsResult(List<Goods> goodsList) {
        this.goodses = goodsList;
        presenter.parseGoodsUser(this, goodses);
    }

    @Override
    public void onLoadGoodsError(String str) {

    }

    @Override
    public void parseUser(List<User> users) {
        adapter = new ExpandableListViewAdapter(this, goodses, users);
        listView.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            listView.expandGroup(i);
        }
    }

    @Override
    public void showSecondKindResult(List<Goods> goodses) {
        this.goodses = goodses;
        presenter.parseGoodsUser(this, goodses);
    }

    /**
     * Sets component.
     *
     * @param appComponent the app component
     */
    protected void setupComponent(AppComponent appComponent) {
        DaggerSearchGoodsComponent
                .builder()
                .appComponent(appComponent)
                .searchGoodsModule(new SearchGoodsModule(this))
                .build()
                .inject(this);
    }

    /**
     * On click.
     *
     * @param view the view
     */
    @OnClick({R.id.activity_search_result_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_search_result_back:
                finish();
                break;
        }
    }
}
