package com.ithomasoft.architecture.mvp.viewstate.lce.data;

import com.ithomasoft.architecture.mvp.lce.MvpLceView;
import com.ithomasoft.architecture.mvp.viewstate.RestorableViewState;
import com.ithomasoft.architecture.mvp.viewstate.lce.AbsParcelableLceViewState;
import com.ithomasoft.architecture.mvp.viewstate.lce.LceViewState;

/**
 * If you really have good reasons you could have <i>Void</i> as content type in a LCE
 * (Loading-Content-Error) View. This is the corresponding {@link LceViewState} and{@link
 * RestorableViewState}
 * <p>
 * Can be used for Activites and Fragments.
 * </p>
 *
 * @param <V> The type of the view
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
@SuppressWarnings("ParcelCreator")
public class VoidViewState<V extends MvpLceView<Void>> extends AbsParcelableLceViewState<Void, V> {
}
