package com.tt.czj.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2017/5/02/0002.
 */
public class TakePhotoUtil {
    /**
     * 使用LruCache来缓存图片
     */
    static RequestQueue queue;
    private static class BitmapCache implements ImageLoader.ImageCache {

        private LruCache<String, Bitmap> mCache;

        /**
         * Instantiates a new Bitmap cache.
         */
        public BitmapCache() {
            // 获取应用程序最大可用内存
            int maxMemory = (int) Runtime.getRuntime().maxMemory();
            int cacheSize = maxMemory / 8;
            mCache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap bitmap) {
                    return bitmap.getRowBytes() * bitmap.getHeight();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String url) {
            return mCache.get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            mCache.put(url, bitmap);
        }

    }

    /**
     * Gets image loader.
     *
     * @param context the context
     * @return the image loader
     */
    public static ImageLoader getmImageLoader(Context context) {
        if(queue==null)
                queue = Volley.newRequestQueue(context);
        return new ImageLoader(queue, new BitmapCache());
    }
}
