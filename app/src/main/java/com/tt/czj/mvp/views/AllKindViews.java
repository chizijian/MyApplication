package com.tt.czj.mvp.views;

import com.tt.czj.mvp.models.Kind;
import com.tt.czj.mvp.models.SecondKind;

import java.util.List;

/**
 * The interface All kind views.
 */
public interface AllKindViews {
    /**
     * The constant TAG.
     */
    public static final String TAG = "AllKindViews";

    /**
     * Show kind.
     *
     * @param kinds the kinds
     */
    void showKind(List<Kind> kinds);

    /**
     * Show second kind.
     *
     * @param secondKinds the second kinds
     */
    void showSecondKind(List<SecondKind> secondKinds);

    /**
     * Load failed.
     *
     * @param str the str
     */
    void loadFailed(String str);
}
