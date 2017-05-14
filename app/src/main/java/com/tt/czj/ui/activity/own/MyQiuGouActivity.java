package com.tt.czj.ui.activity.own;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tt.czj.R;
import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;
import com.tt.czj.ui.activity.GoodsDetailActivity;
import com.tt.czj.ui.activity.base.BaseActivity;
import com.tt.czj.ui.adapter.ExpandableListViewAdapter;
import com.tt.czj.widget.CustomExpandableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.FindListener;

//import static com.baidu.location.h.i.R;

/**
 * The type My qiu gou activity.
 */
public class MyQiuGouActivity extends BaseActivity {

    /**
     * The M home goods list.
     */
    @BindView(R.id.my_qiugou_goods_list)
    CustomExpandableListView mHomeGoodsList;
    /**
     * The Myqiugou back.
     */
    @BindView(R.id.my_qiugou_back)
    ImageView myqiugouBack;
    /**
     * The Goods detail top.
     */
    @BindView(R.id.qiugou_goods_detail_top)
    RelativeLayout goodsDetailTop;
    private ExpandableListViewAdapter adapter;
    private List<Goods> goodses;

    @Override
    public int getContentViewId() {
        return R.layout.activity_my_qiu_gou;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        mHomeGoodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(this,""+goodses.get(position).getPrice(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyQiuGouActivity.this,GoodsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Goods",goodses.get(position));
                // bundle.putSerializable("User",users.get(groupPosition));
                intent.putExtras(bundle);
                startActivity(intent);
                //return true;
            }
        });
    }

    @Override
    public void initData() {
        loadGoodsInfor(this);
    }

    /**
     * Load goods infor.
     *
     * @param context the context
     */
    public void loadGoodsInfor(Context context) {
        BmobQuery<Goods> query = new BmobQuery<Goods>();
        final User user = BmobUser.getCurrentUser(this, User.class);
        ;
        final List<User> users = new ArrayList<>();
        query.addWhereEqualTo("userid", user.getObjectId());
        query.addWhereEqualTo("qiugou",true);
        query.findObjects(context, new FindListener<Goods>() {
            @Override
            public void onSuccess(List<Goods> list) {
                for (int i = 0; i < list.size(); i++) {
                    users.add(user);
                }
                adapter = new ExpandableListViewAdapter(MyQiuGouActivity.this, list, users);
                mHomeGoodsList.setAdapter(adapter);
                for (int i = 0; i < adapter.getGroupCount(); i++) {
                    mHomeGoodsList.expandGroup(i);
                }
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(MyQiuGouActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * On click.
     *
     * @param view the view
     */
    @OnClick({R.id.my_qiugou_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_qiugou_back:
                finish();
                break;
        }
    }


}
