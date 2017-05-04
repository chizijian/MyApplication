package com.tt.tradein.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tt.tradein.R;
import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.Message;
import com.tt.tradein.mvp.models.User;
import com.tt.tradein.ui.activity.BmobPay.BmobPayActivity;
import com.tt.tradein.ui.activity.base.BaseActivity;
import com.tt.tradein.ui.adapter.MessageAdapter;
import com.tt.tradein.ui.adapter.RecyclerViewAdapter;
import com.tt.tradein.utils.UIUtils;
import com.tt.tradein.widget.ImageFlipper;
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
     * The M goods text 3.
     */
    @BindView(R.id.goods_text3)
    TextView mGoodsText3;
    /**
     * The M goods details dot one.
     */
    @BindView(R.id.goods_details_dot_one)
    View mGoodsDetailsDotOne;
    /**
     * The M goods details dot two.
     */
    @BindView(R.id.goods_details_dot_two)
    View mGoodsDetailsDotTwo;
    /**
     * The M goods details dot three.
     */
    @BindView(R.id.goods_details_dot_three)
    View mGoodsDetailsDotThree;
    /**
     * The M goods details dot four.
     */
    @BindView(R.id.goods_details_dot_four)
    View mGoodsDetailsDotFour;
    /**
     * The M goods details dot five.
     */
    @BindView(R.id.goods_details_dot_five)
    View mGoodsDetailsDotFive;
    /**
     * The M goods details dot six.
     */
    @BindView(R.id.goods_details_dot_six)
    View mGoodsDetailsDotSix;
    /**
     * The M goods details dot seven.
     */
    @BindView(R.id.goods_details_dot_seven)
    View mGoodsDetailsDotSeven;
    /**
     * The M goods details dot eight.
     */
    @BindView(R.id.goods_details_dot_eight)
    View mGoodsDetailsDotEight;
    /**
     * The M goods details dot nine.
     */
    @BindView(R.id.goods_details_dot_nine)
    View mGoodsDetailsDotNine;
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
    /**
     * The Is favor.
     */
    boolean isFavor = false;

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
        initDotList();
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

        //gridViewAdapter = new GridViewAdapter(mGoods.getImages(),this);
        //gridView.setAdapter(gridViewAdapter);
        gridView.setLayoutManager(layoutManager);
        gridView.setAdapter(new RecyclerViewAdapter(this, mGoods.getImages()));

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager2.setReverseLayout(true);
        messagelist.setLayoutManager(layoutManager2);
        //messagelist.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));

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
                for (Message m : list
                        ) {
                    Log.e(TAG, "onSuccess: " + m.getMessage());
                }
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
    @OnClick({R.id.goods_detail_back, R.id.imageView_favor, R.id.imageView_talk, R.id.textView_Buy})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goods_detail_back:
                finish();
                break;
            case R.id.imageView_favor:
                add2favor();
                break;
            case R.id.imageView_talk:
                Intent intent = new Intent(this, LeaveMessageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Goods", mGoods);
                bundle.putSerializable("User", mUsers);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.textView_Buy:
                if (buy.getText().toString().equals("立即购买")) {
                    Intent intentbuy = new Intent(this, BmobPayActivity.class);
                    Bundle bundlebuy = new Bundle();
                    bundlebuy.putSerializable("Goods", mGoods);
                    bundlebuy.putSerializable("User", mUsers);
                    intentbuy.putExtras(bundlebuy);
                    startActivity(intentbuy);
                }
                break;
        }
    }

    private void add2favor() {

        User user = BmobUser.getCurrentUser(GoodsDetailActivity.this, User.class);
        if (user == null) {
            Toast.makeText(GoodsDetailActivity.this, "请先登录", Toast.LENGTH_LONG);
            UIUtils.nextPage(this, LoginActivity.class);
            return;
        }
        //user.setObjectId(mUsers.getObjectId());
        BmobRelation relation = new BmobRelation();

        if (!isFavor) {
            relation.add(mGoods);
            img_favor.setImageResource(R.mipmap.icon_favor);
        } else {
            relation.remove(mGoods);
            img_favor.setImageResource(R.mipmap.icon_unfavor);
        }
        //user.setAge(200);
        user.setLikes(relation);
        user.update(GoodsDetailActivity.this, new UpdateListener() {
            @Override
            public void onSuccess() {
                Log.e(TAG, "onSuccess: ");
                if (!isFavor)
                    Toast.makeText(GoodsDetailActivity.this, "已添加到我的收藏", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(GoodsDetailActivity.this, "已移出我的收藏", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e(TAG, "onFailure: ");
            }
        });
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
    public void showBannerData(List<ImageView> images) {
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

}
