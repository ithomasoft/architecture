package com.ithomasoft.architecture.mvp.viewstate.layout;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import com.ithomasoft.architecture.mvp.MvpPresenter;
import com.ithomasoft.architecture.mvp.MvpView;
import com.ithomasoft.architecture.mvp.delegate.ViewGroupMvpDelegate;
import com.ithomasoft.architecture.mvp.delegate.ViewGroupMvpViewStateDelegateImpl;
import com.ithomasoft.architecture.mvp.delegate.ViewGroupMvpViewStateDelegateCallback;
import com.ithomasoft.architecture.mvp.layout.MvpRelativeLayout;
import com.ithomasoft.architecture.mvp.viewstate.ViewState;

/**
 * A {@link MvpRelativeLayout} with ViewState support
 *
 * @author Hannes Dorfmann
 * @since 1.1.0
 */
public abstract class MvpViewStateRelativeLayout<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>>
    extends MvpRelativeLayout<V, P> implements ViewGroupMvpViewStateDelegateCallback<V, P, VS> {

  private boolean restoringViewState = false;
  protected VS viewState;

  public MvpViewStateRelativeLayout(Context context) {
    super(context);
  }

  public MvpViewStateRelativeLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MvpViewStateRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @TargetApi(21)
  public MvpViewStateRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr,
      int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @Override
  protected ViewGroupMvpDelegate<V, P> getMvpDelegate() {
    if (mvpDelegate == null) {
      mvpDelegate = new ViewGroupMvpViewStateDelegateImpl<V, P, VS>(this, this, true);
    }

    return mvpDelegate;
  }

  @Override public VS getViewState() {
    return viewState;
  }

  @Override public void setViewState(VS viewState) {
    this.viewState = viewState;
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
