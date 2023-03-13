package com.ithomasoft.architecture.mvp.delegate;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.ParcelableCompat;
import androidx.core.os.ParcelableCompatCreatorCallbacks;

import com.ithomasoft.architecture.MosbySavedState;
import com.ithomasoft.architecture.mvp.viewstate.RestorableParcelableViewState;

/**
 * The SavedState implementation to store the {@link RestorableParcelableViewState} plus mosby view
 * id
 *
 * @author Hannes Dorfmann
 * @since 1.1
 */
public class MosbyViewStateSavedState extends MosbySavedState {

  public static final Parcelable.Creator<MosbyViewStateSavedState> CREATOR =
      ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<MosbyViewStateSavedState>() {
        public MosbyViewStateSavedState createFromParcel(Parcel in, ClassLoader loader) {
          if (loader == null) {
            loader = RestorableParcelableViewState.class.getClassLoader();
          }
          return new MosbyViewStateSavedState(in, loader);
        }

        public MosbyViewStateSavedState[] newArray(int size) {
          return new MosbyViewStateSavedState[size];
        }
      });

  private RestorableParcelableViewState mosbyViewState;

  public MosbyViewStateSavedState(Parcelable superState, @NonNull String viewId,
      @Nullable RestorableParcelableViewState viewState) {
    super(superState, viewId);
    this.mosbyViewState = viewState;
  }

  protected MosbyViewStateSavedState(Parcel in, ClassLoader loader) {
    super(in, loader);
    this.mosbyViewState = in.readParcelable(loader);
  }

  @Override public void writeToParcel(Parcel out, int flags) {
    super.writeToParcel(out, flags);
    out.writeParcelable(mosbyViewState, flags);
  }

  @Nullable
  public RestorableParcelableViewState getRestoreableViewState() {
    return mosbyViewState;
  }
}
