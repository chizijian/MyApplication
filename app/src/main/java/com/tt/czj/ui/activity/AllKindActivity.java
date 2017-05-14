package com.tt.czj.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.app.MyApp;
import com.tt.czj.di.component.AppComponent;
import com.tt.czj.di.component.DaggerAllKindComponent;
import com.tt.czj.di.modules.AllKindModule;
import com.tt.czj.mvp.models.Kind;
import com.tt.czj.mvp.models.SecondKind;
import com.tt.czj.mvp.presenter.KindPresenter;
import com.tt.czj.mvp.views.AllKindViews;
import com.tt.czj.ui.activity.base.BaseActivity;
import com.tt.czj.ui.adapter.ListViewAdapter;
import com.tt.czj.ui.adapter.SecondKindListAdapter;
import com.tt.czj.utils.GlobalDefineValues;
import com.tt.czj.utils.ToastUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by czj on 2016/5/18 0018.
 */
public class AllKindActivity extends BaseActivity implements AllKindViews {
    /**
     * The constant TAG.
     */
    public static final String TAG = "AllKindActivity";
    /**
     * The Kind list view.
     */
    @BindView(R.id.kind_listView)
    ListView kindListView;
    /**
     * The Second list view.
     */
    @BindView(R.id.second_listView)
    ListView secondListView;
    /**
     * The Presenter.
     */
    @Inject
    KindPresenter presenter;
    /**
     * The All kind back.
     */
    @BindView(R.id.all_kind_back)
    ImageView allKindBack;
    /**
     * The All kind complete.
     */
    @BindView(R.id.all_kind_complete)
    TextView allKindComplete;
    private ListViewAdapter kindAdapter;
    private SecondKindListAdapter secondKindListAdapter;
    private List<Kind> mKinds;
    private int kindOldClickIndex = 0;
    private int kindCurrentClickIndex = 0;
    private String kindString, secondString;

    @Override
    public int getContentViewId() {
        return R.layout.activity_all_kind;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setupComponent(((MyApp) getApplicationContext()).getAppComponent());
    }

    /**
     * Sets component.
     *
     * @param appComponent the app component
     */
    protected void setupComponent(AppComponent appComponent) {
        DaggerAllKindComponent
                .builder()
                .appComponent(appComponent)
                .allKindModule(new AllKindModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initData() {
        presenter.loadKind(this);
    }

    @Override
    public void loadFailed(String str) {

    }

    @Override
    public void showKind(final List<Kind> kinds) {
        kindAdapter = new ListViewAdapter(this, kinds);
        kindListView.setAdapter(kindAdapter);
        kindListView.post(new Runnable() {
            @Override
            public void run() {
                kindListView.getChildAt(kindCurrentClickIndex).setBackgroundColor(getResources().getColor(R.color.white));
                kindString = kinds.get(0).getKind();
                presenter.loadSecondKind(AllKindActivity.this, kinds.get(0));
            }
        });
        kindListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                kindCurrentClickIndex = position;
                if (kindCurrentClickIndex != kindOldClickIndex) {
                    parent.getChildAt(kindCurrentClickIndex).setBackgroundColor(getResources().getColor(R.color.white));
                    parent.getChildAt(kindOldClickIndex).setBackgroundColor(getResources().getColor(R.color.colorBg));
                    kindOldClickIndex = position;
                } else {
                    parent.getChildAt(kindCurrentClickIndex).setBackgroundColor(getResources().getColor(R.color.white));
                }
                kindString = kinds.get(position).getKind();
                presenter.loadSecondKind(AllKindActivity.this, kinds.get(position));
            }
        });
    }

    @Override
    public void showSecondKind(final List<SecondKind> secondKinds) {
        secondKindListAdapter = new SecondKindListAdapter(this, secondKinds);
        secondListView.setAdapter(secondKindListAdapter);
        secondListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                secondString = secondKinds.get(position).getKind();
                view.setBackgroundColor(getResources().getColor(R.color.topBarBgColor));
            }
        });
    }

    /**
     * On click.
     *
     * @param view the view
     */
    @OnClick({R.id.all_kind_complete, R.id.all_kind_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.all_kind_complete:
                if (kindString != null && secondString != null) {
                    Intent intent = new Intent(AllKindActivity.this, PublishGoodsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(GlobalDefineValues.GOODS_KIND, kindString);
                    bundle.putString(GlobalDefineValues.GOODS_SECOND_KIND, secondString);
                    intent.putExtras(bundle);
                    setResult(GlobalDefineValues.CHOOSE_KIND, intent);
                    finish();
                }else if (secondString == null){
                    ToastUtil.showToast(this,"请选择二级分类");
                }else {
                    ToastUtil.showToast(this,"请选择一级分类");
                }
                break;
            case R.id.all_kind_back:
                finish();
                break;
        }
    }
}
