package com.tt.czj.mvp.models;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by czj on 16-5-13.
 */
public class User extends BmobUser {
    private String sex;
    private Integer age;
    private String signature;
    private BmobFile photo;

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    private String alipay;

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    private String address;

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
     * Gets likes.
     *
     * @return the likes
     */
    public BmobRelation getLikes() {
        return likes;
    }

    /**
     * Sets likes.
     *
     * @param likes the likes
     */
    public void setLikes(BmobRelation likes) {
        this.likes = likes;
    }

    private BmobRelation likes;

  /*  public List<Goods> getFavor() {
        return favor;
    }

    public void setFavor(List<Goods> favor) {
        this.favor = favor;
    }*/

    //private List<Goods> favor;

    /**
     * Gets sex.
     *
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets sex.
     *
     * @param sex the sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * Gets signature.
     *
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Sets signature.
     *
     * @param signature the signature
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * Gets photo.
     *
     * @return the photo
     */
    public BmobFile getPhoto() {
        return photo;
    }

    /**
     * Sets photo.
     *
     * @param photo the photo
     */
    public void setPhoto(BmobFile photo) {
        this.photo = photo;
    }
}
