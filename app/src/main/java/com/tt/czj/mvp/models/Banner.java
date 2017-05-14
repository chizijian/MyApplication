package com.tt.czj.mvp.models;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by czj on 16-5-9.
 */
public class Banner extends BmobObject {
    private String description;
    private BmobFile file;

    /**
     * Gets file.
     *
     * @return the file
     */
    public BmobFile getFile() {
        return file;
    }

    /**
     * Sets file.
     *
     * @param file the file
     */
    public void setFile(BmobFile file) {
        this.file = file;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
//
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

}
