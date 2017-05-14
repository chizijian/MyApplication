package com.tt.czj.mvp.presenter;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tt.czj.R;
import com.tt.czj.domain.service.WeatherApiService;
import com.tt.czj.mvp.models.Banner;
import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.views.HomeView;
import com.tt.czj.ui.adapter.HomeHorizentalListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.GetListener;

/**
 * The type Home view presenter.
 */
public class HomeViewPresenterImpl implements HomeViewPresenter {
    /**
     * The constant TAG.
     */
    public static final String TAG = "HomeViewPresenterImpl";
    private HomeView homeView;
    /**
     * The Weather api service.
     */
    WeatherApiService weatherApiService;
    private ImageView imageView;
    private HomeHorizentalListViewAdapter homeHorizentalListViewAdapter;
    /**
     * The Titles.
     */
    int[] titles = {R.string.str_latest_post, R.string.str_book, R.string.str_school_message, R.string.str_near_by, R.string.str_house, R.string.str_part_time};
    /**
     * The Ids.
     */
    final int[] ids = {R.mipmap.icon_time, R.mipmap.icon_book, R.mipmap.icon_send_message, R.mipmap.icon_location, R.mipmap.icon_house, R.mipmap.icon_house};

    private List<User> users = new ArrayList<>();
    /**
     * The Goods.
     */
    List<Goods> goods = new ArrayList<>();

    @Override
    public void parseGoodsUser(Context context, final List<Goods> goods) {
        users.clear();
        //final List<User> users = new ArrayList<>();
        this.goods = goods;
        for (int i = 0; i < goods.size(); i++
                ) {
            BmobQuery<User> query = new BmobQuery<User>();
            Log.e(TAG, "parseGoodsUser: " + goods.get(i).getUserid());
            // query.addWhereEqualTo("objectId",goods.get(i).getUserid());
            query.getObject(context, goods.get(i).getUserid(), new GetListener<User>() {
                @Override
                public void onSuccess(User user) {
                    users.add(user);
                    if (users.size() == goods.size()){
                        homeView.parseUser(users,goods);
                    }
                    /*Message message = handler.get().obtainMessage();
                    message.what = 0;
                    //以消息为载体
                    message.obj = user;//这里的list就是查询出list
                    //向handler发送消息
                    handler.get().sendMessage(message);*/

                }

                @Override
                public void onFailure(int i, String s) {

                }
            });

           /* query.findObjects(context, new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {
                    users.addAll(list);
                    if (users.size() == goods.size()){
                        homeView.parseUser(users);
                    }
                }

                @Override
                public void onError(int i, String s) {

                }
            });*/
        }
    }




    @Override
    public void loadGoodsInfor(Context context, final boolean qiugou) {
        users.clear();
        goods.clear();
        BmobQuery<Goods> query = new BmobQuery<Goods>();
        query.order("-createdAt");
        query.addWhereEqualTo("qiugou", qiugou);
        query.addWhereEqualTo("off_shelve",false);
        query.include("user");
        query.findObjects(context, new FindListener<Goods>() {
            @Override
            public void onSuccess(List<Goods> list) {
                Log.e(TAG, "loadGoodsInfor: " + (qiugou ? "求购" : "二手"));
                goods.addAll(list);
                for (Goods good:list
                     ) {
                    users.add(good.getUser());
                }
                //homeView.onLoadGoodsInforSuccess(list);
                homeView.parseUser(users,list);
            }

            @Override
            public void onError(int i, String s) {
                homeView.onLoadGoodsError(s);
            }
        });
    }

    @Override
    public void loadBannerData(final Context context, final List<ImageView> imageViews) {
        BmobQuery<Banner> query = new BmobQuery<Banner>();
        query.findObjects(context, new FindListener<Banner>() {
            @Override
            public void onSuccess(List<Banner> list) {
                for (int i = 0; i < list.size(); i++) {
                    imageView = new ImageView(context);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    Glide.with(context)
                            .load(list.get(i).getFile().getUrl())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imageView);
                    imageViews.add(imageView);
                }
                homeView.showBannerData(imageViews);
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    /**
     * Instantiates a new Home view presenter.
     *
     * @param homeView          the home view
     * @param weatherApiService the weather api service
     */
    public HomeViewPresenterImpl(HomeView homeView, WeatherApiService weatherApiService) {
        this.homeView = homeView;
        this.weatherApiService = weatherApiService;
    }

    private final ThreadLocal<Handler> handler = new ThreadLocal<Handler>() {
        @Override
        protected Handler initialValue() {
            return new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case 0:
                            Log.e(TAG, "handleMessage: " + users.size());
                            users.add((User) msg.obj);
                            if (users.size() == goods.size()) {
                                List<User> list=new ArrayList<>();
                                list.addAll(users);
                                users.clear();
                                homeView.parseUser(list,goods);
                            }
                            break;
                    }
                }
            };
        }
    };
}
