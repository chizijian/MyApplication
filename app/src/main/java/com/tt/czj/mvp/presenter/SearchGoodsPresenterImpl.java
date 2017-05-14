package com.tt.czj.mvp.presenter;

import android.content.Context;

import com.tt.czj.mvp.models.Goods;
import com.tt.czj.mvp.models.User;
import com.tt.czj.mvp.views.SearchGoodsView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * The type Search goods presenter.
 */
public class SearchGoodsPresenterImpl implements SearchGoodsPresenter {
    /**
     * The constant TAG.
     */
    public static final String TAG = "SearchGoodsPresenterImpl";
    private SearchGoodsView searchGoodsView;

    @Override
    public void queryGoodsBySecondKind(Context context,String secondkind) {
        BmobQuery<Goods> query = new BmobQuery<Goods>();
        query.addWhereEqualTo("secondkind",secondkind);
        query.findObjects(context, new FindListener<Goods>() {
            @Override
            public void onSuccess(List<Goods> list) {
                searchGoodsView.showSecondKindResult(list);
            }

            @Override
            public void onError(int i, String s) {
                searchGoodsView.onLoadGoodsError(s);
            }
        });
    }

    @Override
    public void queryGoodsBYKind(Context context, String kind) {
        BmobQuery<Goods> query = new BmobQuery<Goods>();
        query.addWhereContains("kind",kind);
        query.findObjects(context, new FindListener<Goods>() {
            @Override
            public void onSuccess(List<Goods> list) {
                searchGoodsView.showSecondKindResult(list);
            }

            @Override
            public void onError(int i, String s) {
                searchGoodsView.onLoadGoodsError(s);
            }
        });
    }

    @Override
    public void queryGoodsByKeyWords(Context context,String keyWords) {
        BmobQuery<Goods> eq1 = new BmobQuery<Goods>();
        eq1.addWhereContains("title", keyWords);
        BmobQuery<Goods> eq2 = new BmobQuery<Goods>();
        eq2.addWhereContains("description", keyWords);
        BmobQuery<Goods> eq3 = new BmobQuery<Goods>();
        eq2.addWhereContains("location", keyWords);
        BmobQuery<Goods> eq4=new BmobQuery<>();
        eq4.addWhereContains("kind",keyWords);
        BmobQuery<Goods> eq5=new BmobQuery<>();
        eq5.addWhereEqualTo("secondkind",keyWords);

        List<BmobQuery<Goods>> queries = new ArrayList<BmobQuery<Goods>>();
        queries.add(eq1);
        queries.add(eq2);
        queries.add(eq3);
        queries.add(eq4);
        queries.add(eq5);

        BmobQuery<Goods> mainQuery = new BmobQuery<Goods>();
        mainQuery.or(queries);
        mainQuery.findObjects(context, new FindListener<Goods>() {
            @Override
            public void onSuccess(List<Goods> list) {
                searchGoodsView.showKeyWordsResult(list);
            }

            @Override
            public void onError(int i, String s) {
                searchGoodsView.onLoadGoodsError(s);
            }
        });
    }

    /**
     * Instantiates a new Search goods presenter.
     *
     * @param searchGoodsView the search goods view
     */
    public SearchGoodsPresenterImpl(SearchGoodsView searchGoodsView){
        this.searchGoodsView = searchGoodsView;
    }

    @Override
    public void parseGoodsUser(Context context, final List<Goods> goods) {
        final List<User> users = new ArrayList<>();
        for (int i=0;i<goods.size();i++){
            BmobQuery<User> query = new BmobQuery<User>();
            query.addWhereEndsWith("objectId",goods.get(i).getUserid());
            query.findObjects(context, new FindListener<User>() {
                @Override
                public void onSuccess(List<User> list) {
                    users.addAll(list);
                    if (users.size() == goods.size()){
                        searchGoodsView.parseUser(users);
                    }
                }

                @Override
                public void onError(int i, String s) {

                }
            });
        }
    }
}
