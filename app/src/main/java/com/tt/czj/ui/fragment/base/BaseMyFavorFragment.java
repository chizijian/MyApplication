package com.tt.czj.ui.fragment.base;

import android.content.Context;

import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;
import com.yhy.tpg.handler.ResultHandler;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by czj on 2017/5/11/0011.
 */

public abstract class BaseMyFavorFragment extends BaseTpgFrament {
    private List<Goods> goodses = new ArrayList<>();//商品

    List<User> users = new ArrayList<>();//商品发布者;

    @Override
    public void reloadDate(Object... args) {
        super.reloadDate(args);
        loadGoodsInfor(getContext());
    }

    /**
     * Load goods infor.
     *
     * @param context the context
     */
    public void loadGoodsInfor(final Context context) {
        goodses.clear();
        users.clear();
        BmobQuery<Goods> query = new BmobQuery<>();
        final User user = BmobUser.getCurrentUser(context, User.class);
        query.addWhereRelatedTo("likes", new BmobPointer(user));
        query.addWhereEqualTo("qiugou",IsQiuGou());
        query.include("user");
        query.findObjects(context, new FindListener<Goods>() {
            @Override
            public void onSuccess(final List<Goods> list) {
                goodses.addAll(list);
                for (Goods g : list
                        ) {
                    users.add(g.getUser());
                }
                parseUser(users,goodses);
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    @Override
    public void parseUser(List<User> users, List<Goods> goodses){
        super.parseUser(users,goodses);
    }

    @Override
    public void HiddenChange() {
        loadGoodsInfor(getContext());
    }

    @Override
    public void initView() {
        mGoodsEmptyTextview.setText("暂时没有收藏商品");
        loadGoodsInfor(getContext());
    }

    @Override
    protected void initData(ResultHandler handler) {
        loadGoodsInfor(getContext());
    }
}
