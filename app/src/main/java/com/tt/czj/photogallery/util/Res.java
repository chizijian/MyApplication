package com.tt.czj.photogallery.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;

/**
 * 加载R文件里面的内容
 *
 * @author king
 * @version 2014年10月18日 下午11:46:29
 * @QQ:595163260
 */
public class Res {

	// 文件路径名
	private static String pkgName;
	// R文件的对象
	private static Resources resources;

    /**
     * Init.
     *
     * @param context the context
     */
// 初始化文件夹路径和R资源
	public static void init(Context context) {
		pkgName = context.getPackageName(); 
		resources = context.getResources();
	}

    /**
     * layout文件夹下的xml文件id获取
     *
     * @param layoutName the layout name
     * @return the layout id
     */
    public static int getLayoutID(String layoutName) {
		return resources.getIdentifier(layoutName, "layout", pkgName);
	}

    /**
     * Gets widget id.
     *
     * @param widgetName the widget name
     * @return the widget id
     */
// 获取到控件的ID
	public static int getWidgetID(String widgetName) {
		return resources.getIdentifier(widgetName, "id", pkgName);
	}

    /**
     * anim文件夹下的xml文件id获取
     *
     * @param animName the anim name
     * @return the anim id
     */
    public static int getAnimID(String animName) {
		return resources.getIdentifier(animName, "anim", pkgName);
	}

    /**
     * xml文件夹下id获取
     *
     * @param xmlName the xml name
     * @return the xml id
     */
    public static int getXmlID(String xmlName) {
		return resources.getIdentifier(xmlName, "xml", pkgName);
	}

    /**
     * Gets xml.
     *
     * @param xmlName the xml name
     * @return the xml
     */
// 获取xml文件
	public static XmlResourceParser getXml(String xmlName) {
		int xmlId = getXmlID(xmlName);
		return (XmlResourceParser) resources.getXml(xmlId);
	}

    /**
     * raw文件夹下id获取
     *
     * @param rawName the raw name
     * @return the raw id
     */
    public static int getRawID(String rawName) {
		return resources.getIdentifier(rawName, "raw", pkgName);
	}

    /**
     * drawable文件夹下文件的id
     *
     * @param drawName the draw name
     * @return the drawable id
     */
    public static int getDrawableID(String drawName) {
		return resources.getIdentifier(drawName, "drawable", pkgName);
	}

    /**
     * Gets drawable.
     *
     * @param drawName the draw name
     * @return the drawable
     */
// 获取到Drawable文件
	public static Drawable getDrawable(String drawName) {
		int drawId = getDrawableID(drawName);
		return resources.getDrawable(drawId);
	}

    /**
     * value文件夹
     *
     * @param attrName the attr name
     * @return the attr id
     */
// 获取到value文件夹下的attr.xml里的元素的id
	public static int getAttrID(String attrName) {
		return resources.getIdentifier(attrName, "attr", pkgName);
	}

    /**
     * Gets dimen id.
     *
     * @param dimenName the dimen name
     * @return the dimen id
     */
// 获取到dimen.xml文件里的元素的id
	public static int getDimenID(String dimenName) {
		return resources.getIdentifier(dimenName, "dimen", pkgName);
	}

    /**
     * Gets color id.
     *
     * @param colorName the color name
     * @return the color id
     */
// 获取到color.xml文件里的元素的id
	public static int getColorID(String colorName) {
		return resources.getIdentifier(colorName, "color", pkgName);
	}

    /**
     * Gets color.
     *
     * @param colorName the color name
     * @return the color
     */
// 获取到color.xml文件里的元素的id
	public static int getColor(String colorName) {
		return resources.getColor(getColorID(colorName));
	}

    /**
     * Gets style id.
     *
     * @param styleName the style name
     * @return the style id
     */
// 获取到style.xml文件里的元素id
	public static int getStyleID(String styleName) {
		return resources.getIdentifier(styleName, "style", pkgName);
	}

    /**
     * Gets string id.
     *
     * @param strName the str name
     * @return the string id
     */
// 获取到String.xml文件里的元素id
	public static int getStringID(String strName) {
		return resources.getIdentifier(strName, "string", pkgName);
	}

    /**
     * Gets string.
     *
     * @param strName the str name
     * @return the string
     */
// 获取到String.xml文件里的元素
	public static String getString(String strName) {
		int strId = getStringID(strName);
		return resources.getString(strId);
	}

    /**
     * Get integer int [ ].
     *
     * @param strName the str name
     * @return the int [ ]
     */
// 获取color.xml文件里的integer-array元素
	public static int[] getInteger(String strName) {
		return resources.getIntArray(resources.getIdentifier(strName, "array",
				pkgName));
	}

}
