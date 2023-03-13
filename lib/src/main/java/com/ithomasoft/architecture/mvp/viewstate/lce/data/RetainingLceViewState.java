package com.ithomasoft.architecture.mvp.viewstate.lce.data;

import com.ithomasoft.architecture.mvp.lce.MvpLceView;
import com.ithomasoft.architecture.mvp.viewstate.lce.AbsLceViewState;
import com.ithomasoft.architecture.mvp.viewstate.lce.LceViewState;

/**
 * This allows one to store / restore any kind of data along
 * orientation changes. So this ViewState will not be saved into the Bundle of saveInstanceState().
 * This ViewState will be stored and restored directly by the Activity or Fragment itself.
 *
 * @param <D> the data / model type
 * @param <V> the type of the view
 * @author Hannes Dorfmann
 * @since 2.0.0
 */
public class RetainingLceViewState<D, V extends MvpLceView<D>> extends AbsLceViewState<D, V> {

}
