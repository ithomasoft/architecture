package com.ithomasoft.architecture.mvp.lce;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ithomasoft.architecture.R;
import com.ithomasoft.architecture.mvp.MvpFragment;
import com.ithomasoft.architecture.mvp.MvpPresenter;

/**
 * A {@link MvpFragment} that implements {@link MvpLceView} which gives you 3 options:
 * <ul>
 * <li>Display a loading view: A view with <b>R.id.loadingView</b> must be specified in your
 * inflated xml layout</li>
 * <li>Display a error view: A <b>TextView</b> with <b>R.id.errorView</b> must be declared in your
 * inflated xml layout</li>
 * <li>Display content view: A view with <b>R.id.contentView</b> must be specified in your
 * inflated
 * xml layout</li>
 * </ul>
 *
 * @param <CV> The type of the content view with the id = R.id.contentView. Can be any kind of
 *             android view widget like ListView, RecyclerView, ScrollView or a simple layout like Framelayout
 *             etc. (everything that extends from android.view.View)
 * @param <M>  The underlying data model that will be displayed with this view
 * @param <V>  The View interface that must be implemented by this view. You can use {@link
 *             MvpLceView}, but if you want to add more methods you have to provide your own view interface
 *             that
 *             extends from {@link MvpLceView}
 * @param <P>  The type of the Presenter. Must extend from {@link MvpPresenter}
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public abstract class MvpLceFragment<CV extends View, M, V extends MvpLceView<M>, P extends MvpPresenter<V>>
        extends MvpFragment<V, P> implements MvpLceView<M> {

    protected View loadingView;
    protected CV contentView;
    protected TextView errorView;

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadingView = createLoadingView(view);
        contentView = createContentView(view);
        errorView = createErrorView(view);

        if (loadingView == null) {
            throw new NullPointerException(
                    "Loading view is null! Have you specified a loading view in your layout xml file?"
                            + " You have to give your loading View the id R.id.loadingView");
        }

        if (contentView == null) {
            throw new NullPointerException(
                    "Content view is null! Have you specified a content view in your layout xml file?"
                            + " You have to give your content View the id R.id.contentView");
        }

        if (errorView == null) {
            throw new NullPointerException(
                    "Error view is null! Have you specified a content view in your layout xml file?"
                            + " You have to give your error View the id R.id.errorView");
        }

        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onErrorViewClicked();
            }
        });
    }

    /**
     * Create the loading view. Default is {@code findViewById(R.id.loadingView)}
     *
     * @param view The main view returned from {@link #onCreateView(LayoutInflater, ViewGroup, * Bundle)}
     * @return the loading view
     */
    @NonNull
    protected View createLoadingView(View view) {
        return view.findViewById(R.id.loadingView);
    }

    /**
     * Create the content view. Default is {@code findViewById(R.id.contentView)}
     *
     * @param view The main view returned from {@link #onCreateView(LayoutInflater, ViewGroup, *
     *             Bundle)}
     * @return the content view
     */
    @NonNull
    protected CV createContentView(View view) {
        return (CV) view.findViewById(R.id.contentView);
    }

    /**
     * Create the error view. Default is {@code findViewById(R.id.errorView)}
     *
     * @param view The main view returned from {@link #onCreateView(LayoutInflater, ViewGroup, *
     *             Bundle)}
     * @return the error view
     */
    @NonNull
    protected TextView createErrorView(View view) {
        return (TextView) view.findViewById(R.id.errorView);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {

        if (!pullToRefresh) {
            animateLoadingViewIn();
        }

        // otherwise the pull to refresh widget will already display a loading animation
    }

    /**
     * Override this method if you want to provide your own animation for showing the loading view
     */
    protected void animateLoadingViewIn() {
        LceAnimator.showLoading(loadingView, contentView, errorView);
    }

    @Override
    public void showContent() {
        animateContentViewIn();
    }

    /**
     * Called to animate from loading view to content view
     */
    protected void animateContentViewIn() {
        LceAnimator.showContent(loadingView, contentView, errorView);
    }

    /**
     * Get the error message for a certain Exception that will be shown on {@link
     * #showError(Throwable, boolean)}
     */
    protected abstract String getErrorMessage(Throwable e, boolean pullToRefresh);

    /**
     * The default behaviour is to display a toast message as light error (i.e. pull-to-refresh
     * error).
     * Override this method if you want to display the light error in another way (like crouton).
     */
    protected void showLightError(String msg) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Called if the error view has been clicked. To disable clicking on the errorView use
     * <code>errorView.setClickable(false)</code>
     */
    protected void onErrorViewClicked() {
        loadData(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {

        String errorMsg = getErrorMessage(e, pullToRefresh);

        if (pullToRefresh) {
            showLightError(errorMsg);
        } else {
            errorView.setText(errorMsg);
            animateErrorViewIn();
        }
    }

    /**
     * Animates the error view in (instead of displaying content view / loading view)
     */
    protected void animateErrorViewIn() {
        LceAnimator.showErrorView(loadingView, contentView, errorView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loadingView = null;
        contentView = null;
        errorView.setOnClickListener(null);
        errorView = null;
    }
}
