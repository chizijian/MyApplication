package com.tt.czj.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tt.czj.R;
import com.tt.czj.mvp.models.Kind;

import java.util.List;

/**
 * Created by czj on 2016/5/19 0019.
 */
public class KindActivityListAdapter extends BaseAdapter {
    /**
     * The constant TAG.
     */
    public static final String TAG = "KindActivityListAdapter";
    private List<Kind> kinds;
    private Context mContext;
    private LayoutInflater inflator;

    /**
     * Instantiates a new Kind activity list adapter.
     *
     * @param context the context
     * @param kinds   the kinds
     */
    public KindActivityListAdapter(Context context,List<Kind> kinds){
        mContext = context;
        this.kinds = kinds;
    }
    @Override
    public int getCount() {
        return kinds.size();
    }

    @Override
    public Object getItem(int position) {
        return kinds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            inflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.activity_kind_item_layout,null);
            viewHolder.kindTextView = (TextView)convertView.findViewById(R.id.activity_kind_item_kind_textview);
            viewHolder.descriptionTextView = (TextView)convertView.findViewById(R.id.activity_kind_item_description_textview);
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.activity_kind_item_imageview);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.kindTextView.setText(kinds.get(position).getKind());
        if(kinds.get(position).getImage()!=null)
            Glide.with(mContext).load(kinds.get(position).getImage().getUrl()).into(viewHolder.imageView);
        viewHolder.descriptionTextView.setText(kinds.get(position).getDescription());
        return convertView;
    }

    /**
     * The type View holder.
     */
    public class ViewHolder{
        /**
         * The Kind text view.
         */
        TextView kindTextView, /**
         * The Description text view.
         */
        descriptionTextView;
        /**
         * The Image view.
         */
        ImageView imageView;
    }
}
