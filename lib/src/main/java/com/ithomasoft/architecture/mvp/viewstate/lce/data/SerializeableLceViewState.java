package com.ithomasoft.architecture.mvp.viewstate.lce.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.ithomasoft.architecture.mvp.lce.MvpLceView;
import com.ithomasoft.architecture.mvp.viewstate.RestorableViewState;
import com.ithomasoft.architecture.mvp.viewstate.lce.AbsParcelableLceViewState;
import com.ithomasoft.architecture.mvp.viewstate.lce.LceViewState;
import java.io.Serializable;

/**
 * A {@link LceViewState} and{@link RestorableViewState} that uses a Serializeable as content data
 * <p>
 * Can be used for Activites and Fragments.
 * </p>
 *
 * @param <D> the data / model type
 * @param <V> the type of the view
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public class SerializeableLceViewState<D extends Serializable, V extends MvpLceView<D>>
    extends AbsParcelableLceViewState<D, V> {

  public static final Parcelable.Creator<SerializeableLceViewState> CREATOR =
      new Parcelable.Creator<SerializeableLceViewState>() {
        @Override public SerializeableLceViewState createFromParcel(Parcel source) {
          return new SerializeableLceViewState(source);
        }

        @Override public SerializeableLceViewState[] newArray(int size) {
          return new SerializeableLceViewState[size];
        }
      };

  public SerializeableLceViewState() {
  }

  private SerializeableLceViewState(Parcel in) {
    readFromParcel(in);
  }

  public void writeToParcel(Parcel dest, int flags) {

    dest.writeSerializable(loadedData);
    super.writeToParcel(dest, flags);
  }

  protected void readFromParcel(Parcel in) {
    loadedData = (D) in.readSerializable();
    super.readFromParcel(in);
  }
}
