package com.tt.czj.mvp.views;

import java.util.List;

/**
 * The interface Publish view.
 */
public interface PublishView {
    /**
     * Publish success.
     */
    void publishSuccess();

    /**
     * Publish error.
     *
     * @param str the str
     */
    void publishError(String str);

    /**
     * Upload success.
     *
     * @param images the images
     */
    void uploadSuccess(List<String> images);

    /**
     * Upload failed.
     *
     * @param str the str
     */
    void uploadFailed(String str);
}
