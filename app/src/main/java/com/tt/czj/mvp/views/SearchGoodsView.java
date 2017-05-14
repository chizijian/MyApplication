package com.tt.czj.mvp.views;

import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;

import java.util.List;

/**
 * The interface Search goods view.
 */
public interface SearchGoodsView {
    /**
     * The constant TAG.
     */
    public static final String TAG = "SearchGoodsView";

    /**
     * Show key words result.
     *
     * @param goodsList the goods list
     */
    void showKeyWordsResult(List<Goods> goodsList);

    /**
     * Show second kind result.
     *
     * @param goodses the goodses
     */
    void showSecondKindResult(List<Goods> goodses);

    /**
     * Parse user.
     *
     * @param users the users
     */
    void parseUser(List<User> users);

    /**
     * On load goods error.
     *
     * @param str the str
     */
    void onLoadGoodsError(String str);
}
