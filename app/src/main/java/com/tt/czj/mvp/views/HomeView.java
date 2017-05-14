package com.tt.czj.mvp.views;

import android.widget.ImageView;

import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;

import java.util.List;

/**
 * The interface Home view.
 */
public interface HomeView {

    /**
     * Error load.
     *
     * @param t the t
     */
    void errorLoad(Throwable t);


    /**
     * Show banner data.
     *
     * @param images the images
     */
    void showBannerData(List<ImageView> images);

    /**
     * On load goods error.
     *
     * @param str the str
     */
    void onLoadGoodsError(String str);

    /**
     * Parse user.
     *
     * @param users   the users
     * @param goodses the goodses
     */
    void parseUser(List<User> users,List<Goods> goodses);

}
