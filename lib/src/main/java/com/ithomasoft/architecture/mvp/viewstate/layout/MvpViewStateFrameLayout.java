package com.ithomasoft.architecture.mvp.viewstate.layout;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import com.ithomasoft.architecture.mvp.MvpPresenter;
import com.ithomasoft.architecture.mvp.MvpView;
import com.ithomasoft.architecture.mvp.delegate.ViewGroupMvpDelegate;
import com.ithomasoft.architecture.mvp.delegate.ViewGroupMvpViewStateDelegateImpl;
import com.ithomasoft.architecture.mvp.delegate.ViewGroupMvpViewStateDelegateCallback;
import com.ithomasoft.architecture.mvp.layout.MvpFrameLayout;
import com.ithomasoft.architecture.mvp.viewstate.ViewState;

/**
 * A {@link MvpFrameLayout} with ViewState support.
 *
 * @author Hannes Dorfmann
 * @since 1.1
 */
public abstract class MvpViewStateFrameLayout<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>>
    extends MvpFrameLayout<V, P> implements ViewGroupMvpViewStateDelegateCallback<V, P, VS> {

  private boolean restoringViewState = false;
  protected VS viewState;

  public MvpViewStateFrameLayout(Context context) {
    super(context);
  }

  public MvpViewStateFrameLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MvpViewStateFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @TargetApi(21)
  public MvpViewStateFrameLayout(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected ViewGroupMvpDelegate<V, P> getMvpDelegate() {
    if (mvpDelegate == null) {
      mvpDelegate = new ViewGroupMvpViewStateDelegateImpl<>(this, this, true);
    }

    return mvpDelegate;
  }

  @Override public VS getViewState() {
    return viewState;
  }

  @Override public void setViewState(VS viewState) {
    this.viewState =  viewState;
  }

  @Override public void setRestoringViewState(boolean retstoringViewState) {
    this.restoringViewState = retstoringViewState;
  }

  @Override public boolean isRestoringViewState() {
    return restoringViewState;
  }

  @Override public void onViewStateInstanceRestored(boolean instanceStateRetained) {
    // can be overridden in subclass
  }

}
