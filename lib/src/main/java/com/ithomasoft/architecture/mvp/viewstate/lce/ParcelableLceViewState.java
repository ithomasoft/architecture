package com.ithomasoft.architecture.mvp.viewstate.lce;

import com.ithomasoft.architecture.mvp.lce.MvpLceActivity;
import com.ithomasoft.architecture.mvp.lce.MvpLceView;
import com.ithomasoft.architecture.mvp.viewstate.RestorableParcelableViewState;

/**
 * A common interface for {@link LceViewState} and {@link RestorableParcelableViewState}.
 * This one is used for {@link MvpLceActivity}
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public interface ParcelableLceViewState<D, V extends MvpLceView<D>>
    extends RestorableParcelableViewState<V>, LceViewState<D, V> {
}
