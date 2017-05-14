package com.tt.czj.utils;

/**
 * The type Display util.
 */
public class DisplayUtil {
    /**
     * Px 2 dip int.
     *
     * @param pxValue the px value
     * @param scale   the scale
     * @return the int
     */
    public static int px2dip(float pxValue, float scale) {
        return (int) ((pxValue / scale) + 0.5f);
    }

    /**
     * Dip 2 px int.
     *
     * @param dipValue the dip value
     * @param scale    the scale
     * @return the int
     */
    public static int dip2px(float dipValue, float scale) {
        return (int) ((dipValue * scale) + 0.5f);
    }

    /**
     * Px 2 sp int.
     *
     * @param pxValue   the px value
     * @param fontScale the font scale
     * @return the int
     */
    public static int px2sp(float pxValue, float fontScale) {
        return (int) ((pxValue / fontScale) + 0.5f);
    }

    /**
     * Sp 2 px int.
     *
     * @param spValue   the sp value
     * @param fontScale the font scale
     * @return the int
     */
    public static int sp2px(float spValue, float fontScale) {
        return (int) ((spValue * fontScale) + 0.5f);
    }
}
