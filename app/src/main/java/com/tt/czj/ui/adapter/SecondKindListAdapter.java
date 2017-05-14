package com.tt.czj.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.mvp.models.SecondKind;

import java.util.List;

/**
 * Created by czj on 2016/5/18 0018.
 */
public class SecondKindListAdapter extends BaseAdapter{
    /**
     * The constant TAG.
     */
    public static final String TAG = "SecondKindListAdapter";
    private List<SecondKind> kinds;
    private Context mContext;
    private LayoutInflater inflator;

    /**
     * Instantiates a new Second kind list adapter.
     *
     * @param context the context
     * @param kinds   the kinds
     */
    public SecondKindListAdapter(Context context,List<SecondKind> kinds){
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
            convertView = inflator.inflate(R.layout.kind_listview_item,null);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.kind_item_text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(kinds.get(position).getKind());
        return convertView;
    }

    /**
     * The type View holder.
     */
    public class ViewHolder{
        /**
         * The Text view.
         */
        TextView textView;
    }
}
