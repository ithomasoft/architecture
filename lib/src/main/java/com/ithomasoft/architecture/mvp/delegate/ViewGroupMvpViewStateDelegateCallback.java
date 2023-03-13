package com.ithomasoft.architecture.mvp.delegate;

import com.ithomasoft.architecture.mvp.MvpPresenter;
import com.ithomasoft.architecture.mvp.MvpView;
import com.ithomasoft.architecture.mvp.viewstate.ViewState;

/**
 * * An enhanced version of {@link MvpDelegateCallback} that adds {@link ViewState} support.
 * This interface must be implemented by all (subclasses of) android.view.View like FrameLayout
 * that want to support {@link ViewState} and mvp.
 *
 * @author Hannes Dorfmann
 * @since 3.0
 */
public interface ViewGroupMvpViewStateDelegateCallback<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>>
    extends MvpViewStateDelegateCallback<V, P, VS>, ViewGroupDelegateCallback<V, P> {
}
