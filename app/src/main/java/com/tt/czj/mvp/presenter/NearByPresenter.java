package com.tt.czj.mvp.presenter;

import android.content.Context;

import com.tt.czj.mvp.models.Goods;

import java.util.List;

/**
 * The interface Near by presenter.
 */
public interface NearByPresenter {
    /**
     * Load goods infor.
     *
     * @param context the context
     * @param city    the city
     * @param qiugou  the qiugou
     */
    void loadGoodsInfor(Context context,String city,boolean qiugou);

    /**
     * Parse goods user.
     *
     * @param context the context
     * @param goods   the goods
     */
    void parseGoodsUser(Context context,List<Goods> goods);
}
