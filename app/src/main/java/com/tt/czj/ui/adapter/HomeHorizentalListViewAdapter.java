package com.tt.czj.ui.adapter;

/**
 * Created by czj on 2016/5/6 0006.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.utils.UIUtils;

/**
 * The type Home horizental list view adapter.
 */
public class HomeHorizentalListViewAdapter extends BaseAdapter{
    private int[] mIconIDs;
    private int[] mTitles;
    private Context mContext;
    private LayoutInflater mInflater;
    /**
     * The Icon bitmap.
     */
    Bitmap iconBitmap;
    private int selectIndex = -1;

    /**
     * Instantiates a new Home horizental list view adapter.
     *
     * @param context the context
     * @param titles  the titles
     * @param ids     the ids
     */
    public HomeHorizentalListViewAdapter(Context context, int[] titles, int[] ids){
        this.mContext = context;
        this.mIconIDs = ids;
        this.mTitles = titles;
        mInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mIconIDs.length;
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
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.home_fragment_horixontal_listview_item, null);
            holder.mImage=(ImageView)convertView.findViewById(R.id.horizontal_list_item_imageview);
            holder.mTitle=(TextView)convertView.findViewById(R.id.horizontal_list_item_textview);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        if(position == selectIndex){
            convertView.setSelected(true);
        }else{
            convertView.setSelected(false);
        }

        holder.mTitle.setText(mTitles[position]);
        holder.mImage.setImageResource(mIconIDs[position]);
        return convertView;
    }

    private static class ViewHolder {
        private TextView mTitle ;
        private ImageView mImage;
    }
    private Bitmap getPropThumnail(int id){
        Drawable d = mContext.getResources().getDrawable(id);
        Bitmap b = UIUtils.drawableToBitmap(d);
//		Bitmap bb = BitmapUtil.getRoundedCornerBitmap(b, 100);
//        int w = mContext.getResources().getDimensionPixelOffset(R.dimen.thumnail_default_width);
//        int h = mContext.getResources().getDimensionPixelSize(R.dimen.thumnail_default_height);
//
//        Bitmap thumBitmap = ThumbnailUtils.extractThumbnail(b, w, h);

        return b;
    }

    /**
     * Set select index.
     *
     * @param i the
     */
    public void setSelectIndex(int i){
        selectIndex = i;
    }
}
