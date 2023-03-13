package com.ithomasoft.architecture.mvp.viewstate.lce;

import android.os.Bundle;
import android.view.View;
import com.ithomasoft.architecture.mvp.MvpPresenter;
import com.ithomasoft.architecture.mvp.delegate.FragmentMvpDelegate;
import com.ithomasoft.architecture.mvp.delegate.FragmentMvpViewStateDelegateImpl;
import com.ithomasoft.architecture.mvp.delegate.MvpViewStateDelegateCallback;
import com.ithomasoft.architecture.mvp.lce.MvpLceFragment;
import com.ithomasoft.architecture.mvp.lce.MvpLceView;
import com.ithomasoft.architecture.mvp.viewstate.ViewState;

/**
 * A {@link MvpLceFragment} with {@link ViewState} support.
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public abstract class MvpLceViewStateFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
    extends MvpLceFragment<CV, M, V, P>
    implements MvpLceView<M>, MvpViewStateDelegateCallback<V, P, LceViewState<M, V>> {

  /**
   * The viewstate will be instantiated by calling {@link #createViewState()} in {@link
   * #onViewCreated(View, Bundle)}. Don't instantiate it by hand.
   */
  protected LceViewState<M, V> viewState;

  /**
   * A flag that indicates if the viewstate tires to restore the view right now.
   */
  private boolean restoringViewState = false;

  @Override protected FragmentMvpDelegate<V, P> getMvpDelegate() {
    if (mvpDelegate == null) {
      mvpDelegate = new FragmentMvpViewStateDelegateImpl<>(this, this, true, true);
    }

    return mvpDelegate;
  }

  @Override public LceViewState<M, V> getViewState() {
    return viewState;
  }

  @Override public void setViewState(LceViewState<M, V> viewState) {
    this.viewState = viewState;
  }

  @Override public void showContent() {
    super.showContent();
    viewState.setStateShowContent(getData());
  }

  @Override public void showError(Throwable e, boolean pullToRefresh) {
    super.showError(e, pullToRefresh);
    viewState.setStateShowError(e, pullToRefresh);
  }

  @Override public void showLoading(boolean pullToRefresh) {
    super.showLoading(pullToRefresh);
    viewState.setStateShowLoading(pullToRefresh);
  }

  @Override public void setRestoringViewState(boolean restoringViewState) {
    this.restoringViewState = restoringViewState;
  }

  @Override public boolean isRestoringViewState() {
    return restoringViewState;
  }

  @Override public void onViewStateInstanceRestored(boolean instanceStateRetainedInMemory) {
    if (!instanceStateRetainedInMemory && viewState.isLoadingState()) {
      loadData(viewState.isPullToRefreshLoadingState());
    }
  }

  @Override public void onNewViewStateInstance() {
    loadData(false);
  }

  @Override protected void showLightError(String msg) {
    if (isRestoringViewState()) {
      return; // Do not display toast again while restoring viewstate
    }

    super.showLightError(msg);
  }

  /**
   * Get the data that has been set before in {@link #setData(Object)}
   * <p>
   * <b>It's necessary to return the same data as set before to ensure that {@link ViewState} works
   * correctly</b>
   * </p>
   *
   * @return The data
   */
  public abstract M getData();
}
