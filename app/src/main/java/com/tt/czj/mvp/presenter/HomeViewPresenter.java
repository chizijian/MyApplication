package com.tt.czj.mvp.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.tt.czj.mvp.models.Goods;

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
     * Load banner data.
     *
     * @param context    the context
     * @param imageViews the image views
     */
    void loadBannerData(Context context, List<ImageView> imageViews);

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

}
