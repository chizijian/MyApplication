package com.tt.tradein.mvp.views;

import com.tt.tradein.mvp.models.Goods;
import com.tt.tradein.mvp.models.User;

import java.util.List;

/**
 * The interface Near by view.
 */
public interface NearByView {
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
     * Parse user.
     *
     * @param users the users
     */
    void parseUser(List<User> users,List<Goods> goodses);
}
