package com.ithomasoft.architecture.mvp.viewstate;

import android.os.Bundle;
import android.view.View;
import com.ithomasoft.architecture.mvp.MvpFragment;
import com.ithomasoft.architecture.mvp.MvpPresenter;
import com.ithomasoft.architecture.mvp.MvpView;
import com.ithomasoft.architecture.mvp.delegate.FragmentMvpDelegate;
import com.ithomasoft.architecture.mvp.delegate.FragmentMvpViewStateDelegateImpl;
import com.ithomasoft.architecture.mvp.delegate.MvpViewStateDelegateCallback;

/**
 * This is a enhancement of {@link com.ithomasoft.architecture.mvp.MvpFragment} that introduces the
 * support of {@link com.ithomasoft.architecture.mvp.viewstate.ViewState}.
 * <p>
 * You can change the behaviour of what to do if the viewstate is empty (usually if the fragment
 * creates the viewState for the very first time and therefore has no state / data to restore) by
 * overriding {@link #onNewViewStateInstance()}
 * </p>
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public abstract class MvpViewStateFragment<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>>
    extends MvpFragment<V, P> implements MvpViewStateDelegateCallback<V, P, VS> {

  /**
   * The viewstate will be instantiated by calling {@link #createViewState()} in {@link
   * #onViewCreated(View, Bundle)}. Don't instantiate it by hand.
   */
  protected VS viewState;

  /**
   * A simple flag that indicates if the restoring ViewState  is in progress right now.
   */
  private boolean restoringViewState = false;

  @Override protected FragmentMvpDelegate<V, P> getMvpDelegate() {
    if (mvpDelegate == null) {
      mvpDelegate = new FragmentMvpViewStateDelegateImpl<>(this, this, true, true);
    }

    return mvpDelegate;
  }

  @Override public VS getViewState() {
    return viewState;
  }

  @Override public void setViewState(VS viewState) {
    this.viewState = viewState;
  }

  @Override public void setRestoringViewState(boolean restoringViewState) {
    this.restoringViewState = restoringViewState;
  }

  @Override public boolean isRestoringViewState() {
    return restoringViewState;
  }

  @Override public void onViewStateInstanceRestored(boolean instanceStateRetained) {
    // not needed. You could override this is subclasses if needed
  }
}
