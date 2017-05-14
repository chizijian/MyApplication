package com.tt.czj.mvp.presenter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.tt.czj.mvp.views.PersonCenterView;

/**
 * The type Person center fragment presenter.
 */
public class PersonCenterFragmentPresenterImpl implements PersonCenterFragmentPresenter {
    private PersonCenterView personCenterView;

    /**
     * Instantiates a new Person center fragment presenter.
     *
     * @param personCenterView the person center view
     */
    public PersonCenterFragmentPresenterImpl(PersonCenterView personCenterView) {
        this.personCenterView = personCenterView;
    }

    @Override
    public void loadPhoto(final Context context, String url) {
        Glide.with(context).load(url).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                personCenterView.showPhoto(resource);
            }
        });
    }
}
