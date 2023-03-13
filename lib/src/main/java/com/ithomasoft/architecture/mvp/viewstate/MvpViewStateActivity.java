package com.ithomasoft.architecture.mvp.viewstate;

import com.ithomasoft.architecture.mvp.MvpActivity;
import com.ithomasoft.architecture.mvp.MvpPresenter;
import com.ithomasoft.architecture.mvp.MvpView;
import com.ithomasoft.architecture.mvp.delegate.ActivityMvpDelegate;
import com.ithomasoft.architecture.mvp.delegate.ActivityMvpViewStateDelegateImpl;
import com.ithomasoft.architecture.mvp.delegate.MvpViewStateDelegateCallback;

/**
 * This is a enhancement of {@link com.ithomasoft.architecture.mvp.MvpActivity} that introduces the
 * support of {@link RestorableViewState}.
 * <p>
 * You can change the behaviour of what to do if the viewstate is empty (usually if the activity
 * creates the viewState for the very first time and therefore has no state / data to restore) by
 * overriding {@link #onNewViewStateInstance()}
 * </p>
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public abstract class MvpViewStateActivity<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>>
    extends MvpActivity<V, P> implements MvpViewStateDelegateCallback<V, P, VS> {

  protected VS viewState;

  /**
   * A simple flag that indicates if the restoring ViewState  is in progress right now.
   */
  protected boolean restoringViewState = false;

  @Override protected ActivityMvpDelegate<V, P> getMvpDelegate() {
    if (mvpDelegate == null) {
      mvpDelegate = new ActivityMvpViewStateDelegateImpl<>(this, this, true);
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
