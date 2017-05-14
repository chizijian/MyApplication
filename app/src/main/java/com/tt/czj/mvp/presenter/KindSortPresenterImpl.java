package com.tt.czj.mvp.presenter;

import android.content.Context;

import com.tt.czj.mvp.models.KindSort;
import com.tt.czj.mvp.views.KindSortView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * The type Kind sort presenter.
 */
public class KindSortPresenterImpl implements KindSortPresenter {
    private KindSortView kindSortView;

    /**
     * Instantiates a new Kind sort presenter.
     *
     * @param kindSortView the kind sort view
     */
    public KindSortPresenterImpl(KindSortView kindSortView){
        this.kindSortView = kindSortView;
    }

    @Override
    public void loadKindSortData(final Context context) {
        BmobQuery<KindSort> query = new BmobQuery<KindSort>();
        query.findObjects(context, new FindListener<KindSort>() {
            @Override
            public void onSuccess(List<KindSort> list) {
                kindSortView.presenKindSorts(list);
            }

            @Override
            public void onError(int i, String s) {
                kindSortView.presenterKindsError(s);
            }
        });
    }
}
