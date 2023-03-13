package com.ithomasoft.architecture.mvp.viewstate;

import android.os.Parcelable;
import com.ithomasoft.architecture.mvp.MvpView;

/**
 * A {@link RestorableViewState} that is implements {@link Parcelable} interface so that it can be
 * put directly in a bundle (as parcelable)
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public interface RestorableParcelableViewState<V extends MvpView>
    extends RestorableViewState<V>, Parcelable {
}
