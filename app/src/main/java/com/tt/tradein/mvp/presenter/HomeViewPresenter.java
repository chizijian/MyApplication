package com.tt.tradein.mvp.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.QiugouGoods;

import java.util.List;

/**
 * The interface Home view presenter.
 */
public interface HomeViewPresenter {
    /**
     * The constant TAG.
     */
    public static final String TAG = "HomeViewPresenter";

    /**
     * Load.
     *
     * @param apikey   the apikey
     * @param cityname the cityname
     */
    public void load(String apikey,String cityname);

    /**
     * Load horizental list view data.
     *
     * @param context the context
     */
    public void loadHorizentalListViewData(Context context);

    /**
     * Load banner data.
     *
     * @param context    the context
     * @param imageViews the image views
     */
    public void loadBannerData(Context context, List<ImageView> imageViews);

    /**
     * Load goods infor.
     *
     * @param context the context
     * @param qiugou  the qiugou
     */
    void loadGoodsInfor(Context context,boolean qiugou);

    /**
     * Parse goods user.
     *
     * @param context the context
     * @param goods   the goods
     */
    void parseGoodsUser(Context context,List<Goods> goods);

    /**
     * Load qiugou goods info.
     *
     * @param context the context
     */
    void loadQiugouGoodsInfo(Context context);

    /**
     * Parse qiugou goods user.
     *
     * @param context      the context
     * @param qiugou_goods the qiugou goods
     */
    void parseQiugouGoodsUser(Context context,List<QiugouGoods> qiugou_goods);
}
