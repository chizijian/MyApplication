package com.tt.czj.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.views.NearByView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * The type Near by presenter.
 */
public class NearByPresenterImpl implements NearByPresenter {
    private NearByView nearByView;

    private final String TAG="NearByPresenterImpl";

    private List<User> users = new ArrayList<>();
    /**
     * The Goods.
     */
    List<Goods> goods = new ArrayList<>();

    /**
     * Instantiates a new Near by presenter.
     *
     * @param personCenterView the person center view
     */
    public NearByPresenterImpl(NearByView personCenterView) {
        this.nearByView = personCenterView;
    }
    @Override
    public void loadGoodsInfor(Context context, String city,boolean qiugou) {
        BmobQuery<Goods> query = new BmobQuery<>();
        //query.addWhereEqualTo("prince",city);
        query.addWhereEqualTo("xiaoqu",city);
        query.addWhereEqualTo("qiugou",qiugou);
        query.addWhereEqualTo("off_shelve",false);
        query.include("user");
        query.order("-createdAt");
        query.findObjects(context, new FindListener<Goods>() {
            @Override
            public void onSuccess(List<Goods> list) {
                Log.e(TAG,list.size()+"Goods");
                users.clear();
                goods.clear();
                for (Goods good:list
                     ) {
                    users.add(good.getUser());
                }
                goods.addAll(list);
               // nearByView.onLoadGoodsInforSuccess(list);
                if(users.size()==goods.size())
                    nearByView.parseUser(users,goods);
                else {
                    Log.e(TAG, "onSuccess: 用户与商品数不符");
                }

            }

            @Override
            public void onError(int i, String s) {
                nearByView.onLoadGoodsError(s);
            }
        });
    }

    @Override
    public void parseGoodsUser(Context context,final List<Goods> goods) {
        final List<User> users = new ArrayList<>();
      //
        for (Goods good:goods) {

            BmobQuery<User> query = new BmobQuery<>();
            query.addWhereEqualTo("objectId",good.getUserid());
            query.findObjects(context, new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {
                    users.addAll(list);
                    Log.e("NearBy",users.size()+"Users");
                    if (users.size() == goods.size()){
                        nearByView.parseUser(users,goods);
                    }
                }
                @Override
                public void onError(int i, String s) {
                    Log.e("nearbyUser","error  "+s);
                }
            });
        }
    }
}
