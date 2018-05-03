package com.github.hieupt.baseandroidmvp;

/**
 * Created by HieuPT on 5/3/2018.
 */
public abstract class ActivityPresenter<V extends IView> extends BasePresenter<V> {

    public ActivityPresenter(V view) {
        super(view);
    }

    protected void onActivityCreate() {

    }

    protected void onActivityStart() {

    }

    protected void onActivityResume() {

    }

    protected void onActivityRestart() {

    }

    protected void onActivityPause() {

    }

    protected void onActivityStop() {

    }

    protected void onActivityDestroy() {

    }

}
