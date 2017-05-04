package com.tt.tradein.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.tt.tradein.R;
import com.tt.tradein.app.MyApp;
import com.tt.tradein.di.component.AppComponent;
import com.tt.tradein.di.component.DaggerAllKindComponent;
import com.tt.tradein.di.modules.AllKindModule;
import com.tt.tradein.mvp.models.Kind;
import com.tt.tradein.mvp.models.SecondKind;
import com.tt.tradein.mvp.presenter.KindPresenter;
import com.tt.tradein.mvp.views.AllKindViews;
import com.tt.tradein.ui.activity.base.BaseActivity;
import com.tt.tradein.ui.adapter.KindActivityListAdapter;
import com.tt.tradein.utils.GlobalDefineValues;
import com.tt.tradein.utils.UIUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by czj on 2016/5/19 0019.
 */
public class KindActivity extends BaseActivity implements AllKindViews{
    /**
     * The constant TAG.
     */
    public static final String TAG = "KindActivity";
    /**
     * The List view.
     */
    @BindView(R.id.activity_kind_listview)
    ListView listView;
    /**
     * The Back.
     */
    @BindView(R.id.activity_kind_back)
    ImageView back;
    /**
     * The Presenter.
     */
    @Inject
    KindPresenter presenter;
    private KindActivityListAdapter adapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_kind;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setupComponent(((MyApp) getApplicationContext()).getAppComponent());
    }

    @Override
    public void initData() {
        presenter.loadKind(this);
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
    public void showKind(final List<Kind> kinds) {
        adapter = new KindActivityListAdapter(this,kinds);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(GlobalDefineValues.CHOOSE_KIND_STR,kinds.get(position));
                UIUtils.nextPage(KindActivity.this,SecondKindActivity.class,bundle);
            }
        });
    }

    @Override
    public void showSecondKind(List<SecondKind> secondKinds) {

    }

    @Override
    public void loadFailed(String str) {

    }

    /**
     * On click.
     *
     * @param v the v
     */
    @OnClick({R.id.activity_kind_back})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.activity_kind_back:
                finish();
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
