package com.tt.czj.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.app.MyApp;
import com.tt.czj.di.component.AppComponent;
import com.tt.czj.di.component.DaggerLeaveMessageComponent;
import com.tt.czj.di.modules.LeaveMessageActivityModule;
import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.presenter.LeaveMessagePresenter;
import com.tt.czj.mvp.views.LeaveMessageActivityView;
import com.tt.czj.ui.activity.base.BaseActivity;
import com.tt.czj.utils.ToastUtil;
import com.tt.czj.utils.ToolsUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/09/0009.
 */
public class LeaveMessageActivity extends BaseActivity implements LeaveMessageActivityView{
    /**
     * The M leave message text view.
     */
    @BindView(R.id.leave_message_textView)
    TextView mLeaveMessageTextView;
    /**
     * The M leave message back.
     */
    @BindView(R.id.leave_message_back)
    ImageView mLeaveMessageBack;
    /**
     * The M leave message top.
     */
    @BindView(R.id.leave_message_top)
    RelativeLayout mLeaveMessageTop;
    /**
     * The M leave message edit.
     */
    @BindView(R.id.leave_message_edit)
    EditText mLeaveMessageEdit;
    /**
     * The Leave message presenter.
     */
    @Inject
    LeaveMessagePresenter leaveMessagePresenter;

    /**
     * The M goods.
     */
    Goods mGoods;
    /**
     * The M users.
     */
    User mUsers;

    @Override
    public int getContentViewId() {
        return R.layout.leave_message;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        setupComponent(((MyApp) getApplicationContext()).getAppComponent());
    }

    @Override
    public void initData() {

    }

    /**
     * Sets component.
     *
     * @param appComponent the app component
     */
    protected void setupComponent(AppComponent appComponent) {
        DaggerLeaveMessageComponent
                .builder()
                .appComponent(appComponent)
                .leaveMessageActivityModule(new LeaveMessageActivityModule(this))
                .build()
                .inject(this);
    }
    /**
     * On click.
     *
     * @param v the v
     */
    @OnClick({R.id.leave_message_textView, R.id.leave_message_back, R.id.leave_message_top, R.id.leave_message_edit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leave_message_textView:
                saveMessage(mLeaveMessageEdit.getText().toString());
                break;
            case R.id.leave_message_back:
                finish();
                break;
            case R.id.leave_message_top:
                break;
            case R.id.leave_message_edit:
                break;
        }
    }

    private void saveMessage(String message){
        if(ToolsUtils.isNullOrEmpty(message)){
            ToastUtil.showToast(this,"未填写评论");
        }
        else {
            mGoods = (Goods) getIntent().getSerializableExtra("Goods");
            mUsers = (User) getIntent().getSerializableExtra("User");
            leaveMessagePresenter.leaveMessage(this,mUsers.getObjectId(),message,mGoods.getObjectId());
        }
    }

    @Override
    public void leaveMessageSuccess() {
       /* Bundle bundle = new Bundle();
        bundle.putSerializable("Goods",mGoods);
        bundle.putSerializable("User",mUsers);
        UIUtils.nextPage(this,GoodsDetailActivity.class,bundle);*/
        finish();
    }

    @Override
    public void leaveMessageError(String str) {
        ToastUtil.showToast(this,str);
    }

}
