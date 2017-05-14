package com.tt.czj.mvp.presenter;

import android.content.Context;

import com.tt.czj.mvp.models.Goods;

import java.util.List;

/**
 * The interface Search goods presenter.
 */
public interface SearchGoodsPresenter {
    /**
     * The constant TAG.
     */
    public static final String TAG = "SearchGoodsPresenter";

    /**
     * Query goods by kind.
     *
     * @param context the context
     * @param kind    the kind
     */
    void queryGoodsBYKind(Context context,String kind);

    /**
     * Query goods by second kind.
     *
     * @param context    the context
     * @param secondkind the secondkind
     */
    void queryGoodsBySecondKind(Context context, String secondkind);

    /**
     * Query goods by key words.
     *
     * @param context  the context
     * @param keyWords the key words
     */
    void queryGoodsByKeyWords(Context context,String keyWords);

    /**
     * Parse goods user.
     *
     * @param context the context
     * @param goods   the goods
     */
    void parseGoodsUser(Context context, final List<Goods> goods);
}
