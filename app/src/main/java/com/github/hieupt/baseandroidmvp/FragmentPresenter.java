package com.github.hieupt.baseandroidmvp;

/**
 * Created by HieuPT on 5/3/2018.
 */
public abstract class FragmentPresenter<V extends IView> extends BasePresenter<V> {

    public FragmentPresenter(V view) {
        super(view);
    }

    protected void onFragmentAttach() {
    }

    protected void onFragmentCreate() {
    }

    protected void onFragmentCreateView() {
    }

    protected void onFragmentViewCreated() {
    }

    protected void onActivityCreated() {
    }

    protected void onFragmentStart() {
    }

    protected void onFragmentResume() {
    }

    protected void onFragmentPause() {
    }

    protected void onFragmentStop() {
    }

    protected void onFragmentDestroyView() {
    }

    protected void onFragmentDestroy() {
    }

    protected void onFragmentDetach() {
    }
}
