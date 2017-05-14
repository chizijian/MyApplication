package com.tt.czj.mvp.models;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by czj on 16-5-17.
 */
public class KindSort extends BmobObject {
    private String kind;
    private BmobFile file1;
    private BmobFile file2;
    private BmobFile file3;

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
     * Gets file 1.
     *
     * @return the file 1
     */
    public BmobFile getFile1() {
        return file1;
    }

    /**
     * Sets file 1.
     *
     * @param file1 the file 1
     */
    public void setFile1(BmobFile file1) {
        this.file1 = file1;
    }

    /**
     * Gets file 2.
     *
     * @return the file 2
     */
    public BmobFile getFile2() {
        return file2;
    }

    /**
     * Sets file 2.
     *
     * @param file2 the file 2
     */
    public void setFile2(BmobFile file2) {
        this.file2 = file2;
    }

    /**
     * Gets file 3.
     *
     * @return the file 3
     */
    public BmobFile getFile3() {
        return file3;
    }

    /**
     * Sets file 3.
     *
     * @param file3 the file 3
     */
    public void setFile3(BmobFile file3) {
        this.file3 = file3;
    }
}
