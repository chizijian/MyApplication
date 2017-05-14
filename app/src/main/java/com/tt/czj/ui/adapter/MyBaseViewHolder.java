package com.tt.czj.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2017/4/06/0006.
 */
public class MyBaseViewHolder extends BaseViewHolder {
    /**
     * Instantiates a new My base view holder.
     *
     * @param view the view
     */
    protected MyBaseViewHolder(View view) {
        super(view);
    }

    /**
     * 给View设置标题
     *
     * @param viewId view的id
     * @param title  标题的内容
     * @return text text
     */
    public BaseViewHolder setText(int viewId, String title) {
        TextView view = getView(viewId);
        view.setText(title);
        return this;
    }

    /**
     * 给imageView设置图片
     *
     * @param viewId imageView的id
     * @param imgUrl 图片的url
     * @return image resource
     */
    public BaseViewHolder setImageResource(int viewId, String imgUrl) {
        ImageView view = getView(viewId);
        ImageLoader.getInstance().displayImage(imgUrl,view);
        return this;
    }
}
