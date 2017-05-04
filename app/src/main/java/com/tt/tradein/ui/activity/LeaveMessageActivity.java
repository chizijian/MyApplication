package com.tt.tradein.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tt.tradein.R;
import com.tt.tradein.app.MyApp;
import com.tt.tradein.di.component.AppComponent;
import com.tt.tradein.di.component.DaggerLeaveMessageComponent;
import com.tt.tradein.di.modules.LeaveMessageActivityModule;
import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.Message;
import com.tt.tradein.mvp.models.User;
import com.tt.tradein.mvp.presenter.LeaveMessagePresenter;
import com.tt.tradein.mvp.presenter.LeaveMessagePresenterlmpl;
import com.tt.tradein.mvp.views.LeaveMessageActivityView;
import com.tt.tradein.ui.activity.base.BaseActivity;

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
    LeaveMessagePresenter leaveMessagePresenter=new LeaveMessagePresenterlmpl(this);

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
     * On click.
     *
     * @param v the v
     */
    @OnClick({R.id.leave_message_textView, R.id.leave_message_back, R.id.leave_message_top, R.id.leave_message_edit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leave_message_textView:
                saveMessage(mLeaveMessageEdit.getText().toString());
                Intent intent = new Intent(this,GoodsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Goods",mGoods);
                bundle.putSerializable("User",mUsers);
                intent.putExtras(bundle);
                startActivity(intent);
                fileList();
                break;
            case R.id.leave_message_back:
                break;
            case R.id.leave_message_top:
                break;
            case R.id.leave_message_edit:
                break;
        }
    }

    private void saveMessage(String message){
        if(message==null){
            Toast.makeText(this,"未填写评论",Toast.LENGTH_LONG);
            return;
        }
        else {
            Message m=new Message();
            mGoods = (Goods) getIntent().getSerializableExtra("Goods");
            mUsers = (User) getIntent().getSerializableExtra("User");
            leaveMessagePresenter.leaveMessage(this,mUsers.getObjectId(),message,mGoods.getObjectId());
        }
    }

    @Override
    public void leaveMessageSuccess() {

    }

    @Override
    public void leaveMessageError(String str) {

    }

    /**
     * Sets component.
     *
     * @param appComponent the app component
     */
    protected void setupComponent(AppComponent appComponent) {
        DaggerLeaveMessageComponent.builder()
                .appComponent(appComponent)
                .leaveMessageActivityModule(new LeaveMessageActivityModule(this))
                .build()
                .inject(this);
    }
}
