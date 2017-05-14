package com.tt.czj.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.ui.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by czj on 2017/5/06/0006.
 */
public class QiuGouSellerActivity extends BaseActivity {
    /**
     * The M qiugou seller.
     */
    @BindView(R.id.qiugou_seller)
    TextView mQiugouSeller;
    /**
     * The M qiugou seller title.
     */
    @BindView(R.id.qiugou_seller_title)
    EditText mQiugouSellerTitle;
    /**
     * The M qiugou seller word message.
     */
    @BindView(R.id.qiugou_seller_word_message)
    EditText mQiugouSellerWordMessage;
    /**
     * The M qiugou seller no scrollgridview.
     */
    @BindView(R.id.qiugou_seller_noScrollgridview)
    GridView mQiugouSellerNoScrollgridview;
    /**
     * The M qiugou seller price.
     */
    @BindView(R.id.qiugou_seller_price)
    EditText mQiugouSellerPrice;
    /**
     * The M qiugou seller goods kind text.
     */
    @BindView(R.id.qiugou_seller_goods_kind_text)
    TextView mQiugouSellerGoodsKindText;
    /**
     * The M qiugou seller kind.
     */
    @BindView(R.id.qiugou_seller_kind)
    RelativeLayout mQiugouSellerKind;
    /**
     * The M qiugou seller new degree.
     */
    @BindView(R.id.qiugou_seller_new_degree)
    EditText mQiugouSellerNewDegree;
    /**
     * The M qiugou seller mount.
     */
    @BindView(R.id.qiugou_seller_mount)
    EditText mQiugouSellerMount;
    /**
     * The M qiugou seller location.
     */
    @BindView(R.id.qiugou_seller_location)
    TextView mQiugouSellerLocation;
    /**
     * The M qiugou seller publish xiaoqu.
     */
    @BindView(R.id.qiugou_seller_publish_xiaoqu)
    Spinner mQiugouSellerPublishXiaoqu;

    @Override
    public int getContentViewId() {
        return R.layout.activity_qiugou_seller;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    /**
     * On click.
     *
     * @param v the v
     */
    @OnClick({R.id.qiugou_seller, R.id.qiugou_seller_title, R.id.qiugou_seller_word_message, R.id.qiugou_seller_noScrollgridview, R.id.qiugou_seller_price, R.id.qiugou_seller_new_degree, R.id.qiugou_seller_mount})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qiugou_seller:
                break;
            case R.id.qiugou_seller_title:
                break;
            case R.id.qiugou_seller_word_message:
                break;
            case R.id.qiugou_seller_noScrollgridview:
                break;
            case R.id.qiugou_seller_price:
                break;
            case R.id.qiugou_seller_new_degree:
                break;
            case R.id.qiugou_seller_mount:
                break;
        }
    }
}
