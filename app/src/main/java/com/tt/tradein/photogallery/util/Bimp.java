package com.tt.tradein.photogallery.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The type Bimp.
 */
public class Bimp {
    /**
     * The constant max.
     */
    public static int max = 0;

    /**
     * The constant tempSelectBitmap.
     */
    public static ArrayList<ImageItem> tempSelectBitmap = new ArrayList<ImageItem>();   //选择的图片的临时列表

    /**
     * Revition image size bitmap.
     *
     * @param path the path
     * @return the bitmap
     * @throws IOException the io exception
     */
    public static Bitmap revitionImageSize(String path) throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            if ((options.outWidth >> i <= 1000)
                    && (options.outHeight >> i <= 1000)) {
                in = new BufferedInputStream(
                        new FileInputStream(new File(path)));
                options.inSampleSize = (int) Math.pow(2.0D, i);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(in, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }

    /**
     * Contains boolean.
     *
     * @param item the item
     * @return the boolean
     */
    public static  boolean Contains(ImageItem item) {
        for (ImageItem imageItem : tempSelectBitmap
                ) {
            if (imageItem.getImagePath().equals(item.getImagePath()))
                return true;
        }
        return false;
    }
}