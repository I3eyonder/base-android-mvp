package com.github.hieupt.baseandroidmvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HieuPT on 5/3/2018.
 */
public abstract class BaseFragment<V extends IView, P extends FragmentPresenter<V>> extends Fragment implements IView {

    private P mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = onCreatePresenter(onCreateView());
        mPresenter.onFragmentAttach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onFragmentCreate();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter.onFragmentCreateView();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.onFragmentViewCreated();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.onActivityCreated();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onFragmentStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onFragmentResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onFragmentPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onFragmentStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onFragmentDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onFragmentDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.onFragmentDetach();
    }

    protected P getPresenter() {
        return mPresenter;
    }

    protected abstract P onCreatePresenter(V view);

    protected abstract V onCreateView();

    @Override
    public Context getViewContext() {
        return getContext();
    }
}
