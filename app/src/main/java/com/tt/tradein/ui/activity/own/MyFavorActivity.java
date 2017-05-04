package com.tt.tradein.ui.activity.own;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tt.tradein.R;
import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.User;
import com.tt.tradein.ui.activity.GoodsDetailActivity;
import com.tt.tradein.ui.activity.base.BaseActivity;
import com.tt.tradein.ui.adapter.ExpandableListViewAdapter;
import com.tt.tradein.widget.CustomExpandableListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

/**
 * The type My favor activity.
 */
public class MyFavorActivity extends BaseActivity {

    /**
     * The M home goods list.
     */
    @BindView(R.id.my_favor_goods_list)
    CustomExpandableListView mHomeGoodsList;
    /**
     * The My favor back.
     */
    @BindView(R.id.my_favor_back)
    ImageView myFavorBack;
    /**
     * The Goods detail top.
     */
    @BindView(R.id.favor_goods_detail_top)
    RelativeLayout goodsDetailTop;
    private ExpandableListViewAdapter adapter;
    private List<Goods> goodses = new ArrayList<>();
    /**
     * The Users.
     */
    List<User> users = new ArrayList<>();
    /**
     * The User id list.
     */
    List<String> userIdList = new ArrayList<String>();

    private Context mContext;

    @Override
    public int getContentViewId() {
        return R.layout.activity_my_favor;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mContext=this;

        mHomeGoodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(this,""+goodses.get(position).getPrice(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyFavorActivity.this, GoodsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Goods", goodses.get(position));
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
    public void loadGoodsInfor(final Context context) {
        BmobQuery<Goods> query = new BmobQuery<>();
        User user = BmobUser.getCurrentUser(context, User.class);
        query.addWhereRelatedTo("likes", new BmobPointer(user));
        query.include("user");
        query.findObjects(context, new FindListener<Goods>() {
            @Override
            public void onSuccess(final List<Goods> list) {
                goodses.addAll(list);
                for (Goods g : list
                        ) {
                    users.add(g.getUser());
                }
                adapter = new ExpandableListViewAdapter(MyFavorActivity.this, list, users);
                mHomeGoodsList.setAdapter(adapter);
                for (int i = 0; i < adapter.getGroupCount(); i++) {
                    mHomeGoodsList.expandGroup(i);
                }
                mHomeGoodsList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        Toast.makeText(mContext, "" +list.get(groupPosition).getPrice(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Goods", list.get(groupPosition));
                        bundle.putSerializable("User", users.get(groupPosition));
                        intent.putExtras(bundle);
                        startActivity(intent);
                        return true;
                    }
                });
                mHomeGoodsList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        Toast.makeText(mContext, "" + list.get(groupPosition).getPrice(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Goods", list.get(groupPosition));
                        bundle.putSerializable("User", users.get(groupPosition));
                        intent.putExtras(bundle);
                        startActivity(intent);
                        return true;
                    }
                });
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    /**
     * On click.
     *
     * @param view the view
     */
    @OnClick({R.id.my_favor_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_favor_back:
                finish();
                break;
        }
    }

    private void parseGoodsUser(Context context, final List<Goods> goods) {
        final List<User> users = new ArrayList<>();
        for (Goods good : goods
                ) {
            BmobQuery<User> query = new BmobQuery<User>();
            query.addWhereEqualTo("objectId", good.getUserid());
            //Log.e(TAG, "parseGoodsUser: "+goods.get(i).getUserid());
            query.findObjects(context, new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {
                    users.addAll(list);
                    if (users.size() == goods.size()) {
                        parseUser(users);
                    }
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        }
    }

    /**
     * Parse user.
     *
     * @param users the users
     */
    public void parseUser(final List<User> users) {
        Log.e(TAG + ":User", users.size() + "");
        Log.e(TAG + ":Goods", goodses.size() + "");
        adapter = new ExpandableListViewAdapter(mContext, goodses, users);
        mHomeGoodsList.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            mHomeGoodsList.expandGroup(i);
        }
        mHomeGoodsList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(mContext, "" + goodses.get(groupPosition).getPrice(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Goods", goodses.get(groupPosition));
                bundle.putSerializable("User", users.get(groupPosition));
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
        });
        mHomeGoodsList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext, "" + goodses.get(groupPosition).getPrice(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Goods", goodses.get(groupPosition));
                bundle.putSerializable("User", users.get(groupPosition));
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
        });
    }

    /**
     * On load goods infor success.
     *
     * @param goods the goods
     */
    public void onLoadGoodsInforSuccess(List<Goods> goods) {
        this.goodses = goods;
        parseGoodsUser(mContext, goods);
    }
}


