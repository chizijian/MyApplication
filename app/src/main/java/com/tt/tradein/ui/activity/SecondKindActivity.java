package com.tt.tradein.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
import com.tt.tradein.ui.adapter.SecondKindListAdapter;
import com.tt.tradein.utils.GlobalDefineValues;
import com.tt.tradein.utils.UIUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by czj on 2016/5/19 0019.
 */
public class SecondKindActivity extends BaseActivity implements AllKindViews{
    /**
     * The constant TAG.
     */
    public static final String TAG = "SecondKindActivity";
    /**
     * The List view.
     */
    @BindView(R.id.activity_second_kind_listview)
    ListView listView;
    /**
     * The Back.
     */
    @BindView(R.id.activity_second_kind_back)
    ImageView back;
    /**
     * The Text view.
     */
    @BindView(R.id.activity_second_kind_top)
    TextView textView;
    private Kind kind;
    private SecondKindListAdapter adapter;
    /**
     * The Presenter.
     */
    @Inject
    KindPresenter presenter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_second_kind;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setupComponent(((MyApp) getApplicationContext()).getAppComponent());
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        kind = (Kind) intent.getSerializableExtra(GlobalDefineValues.CHOOSE_KIND_STR);
        textView.setText(kind.getKind());
        presenter.loadSecondKind(this,kind);
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
    public void showKind(List<Kind> kinds) {

    }

    @Override
    public void showSecondKind(final List<SecondKind> secondKinds) {
        adapter = new SecondKindListAdapter(this,secondKinds);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString(GlobalDefineValues.REQUEST_SEARCH_RESULT,GlobalDefineValues.REQUEST_SEARCH_RESULT_SECOND_KIND);
                bundle.putString(GlobalDefineValues.Search_Key_Words,secondKinds.get(position).getKind());
                UIUtils.nextPage(SecondKindActivity.this,SearchResultActivity.class,bundle);
            }
        });
    }

    @Override
    public void loadFailed(String str) {

    }

    /**
     * On click.
     *
     * @param v the v
     */
    @OnClick({R.id.activity_second_kind_back})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.activity_second_kind_back:
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
