package com.ithomasoft.architecture;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.core.os.ParcelableCompat;
import androidx.core.os.ParcelableCompatCreatorCallbacks;
import androidx.customview.view.AbsSavedState;

/**
 * The SavedState implementation to store the view's internal id to
 *
 * @author Hannes Dorfmann
 * @since 3.0
 */
public class MosbySavedState extends AbsSavedState {

  public static final Creator<MosbySavedState> CREATOR =
      ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks<MosbySavedState>() {
        public MosbySavedState createFromParcel(Parcel in, ClassLoader loader) {
          if (loader == null) {
            loader = MosbySavedState.class.getClassLoader();
          }
          return new MosbySavedState(in, loader);
        }

        public MosbySavedState[] newArray(int size) {
          return new MosbySavedState[size];
        }
      });

  private String mosbyViewId;

  public MosbySavedState(Parcelable superState, String mosbyViewId) {
    super(superState);
    this.mosbyViewId = mosbyViewId;
  }

  protected MosbySavedState(Parcel in, ClassLoader loader) {
    super(in, loader);
    this.mosbyViewId = in.readString();
  }

  @Override public void writeToParcel(Parcel out, int flags) {
    super.writeToParcel(out, flags);
    out.writeString(mosbyViewId);
  }

  public String getMosbyViewId() {
    return mosbyViewId;
  }

}
