package com.tt.tradein.mvp.views;

import android.widget.ImageView;

import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.QiugouGoods;
import com.tt.tradein.mvp.models.User;
import com.tt.tradein.mvp.models.WeatherResultBean;
import com.tt.tradein.ui.adapter.HomeHorizentalListViewAdapter;

import java.util.List;

/**
 * The interface Home view.
 */
public interface HomeView {
    /**
     * Showweather infor.
     *
     * @param weatherResultBean the weather result bean
     */
    void showweatherInfor(WeatherResultBean weatherResultBean);

    /**
     * Error load.
     *
     * @param t the t
     */
    void errorLoad(Throwable t);

    /**
     * Sets hirizental list view data.
     *
     * @param adapter the adapter
     */
    void setHirizentalListViewData(HomeHorizentalListViewAdapter adapter);

    /**
     * Show banner data.
     *
     * @param images the images
     */
    void showBannerData(List<ImageView> images);

    /**
     * On load goods infor success.
     *
     * @param goods the goods
     */
    void onLoadGoodsInforSuccess(List<Goods> goods);

    /**
     * On load goods error.
     *
     * @param str the str
     */
    void onLoadGoodsError(String str);

    /**
     * On load qiugou goods infor success.
     *
     * @param goods the goods
     */
    void onLoadQiugouGoodsInforSuccess(List<QiugouGoods> goods);

    /**
     * On load qiugo goods error.
     *
     * @param str the str
     */
    void onLoadQiugoGoodsError(String str);

    /**
     * Parse user.
     *
     * @param users the users
     */
    void parseUser(List<User> users);

    /**
     * Parse user.
     *
     * @param users   the users
     * @param goodses the goodses
     */
    void parseUser(List<User> users,List<Goods> goodses);

    /**
     * Parse qiugou user.
     *
     * @param users the users
     */
    void parseQiugouUser(List<User> users);
}
