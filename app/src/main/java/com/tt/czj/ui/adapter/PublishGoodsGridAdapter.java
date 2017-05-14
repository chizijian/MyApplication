package com.tt.czj.ui.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.tt.czj.R;
import com.tt.czj.photogallery.util.Bimp;
import com.tt.czj.utils.MeasureUtils;

/**
 * Created by czj on 16-5-15.
 */
public class PublishGoodsGridAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private int selectedPosition = -1;
    private boolean shape;
    private Context mContext;
    private OnPublishGridDeleteItemListener listener;

    /**
     * Instantiates a new Publish goods grid adapter.
     *
     * @param context the context
     */
    public PublishGoodsGridAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * Sets listener.
     *
     * @param listener the listener
     */
    public void setListener(OnPublishGridDeleteItemListener listener) {
        this.listener = listener;
    }

    /**
     * Is shape boolean.
     *
     * @return the boolean
     */
    public boolean isShape() {
        return shape;
    }

    /**
     * Sets shape.
     *
     * @param shape the shape
     */
    public void setShape(boolean shape) {
        this.shape = shape;
    }

    public int getCount() {
        if(Bimp.tempSelectBitmap.size() == 9){
            return 9;
        }
        return (Bimp.tempSelectBitmap.size() + 1);
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int arg0) {
        return 0;
    }

    /**
     * Sets selected position.
     *
     * @param position the position
     */
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    /**
     * Gets selected position.
     *
     * @return the selected position
     */
    public int getSelectedPosition() {
        return selectedPosition;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_published_grida, parent, false);
            holder = new ViewHolder();
            int width = MeasureUtils.getBase5Width(MeasureUtils.getScreenWidth(mContext));
            convertView.setLayoutParams(new AbsListView.LayoutParams(width,width));
            holder.deleteImageView = (ImageView)convertView.findViewById(R.id.item_grida_delete);
            holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteOnClick(v,position);
            }
        });
        if (position ==Bimp.tempSelectBitmap.size()) {
            holder.image.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_addpic_unfocused));
            holder.deleteImageView.setVisibility(View.INVISIBLE);
            if (position == 9) {
                holder.image.setVisibility(View.GONE);
            }
        } else {
            holder.deleteImageView.setVisibility(View.VISIBLE);
            holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(position).getBitmap());
        }

        return convertView;
    }

    /**
     * The type View holder.
     */
    public class ViewHolder {
        /**
         * The Delete image view.
         */
        public ImageView deleteImageView;
        /**
         * The Image.
         */
        public ImageView image;
    }

    /**
     * The interface On publish grid delete item listener.
     */
    public interface OnPublishGridDeleteItemListener{
        /**
         * Delete on click.
         *
         * @param v        the v
         * @param position the position
         */
        void deleteOnClick(View v,int position);
    }
}
