package com.tt.czj.ui.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/06/0006.
 */
public class RecyclerViewHolder extends ViewHolder {

    private SparseArray<View> arrayView;

    /**
     * Instantiates a new Recycler view holder.
     *
     * @param itemView the item view
     */
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        arrayView = new SparseArray<>();
    }

    /**
     * 通过填写的itemId来获取具体的View的对象
     *
     * @param <T>    必须是View的子类
     * @param itemId R.id.***
     * @return t t
     */
    public <T extends View> T getView(int itemId){
        //arrayVie类似于Map容器，get(key)取出的是value值
        View mView = arrayView.get(itemId);
        if(mView == null){
            //实例化具体的View类型
            mView = itemView.findViewById(itemId);
            arrayView.put(itemId,mView);
        }
        return (T) mView;
    }

    /**
     * 设置TextView的内容
     *
     * @param itemId the item id
     * @param text   the text
     */
    public void setText(int itemId,String text){
        TextView tv = getView(itemId);
        tv.setText(text);
    }

    /**
     * 设置图片
     *
     * @param itemId  the item id
     * @param imageId the image id
     */
    public void setBitmapImage(int itemId,int imageId){
        ImageView iv = getView(itemId);
        iv.setImageResource(imageId);
    }
}
