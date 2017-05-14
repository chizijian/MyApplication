package com.tt.czj.mvp.models;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by czj on 16-5-12.
 */
public class Goods extends BmobObject {
    private String title;//主题
    private String description;//描述
    private String new_degree;//新旧程度
    private String price;
    private String location;
    private String mount;
    private String userid;
    private String prince;
    private String kind;
    private String secondkind;

    public boolean isOff_shelve() {
        return off_shelve;
    }

    public void setOff_shelve(boolean off_shelve) {
        this.off_shelve = off_shelve;
    }

    private boolean off_shelve;

    public Boolean getIs_qiugou_seller() {
        return is_qiugou_seller;
    }

    public void setIs_qiugou_seller(Boolean is_qiugou_seller) {
        this.is_qiugou_seller = is_qiugou_seller;
    }

    public String getQiugou_goods_id() {
        return qiugou_goods_id;
    }

    public void setQiugou_goods_id(String qiugou_goods_id) {
        this.qiugou_goods_id = qiugou_goods_id;
    }

    private Boolean is_qiugou_seller;
    private String qiugou_goods_id;

    /**
     * Gets xiaoqu.
     *
     * @return the xiaoqu
     */
    public String getXiaoqu() {
        return xiaoqu;
    }

    /**
     * Sets xiaoqu.
     *
     * @param xiaoqu the xiaoqu
     */
    public void setXiaoqu(String xiaoqu) {
        this.xiaoqu = xiaoqu;
    }

    private String xiaoqu;

    /**
     * Is qiugou boolean.
     *
     * @return the boolean
     */
    public boolean isQiugou() {
        return qiugou;
    }

    /**
     * Sets qiugou.
     *
     * @param qiugou the qiugou
     */
    public void setQiugou(boolean qiugou) {
        this.qiugou = qiugou;
    }

    private boolean qiugou;

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    /**
     * Gets kind.
     *
     * @return the kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * Sets kind.
     *
     * @param kind the kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * Gets secondkind.
     *
     * @return the secondkind
     */
    public String getSecondkind() {
        return secondkind;
    }

    /**
     * Sets secondkind.
     *
     * @param secondkind the secondkind
     */
    public void setSecondkind(String secondkind) {
        this.secondkind = secondkind;
    }

    /**
     * Gets prince.
     *
     * @return the prince
     */
    public String getPrince() {
        return prince;
    }

    /**
     * Sets prince.
     *
     * @param prince the prince
     */
    public void setPrince(String prince) {
        this.prince = prince;
    }

    private List<String> images;

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets new degree.
     *
     * @return the new degree
     */
    public String getNew_degree() {
        return new_degree;
    }

    /**
     * Sets new degree.
     *
     * @param new_degree the new degree
     */
    public void setNew_degree(String new_degree) {
        this.new_degree = new_degree;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Gets mount.
     *
     * @return the mount
     */
    public String getMount() {
        return mount;
    }

    /**
     * Sets mount.
     *
     * @param mount the mount
     */
    public void setMount(String mount) {
        this.mount = mount;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets userid.
     *
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * Sets userid.
     *
     * @param userid the userid
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * Gets images.
     *
     * @return the images
     */
    public List<String> getImages() {
        return images;
    }

    /**
     * Sets images.
     *
     * @param images the images
     */
    public void setImages(List<String> images) {
        this.images = images;
    }

    public boolean equals(Object obj) {
        return (this.getObjectId().equals(((Goods) obj).getObjectId()));
    }
}
