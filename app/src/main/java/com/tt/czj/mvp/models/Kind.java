package com.tt.czj.mvp.models;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by czj on 2016/5/18 0018.
 */
public class Kind extends BmobObject {
    /**
     * The constant TAG.
     */
    public static final String TAG = "Kind";
    private String description;
    private String kind;
    private BmobFile image;

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
