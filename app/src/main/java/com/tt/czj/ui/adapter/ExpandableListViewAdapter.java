package com.tt.czj.ui.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tt.czj.R;
import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;
import com.tt.czj.ui.activity.GoodsDetailActivity;
import com.tt.czj.utils.TakePhotoUtil;
import com.tt.czj.utils.TimeUtils;
import com.tt.czj.utils.ToastUtil;
import com.tt.czj.utils.UIUtils;
import com.tt.czj.widget.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by czj on 2/3/17.
 */
public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "ExpandableAdapter";

    /**
     * Gets context.
     *
     * @return the context
     */
    public Context getContext() {
        return context;
    }

    /**
     * Sets context.
     *
     * @param context the context
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Gets goods.
     *
     * @return the goods
     */
    public List<Goods> getGoods() {
        return goods;
    }

    /**
     * Sets goods.
     *
     * @param goods the goods
     */
    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    private Context context;
    private List<Goods> goods = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            notifyDataSetChanged();//更新数据
            super.handleMessage(msg);
        }
    };

    /**
     * Refresh.
     *
     * @param expandableListView the expandable list view
     * @param groupPosition      the group position
     */
/*供外界更新数据的方法*/
    public void refresh(ExpandableListView expandableListView, int groupPosition) {
        handler.sendMessage(new Message());
        //必须重新伸缩之后才能更新数据
        expandableListView.collapseGroup(groupPosition);
        expandableListView.expandGroup(groupPosition);
    }

    /**
     * Instantiates a new Expandable list view adapter.
     *
     * @param context the context
     * @param brands  the brands
     * @param users   the users
     */
    public ExpandableListViewAdapter(Context context, List<Goods> brands, List<User> users) {
        super();
        this.context = context;
        setDate(users, brands);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return goods.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return goods.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return goods.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        //Log.e(TAG, "getGroupView: " + "第" + groupPosition + "行");
        ParentHolder parentHolder;

        if (convertView == null) {
            LayoutInflater userInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = userInflater.inflate(R.layout.item_parent, null);
            convertView.setHorizontalScrollBarEnabled(true);
            parentHolder = new ParentHolder(convertView);
            convertView.setTag(parentHolder);
            // Log.e(TAG, "getGroupView: "+"为空：" + groupPosition );
        } else {
            parentHolder = (ParentHolder) convertView.getTag();
            //Log.e(TAG, "getGroupView: "+"不为空：" + groupPosition );
        }
        parentHolder.mUserNameTextView.setText(users.get(groupPosition).getUsername());
        parentHolder.mDescriptionTextView.setText(goods.get(groupPosition).getDescription());
        parentHolder.mPriceTextView.setText("￥" + goods.get(groupPosition).getPrice());
        if (!users.isEmpty())
            if (users.get(groupPosition).getPhoto() != null) {
                //Log.e(TAG, "getGroupView: user="+users.get(groupPosition).getUsername()+"   photo="+users.get(groupPosition).getPhoto().getFilename());
                parentHolder.mUserPhotoImageView.setDefaultImageResId(R.mipmap.icon_photo);
                parentHolder.mUserPhotoImageView.setImageUrl(users.get(groupPosition).getPhoto()
                        .getUrl(), TakePhotoUtil.getmImageLoader(context));
            } else {
              // Log.e(TAG, "getGroupView: user="+users.get(groupPosition).getUsername());
                parentHolder.mUserPhotoImageView.setImageResource(R.mipmap.icon_photo);
            }
        else {
            ToastUtil.showToast(context, "数据加载失败");
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //Log.e(TAG, "getChildView: " + "第" + groupPosition + "行" + "," + "第" + childPosition + "项");
        ChildHolder childHolder;
        if (goods.get(groupPosition).getImages() == null) {
            return new View(context);
        }
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_group_child, parent, false);
            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        childHolder.mTimeTextView.setText(TimeUtils.convert_before(goods.get(groupPosition).getCreatedAt()));
        childHolder.mLocationTextView.setText(goods.get(groupPosition).getLocation());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        childHolder.horizontalListView.setLayoutManager(layoutManager);
        RecyclerViewAdapter horizontalListAdapter = new RecyclerViewAdapter(context, goods.get(groupPosition).getImages());
        horizontalListAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("Goods", goods.get(groupPosition));
                bundle.putSerializable("User", users.get(groupPosition));
                UIUtils.nextPage(context,GoodsDetailActivity.class,bundle);
            }
        });
        childHolder.horizontalListView.setAdapter(horizontalListAdapter);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    private void setDate(List<User> users, List<Goods> goods) {
        setUsers(users);
        setGoods(goods);
        notifyDataSetChanged();
    }

    /**
     * The type Child holder.
     */
    static class ChildHolder {
        /**
         * The Horizontal list view.
         */
        @BindView(R.id.pictures)
        RecyclerView horizontalListView ;
        /**
         * The Location.
         */
        @BindView(R.id.location)
       ImageView location;
        /**
         * The M location text view.
         */
        @BindView(R.id.location_textview)
       TextView mLocationTextView;
        /**
         * The M time text view.
         */
        @BindView(R.id.goods_time)
      TextView mTimeTextView;

        /**
         * Instantiates a new Child holder.
         *
         * @param view the view
         */
        ChildHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * The type Parent holder.
     */
    static class ParentHolder {
        /**
         * The M user photo image view.
         */
        @BindView(R.id.user_photo)
      CircleImageView mUserPhotoImageView;
        /**
         * The M user name text view.
         */
        @BindView(R.id.user_name)
     TextView mUserNameTextView;
        /**
         * The M price text view.
         */
        @BindView(R.id.goods_price)
       TextView mPriceTextView;
        /**
         * The M description text view.
         */
        @BindView(R.id.goods_description)
      TextView mDescriptionTextView;

        /**
         * Instantiates a new Parent holder.
         *
         * @param view the view
         */
        ParentHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}