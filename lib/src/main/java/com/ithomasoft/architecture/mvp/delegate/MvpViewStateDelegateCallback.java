package com.ithomasoft.architecture.mvp.delegate;

import com.ithomasoft.architecture.mvp.MvpPresenter;
import com.ithomasoft.architecture.mvp.MvpView;
import com.ithomasoft.architecture.mvp.viewstate.ViewState;

/**
 * An enhanced version of {@link MvpDelegateCallback} that adds {@link ViewState} support.
 * This interface must be implemented by all (subclasses of) Activity and Fragment
 * that want to support {@link
 * ViewState} and mvp.
 *
 * @author Hannes Dorfmann
 * @see ViewGroupMvpViewStateDelegateCallback
 * @since 1.0.0
 */
public interface MvpViewStateDelegateCallback<V extends MvpView, P extends MvpPresenter<V>, VS extends ViewState<V>>
    extends MvpDelegateCallback<V, P> {

  /**
   * Get the viewState
   */
  VS getViewState();

  /**
   * Set the viewstate. <b>Should only be called by {@link MvpViewStateInternalDelegate}</b>
   */
  void setViewState(VS viewState);

  /**
   * Create the viewstate.
   */
  @NonNull VS createViewState();

  /**
   * This method will be called by {@link MvpViewStateInternalDelegate} to inform that restoring
   * the
   * view state
   * is in progress.
   *
   * @param restoringViewState true, if restoring viewstate is in progress, otherwise false
   */
  void setRestoringViewState(boolean restoringViewState);

  /**
   * @return true if the viewstate is restoring right now (not finished yet). Otherwise false.
   */
  boolean isRestoringViewState();

  /**
   * Called if the {@link ViewState} instance has been restored successfully.
   * <p>
   * In this method you have to restore the viewstate by reading the view state properties and
   * setup
   * the view to be on the same state as before.
   * </p>
   *
   * @param instanceStateRetained true, if the viewstate has been restored from memory, otherwise
   * false (viewstate has been restored from Bundle)
   */
  void onViewStateInstanceRestored(boolean instanceStateRetained);

  /**
   * Called if a new {@link ViewState} has been created because no viewstate from a previous
   * Activity or Fragment instance could be restored.
   * <p><b>Typically this is called on the first time the <i>Activity</i> or <i>Fragment</i> starts
   * and therefore no view state instance previously exists</b></p>
   */
  void onNewViewStateInstance();
}
