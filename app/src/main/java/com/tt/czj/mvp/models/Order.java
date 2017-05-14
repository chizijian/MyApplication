package com.tt.czj.mvp.models;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/4/13/0013.
 */
public class Order extends BmobObject {
    private String orderId;

    /**
     * Gets is success.
     *
     * @return the is success
     */
    public String getIsSuccess() {
        return isSuccess;
    }

    /**
     * Sets is success.
     *
     * @param isSuccess the is success
     */
    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    private String isSuccess;

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets order id.
     *
     * @param orderId the order id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets seller.
     *
     * @return the seller
     */
    public User getSeller() {
        return seller;
    }

    /**
     * Sets seller.
     *
     * @param seller the seller
     */
    public void setSeller(User seller) {
        this.seller = seller;
    }

    /**
     * Gets buyer.
     *
     * @return the buyer
     */
    public User getBuyer() {
        return buyer;
    }

    /**
     * Sets buyer.
     *
     * @param buyer the buyer
     */
    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    /**
     * Gets goods.
     *
     * @return the goods
     */
    public Goods getGoods() {
        return goods;
    }

    /**
     * Sets goods.
     *
     * @param goods the goods
     */
    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets details.
     *
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets details.
     *
     * @param details the details
     */
    public void setDetails(String details) {
        this.details = details;
    }

    private User seller;
    private User buyer;
    private Goods goods;
    private double price;
    private String details;
}
