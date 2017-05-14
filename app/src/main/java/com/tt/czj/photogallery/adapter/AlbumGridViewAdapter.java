package com.tt.czj.photogallery.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tt.czj.photogallery.util.BitmapCache;
import com.tt.czj.photogallery.util.ImageItem;
import com.tt.czj.photogallery.util.Res;
import com.tt.czj.utils.MeasureUtils;

import java.util.ArrayList;

/**
 * 这个是显示一个文件夹里面的所有图片时用的适配器
 */
public class AlbumGridViewAdapter extends BaseAdapter {
    /**
     * The Tag.
     */
    final String TAG = getClass().getSimpleName();
	private Context mContext;
	private ArrayList<ImageItem> dataList;
	private ArrayList<ImageItem> selectedDataList;
	private DisplayMetrics dm;
    /**
     * The Cache.
     */
    BitmapCache cache;

    /**
     * Instantiates a new Album grid view adapter.
     *
     * @param c                the c
     * @param dataList         the data list
     * @param selectedDataList the selected data list
     */
    public AlbumGridViewAdapter(Context c, ArrayList<ImageItem> dataList,
								ArrayList<ImageItem> selectedDataList) {
		mContext = c;
		cache = new BitmapCache();
		this.dataList = dataList;
		this.selectedDataList = selectedDataList;
		dm = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
	}

	public int getCount() {
		return dataList.size();
	}

	public Object getItem(int position) {
		return dataList.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

    /**
     * The Callback.
     */
    BitmapCache.ImageCallback callback = new BitmapCache.ImageCallback() {
		@Override
		public void imageLoad(ImageView imageView, Bitmap bitmap,
							  Object... params) {
			if (imageView != null && bitmap != null) {
				String url = (String) params[0];
				if (url != null && url.equals((String) imageView.getTag())) {
					((ImageView) imageView).setImageBitmap(bitmap);
				} else {
					Log.e(TAG, "callback, bmp not match");
				}
			} else {
				Log.e(TAG, "callback, bmp null");
			}
		}
	};
	
	/**
	 * 存放列表项控件句柄
	 */
	private class ViewHolder {
        /**
         * The Image view.
         */
        public ImageView imageView;
        /**
         * The Toggle button.
         */
        public ToggleButton toggleButton;
        /**
         * The Choosetoggle.
         */
        public Button choosetoggle;
        /**
         * The Text view.
         */
        public TextView textView;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(
					Res.getLayoutID("plugin_camera_select_imageview"), parent, false);
			viewHolder.imageView = (ImageView) convertView
					.findViewById(Res.getWidgetID("image_view"));
			viewHolder.toggleButton = (ToggleButton) convertView
					.findViewById(Res.getWidgetID("toggle_button"));
			viewHolder.choosetoggle = (Button) convertView
					.findViewById(Res.getWidgetID("choosedbt"));
			int width = MeasureUtils.getBaseCellWidth(MeasureUtils.getScreenWidth(mContext));
			convertView.setLayoutParams(new AbsListView.LayoutParams(width,width));
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		String path;
		if (dataList != null && dataList.size() > position)
			path = dataList.get(position).imagePath;
		else
			path = "camera_default";
		if (path.contains("camera_default")) {
			viewHolder.imageView.setImageResource(Res.getDrawableID("plugin_camera_no_pictures"));
		} else {
//			ImageManager2.from(mContext).displayImage(viewHolder.imageView,
//					path, Res.getDrawableID("plugin_camera_camera_default"), 100, 100);
			final ImageItem item = dataList.get(position);
			viewHolder.imageView.setTag(item.imagePath);
			cache.displayBmp(viewHolder.imageView, item.thumbnailPath, item.imagePath,
					callback);
		}
		viewHolder.toggleButton.setTag(position);
		viewHolder.choosetoggle.setTag(position);
		viewHolder.toggleButton.setOnClickListener(new ToggleClickListener(viewHolder.choosetoggle));
		if (selectedDataList.contains(dataList.get(position))) {
			viewHolder.toggleButton.setChecked(true);
			viewHolder.choosetoggle.setVisibility(View.VISIBLE);
		} else {
			viewHolder.toggleButton.setChecked(false);
			viewHolder.choosetoggle.setVisibility(View.GONE);
		}
		return convertView;
	}

    /**
     * Dip to px int.
     *
     * @param dip the dip
     * @return the int
     */
    public int dipToPx(int dip) {
		return (int) (dip * dm.density + 0.5f);
	}
	private class ToggleClickListener implements OnClickListener {
        /**
         * The Choose bt.
         */
        Button chooseBt;

        /**
         * Instantiates a new Toggle click listener.
         *
         * @param choosebt the choosebt
         */
        public ToggleClickListener(Button choosebt){
			this.chooseBt = choosebt;
		}
		
		@Override
		public void onClick(View view) {
			if (view instanceof ToggleButton) {
				ToggleButton toggleButton = (ToggleButton) view;
				int position = (Integer) toggleButton.getTag();
				if (dataList != null && mOnItemClickListener != null
						&& position < dataList.size()) {
					mOnItemClickListener.onItemClick(toggleButton, position, toggleButton.isChecked(),chooseBt);
				}
			}
		}
	}
	

	private OnItemClickListener mOnItemClickListener;

    /**
     * Sets on item click listener.
     *
     * @param l the l
     */
    public void setOnItemClickListener(OnItemClickListener l) {
		mOnItemClickListener = l;
	}

    /**
     * The interface On item click listener.
     */
    public interface OnItemClickListener {
        /**
         * On item click.
         *
         * @param view      the view
         * @param position  the position
         * @param isChecked the is checked
         * @param chooseBt  the choose bt
         */
        public void onItemClick(ToggleButton view, int position,
								boolean isChecked, Button chooseBt);
	}

}
