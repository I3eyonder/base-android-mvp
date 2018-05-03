package com.github.hieupt.baseandroidmvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HieuPT on 5/3/2018.
 */
public abstract class BaseActivity<V extends IView, P extends ActivityPresenter<V>> extends AppCompatActivity implements IView {

    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = onCreatePresenter(onCreateView());
        mPresenter.onActivityCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onActivityStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onActivityResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPresenter.onActivityRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onActivityPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onActivityStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onActivityDestroy();
    }

    protected P getPresenter() {
        return mPresenter;
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    protected abstract P onCreatePresenter(V view);

    protected abstract V onCreateView();
}
