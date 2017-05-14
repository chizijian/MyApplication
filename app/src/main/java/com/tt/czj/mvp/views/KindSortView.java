package com.tt.czj.mvp.views;

import com.tt.czj.mvp.models.KindSort;

import java.util.List;

/**
 * The interface Kind sort view.
 */
public interface KindSortView {
    /**
     * Presen kind sorts.
     *
     * @param kindSorts the kind sorts
     */
    void presenKindSorts(List<KindSort> kindSorts);

    /**
     * Presenter kinds error.
     *
     * @param string the string
     */
    void presenterKindsError(String string);
}
