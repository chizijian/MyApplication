package com.tt.tradein.ui.activity.own;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tt.tradein.R;
import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.Order;
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
 * Created by Administrator on 2017/4/13/0013.
 */
public class MyOrderActivity extends BaseActivity {
    /**
     * The M my order back.
     */
    @BindView(R.id.my_order_back)
    ImageView mMyOrderBack;
    /**
     * The M order goods detail top.
     */
    @BindView(R.id.order_goods_detail_top)
    RelativeLayout mOrderGoodsDetailTop;
    /**
     * The M my order goods list.
     */
    @BindView(R.id.my_order_goods_list)
    CustomExpandableListView mMyOrderGoodsList;

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
        return R.layout.activity_my_order;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        mContext=this;
        mMyOrderGoodsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(this,""+goodses.get(position).getPrice(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
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
        BmobQuery<Order> query=new BmobQuery<>();
        query.addWhereEqualTo("buyer", new BmobPointer(BmobUser.getCurrentUser(mContext,User.class)));
        query.include("goods,seller");
        query.findObjects(mContext, new FindListener<Order>() {
            @Override
            public void onSuccess(List<Order> list) {
                for (Order order:list
                        ) {
                    goodses.add(order.getGoods());
                    users.add(order.getBuyer());
                }
                adapter = new ExpandableListViewAdapter(mContext, goodses, users);
                mMyOrderGoodsList.setAdapter(adapter);
                for (int i = 0; i < adapter.getGroupCount(); i++) {
                    mMyOrderGoodsList.expandGroup(i);
                }
             /*   mMyOrderGoodsList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        Toast.makeText(mContext, "" +goodses.get(groupPosition).getPrice(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Goods", goodses.get(groupPosition));
                        bundle.putSerializable("User", users.get(groupPosition));
                        intent.putExtras(bundle);
                        startActivity(intent);
                        return true;
                    }
                });
                mMyOrderGoodsList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
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
                });*/
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    /**
     * On click.
     *
     * @param v the v
     */
    @OnClick({R.id.my_order_back, R.id.order_goods_detail_top})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_order_back:
                finish();
                break;
            case R.id.order_goods_detail_top:
                break;
        }
    }
}
