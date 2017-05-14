package com.tt.czj.mvp.models;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by czj on 2016/5/17 0017.
 */
public class SecondKind extends BmobObject {
    /**
     * The constant TAG.
     */
    public static final String TAG = "SecondKind";
    private String kind;
    private String parentid;
    private BmobFile image;

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
     * Gets parentid.
     *
     * @return the parentid
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * Sets parentid.
     *
     * @param parentid the parentid
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public BmobFile getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(BmobFile image) {
        this.image = image;
    }
}
