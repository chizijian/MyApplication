package com.tt.czj.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.tt.czj.R;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by czj on 16-6-14.
 */
public class GridViewAdapter extends BaseAdapter {
    private List<String> mImages;
    private Context mContext;
    private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
    final private DisplayImageOptions options = getSimpleOptions();
    final private ImageLoader imageLoader = ImageLoader.getInstance();

    /**
     * Instantiates a new Grid view adapter.
     *
     * @param mImages  the m images
     * @param mContext the m context
     */
    public GridViewAdapter(List<String> mImages, Context mContext) {
        Log.e("GridViewAdapter", "getView: start");
        this.mImages = mImages;
        this.mContext = mContext;
    }

    private class ViewHolder {
        /**
         * The Image.
         */
        public ImageView image;
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        final ViewHolder holder = new ViewHolder();;
        //通过convertView来判断是否已经加载过了，如果没有就加载
        if (convertView == null) {
            holder.image = new ImageView(mContext);
        } else {
            holder.image = (ImageView) convertView;
        }

        /**
         * 显示图片
         * 参数1：图片url
         * 参数2：显示图片的控件
         * 参数3：显示图片的设置
         * 参数4：监听器
         */
        imageLoader.displayImage(mImages.get(position), holder.image, options,animateFirstListener);

        return holder.image;
    }

    /**
     * 设置常用的设置项
     * @return
     */
    private DisplayImageOptions getSimpleOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(android.R.drawable.ic_menu_upload) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(android.R.drawable.stat_notify_error)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .build();//构建完成
        return options;
    }

    /**
     * 图片加载第一次显示监听器
     * @author Administrator
     *
     */
    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        /**
         * The Displayed images.
         */
        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                // 是否第一次显示
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    // 图片淡入效果
                    FadeInBitmapDisplayer.animate(imageView, 200);
                    displayedImages.add(imageUri);
                }
            }
        }
    }

    /*@Override
    public int getCount() {

       Log.e("GridViewAdapter", "getCount: "+mImages.size());
        return mImages.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("GridViewAdapter", "getView: "+mImages.size());
        //Toast.makeText(mContext,mImages.size(), Toast.LENGTH_LONG);
        final ImageView imageview; // 声明ImageView的对象
        if (convertView == null) {
            imageview = new ImageView(mContext); // 实例化ImageView的对象
            imageview.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // 设置缩放方式
        } else {
            imageview = (ImageView) convertView;
        }
        //Glide.with(mContext).load(mImages.get(position)).into(imageview); // 为ImageView设置要显示的图片
        ImageLoader.getInstance().loadImage(mImages.get(position),new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view,
                                          Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                imageview.setImageBitmap(loadedImage);
            }
        });
        return imageview;
    }*/
}
