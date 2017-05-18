package com.tt.czj.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tt.czj.R;
import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.Message;
import com.tt.czj.mvp.models.User;
import com.tt.czj.ui.activity.BmobPay.BmobPayActivity;
import com.tt.czj.ui.activity.base.BaseActivity;
import com.tt.czj.ui.adapter.ExpandableListViewAdapter;
import com.tt.czj.ui.adapter.MessageAdapter;
import com.tt.czj.ui.adapter.RecyclerViewAdapter;
import com.tt.czj.utils.UIUtils;
import com.tt.czj.widget.CustomExpandableListView;
import com.tt.czj.widget.ImageFlipper;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by czj on 17-2-12.
 */
public class GoodsDetailActivity extends BaseActivity {
    /**
     * The Goods detail back.
     */
    @BindView(R.id.goods_detail_back)
    ImageView goodsDetailBack;
    /**
     * The Goods detail top.
     */
    @BindView(R.id.goods_detail_top)
    RelativeLayout goodsDetailTop;
    /**
     * The Goods detail title.
     */
    @BindView(R.id.goods_detail_title)
    TextView goodsDetailTitle;
    /**
     * The Goods detail description.
     */
    @BindView(R.id.goods_detail_description)
    TextView goodsDetailDescription;
    /**
     * The Goods detail location.
     */
    @BindView(R.id.goods_detail_location)
    TextView goodsDetailLocation;
    /**
     * The Goods detail price.
     */
    @BindView(R.id.goods_detail_price)
    TextView goodsDetailPrice;
    /**
     * The Grid view.
     */
    @BindView(R.id.goods_detail_image_grid)
    RecyclerView gridView;
    /**
     * The Messagelist.
     */
    @BindView(R.id.message_List)
    RecyclerView messagelist;

    @BindView(R.id.qiugou_seller_list)
    CustomExpandableListView mQiugouSellerList;
    /**
     * The Img favor.
     */
    @BindView(R.id.imageView_favor)
    ImageView img_favor;
    /**
     * The Talk.
     */
    @BindView(R.id.imageView_talk)
    ImageView talk;
    /**
     * The Buy.
     */
    @BindView(R.id.textView_Buy)
    TextView buy;

    /**
     * The M goods details dots ll.
     */
    @BindView(R.id.goods_details_dots_ll)
    LinearLayout mGoodsDetailsDotsLl;
    /**
     * The M image fliper.
     */
    @BindView(R.id.goods_detail_imageFliper)
    ImageFlipper mImageFliper;

    private Goods mGoods;//当前商品
    private User mUsers;//发布者

    private final int ADD=0;
    private final int REMOVE=1;

    /**
     * The Is favor.
     */
    boolean isFavor = false;

    /**
     * The Dot list.
     */
    @BindViews({R.id.goods_details_dot_one,R.id.goods_details_dot_two,R.id.goods_details_dot_three,R.id.goods_details_dot_four,R.id.goods_details_dot_five,R.id.goods_details_dot_six,R.id.goods_details_dot_seven,R.id.goods_details_dot_eight,R.id.goods_details_dot_nine})
    List<View> DotList;//圆点指示器

    @Override
    public int getContentViewId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
    }

    @Override
    public void initData() {

        mGoods = (Goods) getIntent().getSerializableExtra("Goods");
        mUsers = (User) getIntent().getSerializableExtra("User");

        if (mGoods.isQiugou()) {
            buy.setText("立即卖出");
        }

        /*收藏按钮*/
        BmobQuery<Goods> query = new BmobQuery<>();
        User user = BmobUser.getCurrentUser(GoodsDetailActivity.this, User.class);
        if (user == null) {
            Toast.makeText(GoodsDetailActivity.this, "请先登录", Toast.LENGTH_LONG).show();
            UIUtils.nextPage(this, LoginActivity.class);
            return;
        }
        query.addWhereRelatedTo("likes", new BmobPointer(user));
        query.findObjects(this, new FindListener<Goods>() {
            @Override
            public void onSuccess(List<Goods> list) {
                img_favor.setImageResource(R.mipmap.icon_unfavor);
                isFavor = false;
                if (list.contains(mGoods)) {
                    img_favor.setImageResource(R.mipmap.icon_favor);
                    isFavor = true;
                }
            }

            @Override
            public void onError(int i, String s) {
                img_favor.setImageResource(R.mipmap.icon_unfavor);
                isFavor = false;
            }
        });
        /*圆点指示器*/
        initDotList();

        /*加载图片*/
        List<ImageView> imageViews = new ArrayList<>();
        for (String path : mGoods.getImages()
                ) {
            ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Glide.with(this)
                    .load(path)
                    .crossFade()
                    .fitCenter()
                    .into(imageView);
            imageViews.add(imageView);
        }
        showBannerData(imageViews);

        goodsDetailTitle.setText(mGoods.getTitle());
        goodsDetailDescription.setText(mGoods.getDescription());
        goodsDetailLocation.setText(mGoods.getLocation());
        goodsDetailPrice.setText(mGoods.getPrice());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        gridView.setLayoutManager(layoutManager);
        gridView.setAdapter(new RecyclerViewAdapter(this, mGoods.getImages()));

        if(!mGoods.isQiugou())
            ShowSecondHandGoodsMessage();//加载二手货物评论
        else{
            ShowQiuGouGoodsMessage();
        }

    }

    /**
     * On click.
     *
     * @param v the v
     */
    @OnClick({R.id.goods_detail_back, R.id.imageView_favor, R.id.imageView_talk, R.id.textView_Buy})
    public void onClick(View v) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("Goods", mGoods);
        bundle.putSerializable("User", mUsers);
        switch (v.getId()) {
            case R.id.goods_detail_back:
                finish();
                break;
            case R.id.imageView_favor:
                add2favor();
                break;
            case R.id.imageView_talk:
                if (buy.getText().toString().equals("立即购买")) {
                    UIUtils.nextPage(this,LeaveMessageActivity.class,bundle);
                   // finish();
                }
                else {
                    bundle.putString("source","qiugou_seller");
                    UIUtils.nextPage(this,PublishGoodsActivity.class,bundle);
                }
                break;
            case R.id.textView_Buy:
                if (buy.getText().toString().equals("立即购买")) {
                    UIUtils.nextPage(this,BmobPayActivity.class,bundle);
                }
                else {
                    bundle.putString("source","qiugou_seller");
                    UIUtils.nextPage(this,PublishGoodsActivity.class,bundle);
                }
                break;
        }
    }

    private void add2favor() {
        if (!isFavor) {
            handler.sendEmptyMessage(ADD);
        } else {
            handler.sendEmptyMessage(REMOVE);
        }
        isFavor = !isFavor;
    }


    /**
     * Init dots.
     *
     * @param i the
     */
    public void initDots(final int i) {
        ButterKnife.apply(DotList, new ButterKnife.Action<View>() {
            @Override
            public void apply(@NonNull View view, int index) {
                if (index == i) {
                    return;
                }
                view.setBackgroundResource(R.drawable.indictor_shape_normal);
            }
        });
        DotList.get(i).setBackgroundResource(R.drawable.indicator_shape_selected);
    }

    private void initDotList() {
        ButterKnife.apply(DotList, new ButterKnife.Action<View>() {
            @Override
            public void apply(@NonNull View view, int index) {
                if (index < mGoods.getImages().size()) {
                    return;
                }
                view.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Show banner data.
     *
     * @param images the images
     */
    private void showBannerData(List<ImageView> images) {
        if (images.size() <= 1) {
            mImageFliper.stopFlipping();
            //mImageFliper.stopNestedScroll();
        }
        for (int i = 0; i < images.size(); i++) {
            mImageFliper.addView(images.get(i));
        }
        mImageFliper.setOnPageChangeListener(new ImageFlipper.OnPageChangeListener() {
            @Override
            public void onPageSelected(int index) {
                initDots(index);
            }
        });

    }
    /*二手商品评论*/
    private void ShowSecondHandGoodsMessage(){

        mQiugouSellerList.setVisibility(View.GONE);
        messagelist.setVisibility(View.VISIBLE);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager2.setReverseLayout(true);
        messagelist.setLayoutManager(layoutManager2);

        messagelist.setHasFixedSize(true);
        messagelist.setNestedScrollingEnabled(false);

        BmobQuery<Message> messageBmobQuery = new BmobQuery<>();
        messageBmobQuery.addWhereEqualTo("Message_Goodsid", mGoods.getObjectId());
        messageBmobQuery.findObjects(this, new FindListener<Message>() {
            @Override
            public void onSuccess(List<Message> list) {
                Log.e(TAG, "onSuccess: 加载评论成功");
                MessageAdapter mAdapter = new MessageAdapter(R.layout.message_item, list, GoodsDetailActivity.this);
                mAdapter.openLoadAnimation();

                messagelist.addItemDecoration(new HorizontalDividerItemDecoration.Builder(GoodsDetailActivity.this)
                        .color(R.color.albumback).size(8)
                        .build());
                messagelist.setAdapter(mAdapter);
                /*for (Message m : list
                        ) {
                    Log.e(TAG, "onSuccess: " + m.getMessage());
                }*/
            }

            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "onError: "+s );
            }
        });
    }

    /*求购商品卖家回复*/
    private void ShowQiuGouGoodsMessage(){
       mQiugouSellerList.setVisibility(View.VISIBLE);
        messagelist.setVisibility(View.GONE);

       BmobQuery<Goods> query=new BmobQuery<>();
        query.addWhereEqualTo("is_qiugou_seller",true);
        query.addWhereEqualTo("qiugou_goods_id",mGoods.getObjectId());
        query.include("user");
        query.findObjects(this, new FindListener<Goods>() {
            @Override
            public void onSuccess(final List<Goods> list) {
                final List<User> users=new ArrayList<>();
                for (Goods goods:list
                     ) {
                    users.add(goods.getUser());
                }
                ExpandableListViewAdapter adapter=new ExpandableListViewAdapter(GoodsDetailActivity.this,list,users);
                mQiugouSellerList.setAdapter(adapter);
                for (int i=0;i<adapter.getGroupCount();i++){
                    mQiugouSellerList.collapseGroup(i);
                    mQiugouSellerList.expandGroup(i);
                }
                mQiugouSellerList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Goods", list.get(groupPosition));
                        bundle.putSerializable("User", users.get(groupPosition));
                        UIUtils.nextPage(GoodsDetailActivity.this,GoodsDetailActivity.class,bundle);
                        finish();
                        return true;
                    }
                });

                mQiugouSellerList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Goods", list.get(groupPosition));
                        bundle.putSerializable("User", users.get(groupPosition));
                        UIUtils.nextPage(GoodsDetailActivity.this,GoodsDetailActivity.class,bundle);
                        finish();
                        return true;
                    }
                });
            }

            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "onError: "+s );
            }
        });
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            BmobRelation relation = new BmobRelation();
            switch (msg.what){
                case ADD:
                    relation.add(mGoods);
                    img_favor.setImageResource(R.mipmap.icon_favor);
                    updateFavor(relation);
                    break;
                case REMOVE:
                    relation.remove(mGoods);
                    img_favor.setImageResource(R.mipmap.icon_unfavor);
                    updateFavor(relation);
                    break;
            }
        }
    };
    private void updateFavor(BmobRelation bmobRelation){
        User user = BmobUser.getCurrentUser(GoodsDetailActivity.this, User.class);
        if (user == null) {
            Toast.makeText(GoodsDetailActivity.this, "请先登录", Toast.LENGTH_LONG).show();
            UIUtils.nextPage(this, LoginActivity.class);
            return;
        }

        user.setLikes(bmobRelation);
        user.update(GoodsDetailActivity.this, new UpdateListener() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "onSuccess: ");
                if (isFavor)
                    Toast.makeText(GoodsDetailActivity.this, "已添加到我的收藏", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(GoodsDetailActivity.this, "已移出我的收藏", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e(TAG, "onFailure: ");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mGoods.isQiugou()) {
            ShowQiuGouGoodsMessage();
        }
        else
            ShowSecondHandGoodsMessage();
    }
}
