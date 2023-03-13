package com.ithomasoft.architecture.mvp.viewstate.lce.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.ithomasoft.architecture.mvp.lce.MvpLceView;
import com.ithomasoft.architecture.mvp.viewstate.RestorableViewState;
import com.ithomasoft.architecture.mvp.viewstate.lce.AbsParcelableLceViewState;
import com.ithomasoft.architecture.mvp.viewstate.lce.LceViewState;

/**
 * A {@link LceViewState} and {@link RestorableViewState} that uses Parcelable as content data.
 * Internally it uses {@link Parcel#writeParcelable(Parcelable, int)} and {@link
 * Parcel#readParcelable(ClassLoader)} for serialisation. It uses the default class loader. You can
 * override {@link #getClassLoader()} for provide your own ClassLoader.
 *
 * <p>
 * Can be used for Activites and Fragments.
 * </p>
 *
 * @param <D> the data / model type
 * @param <V> the type of the view
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public class ParcelableDataLceViewState<D extends Parcelable, V extends MvpLceView<D>>
    extends AbsParcelableLceViewState<D, V> {

  public static final Parcelable.Creator<ParcelableDataLceViewState> CREATOR =
      new Parcelable.Creator<ParcelableDataLceViewState>() {
        @Override public ParcelableDataLceViewState createFromParcel(Parcel source) {
          return new ParcelableDataLceViewState(source);
        }

        @Override public ParcelableDataLceViewState[] newArray(int size) {
          return new ParcelableDataLceViewState[size];
        }
      };

  private static final String BUNDLE_PARCELABLE_WORKAROUND =
      "com.hannesdorfmann.mosby.mvp.viewstate.lce.ParcelableLceViewState.workaround";

  public ParcelableDataLceViewState() {
  }

  private ParcelableDataLceViewState(Parcel source) {
    readFromParcel(source);
  }

  @Override public void writeToParcel(Parcel dest, int flags) {

    dest.writeParcelable(loadedData, flags);
    super.writeToParcel(dest, flags);
  }

  @Override protected void readFromParcel(Parcel source) {
    loadedData = source.readParcelable(getClassLoader());
    super.readFromParcel(source);
  }

  /**
   * Get the ClassLoader that is used for deserialization
   */
  protected ClassLoader getClassLoader() {
    return getClass().getClassLoader();
  }
}
