package com.tt.czj.mvp.presenter;

import android.content.Context;

import java.util.List;

/**
 * The interface Publish activity presenter.
 */
public interface PublishActivityPresenter {
    /**
     * Publish goods boolean.
     *
     * @param context     the context
     * @param title       the title
     * @param description the description
     * @param images      the images
     * @param kind        the kind
     * @param secondKind  the second kind
     * @param price       the price
     * @param newDegree   the new degree
     * @param location    the location
     * @param prince      the prince
     * @param qiugou      the qiugou
     * @param xiaoqu      the xiaoqu
     * @return the boolean
     */
    public boolean publishGoods(Context context,String title,String description,List<String> images,
                                String kind,String secondKind,String price, String newDegree,String location,
                                String prince,boolean qiugou,String xiaoqu,boolean is_qiugou_seller,String qiugou_goods_id,boolean off_shelve);

    /**
     * Upload picture boolean.
     *
     * @param context the context
     * @param images  the images
     * @return the boolean
     */
    public boolean uploadPicture(Context context,String[]images);
}
