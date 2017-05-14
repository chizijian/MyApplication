package com.tt.czj.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.tt.czj.mvp.models.Kind;
import com.tt.czj.mvp.models.SecondKind;
import com.tt.czj.mvp.views.AllKindViews;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * The type Kind presenter.
 */
public class KindPresenterImpl implements KindPresenter {
    /**
     * The constant TAG.
     */
    public static final String TAG = "KindPresenterImpl";
    private AllKindViews allKindViews;

    /**
     * Instantiates a new Kind presenter.
     *
     * @param allKindViews the all kind views
     */
    public KindPresenterImpl(AllKindViews allKindViews){
        this.allKindViews = allKindViews;
    }

    @Override
    public void loadKind(Context context) {
        BmobQuery<Kind> query = new BmobQuery<Kind>();
        query.findObjects(context, new FindListener<Kind>() {
            @Override
            public void onSuccess(List<Kind> list) {
                allKindViews.showKind(list);
            }

            @Override
            public void onError(int i, String s) {
                allKindViews.loadFailed(s);
            }
        });
    }

    @Override
    public void loadSecondKind(Context context,Kind kind) {
        BmobQuery<SecondKind> query = new BmobQuery<SecondKind>();
        query.addWhereEqualTo("parentid",kind.getObjectId());
        query.findObjects(context, new FindListener<SecondKind>() {
            @Override
            public void onSuccess(List<SecondKind> list) {
                if (list.isEmpty()){
                    Log.e("secondkind:","空");
                }
                for (SecondKind s:list
                        ) {
                    Log.e("secondkind:",s.getKind());
                }
                allKindViews.showSecondKind(list);
            }

            @Override
            public void onError(int i, String s) {
                Log.e("error:","未查询到数据");
                allKindViews.loadFailed(s);
            }
        });
    }
}
