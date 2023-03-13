package com.ithomasoft.architecture.mvp.viewstate.lce;

import com.ithomasoft.architecture.mvp.lce.MvpLceView;

/**
 * A base view state implementation for {@link LceViewState} (Loading-Content-Error)
 *
 * @param <D> the data / model type
 * @param <V> the type of the view
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public abstract class AbsLceViewState<D, V extends MvpLceView<D>> implements LceViewState<D, V> {

  /**
   * The current viewstate. Used to identify if the view is/was showing loading, error, or content.
   */
  protected int currentViewState;
  protected boolean pullToRefresh;
  protected Throwable exception;
  protected D loadedData;

  @Override public void setStateShowContent(D loadedData) {

    currentViewState = STATE_SHOW_CONTENT;
    this.loadedData = loadedData;
    exception = null;
  }

  @Override public void setStateShowError(Throwable e, boolean pullToRefresh) {
    currentViewState = STATE_SHOW_ERROR;
    exception = e;
    this.pullToRefresh = pullToRefresh;
    if (!pullToRefresh) {
      loadedData = null;
    }
    // else, don't clear loaded data, because of pull to refresh where previous data may
    // be displayed while showing error
  }

  @Override public void setStateShowLoading(boolean pullToRefresh) {
    currentViewState = STATE_SHOW_LOADING;
    this.pullToRefresh = pullToRefresh;
    exception = null;

    if (!pullToRefresh) {
      loadedData = null;
    }
    // else, don't clear loaded data, because of pull to refresh where previous data
    // may be displayed while showing error
  }

  @Override public void apply(V view, boolean retained) {

    if (currentViewState == STATE_SHOW_CONTENT) {
      view.setData(loadedData);
      view.showContent();
    } else if (currentViewState == STATE_SHOW_LOADING) {

      boolean ptr = pullToRefresh;
      if (pullToRefresh) {
        view.setData(loadedData);
        view.showContent();
      }

      view.showLoading(ptr);
    } else if (currentViewState == STATE_SHOW_ERROR) {

      boolean ptr = pullToRefresh;
      Throwable e = exception;
      if (pullToRefresh) {
        view.setData(loadedData);
        view.showContent();
      }
      view.showError(e, ptr);
    }
  }

  @Override public boolean isLoadingState() {
    return STATE_SHOW_LOADING == currentViewState;
  }

  @Override public boolean isPullToRefreshLoadingState() {
    return isLoadingState() && pullToRefresh;
  }
}
