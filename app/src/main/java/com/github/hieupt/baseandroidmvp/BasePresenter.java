package com.github.hieupt.baseandroidmvp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HieuPT on 5/3/2018.
 */
public abstract class BasePresenter<V extends IView> {

    private boolean mIsViewAttached;

    private final V mView;

    private final Map<Class<? extends BaseModel>, ModelWrapper<?>> mModelMap = new HashMap<>();

    public BasePresenter(V view) {
        if (view == null) {
            throw new IllegalArgumentException("View must not be null");
        }
        this.mView = view;
    }

    public final boolean isViewAttached() {
        return mIsViewAttached;
    }

    public final void onViewAttached() {
        mIsViewAttached = true;
    }

    public final void onViewDetached() {
        mIsViewAttached = false;
    }

    protected final <M extends BaseModel> void registerModel(M model) {
        if (model == null) {
            throw new IllegalArgumentException("Model must not be null");
        }
        mModelMap.put(model.getClass(), new ModelWrapper<>(model));
    }

    @SuppressWarnings("unchecked")
    protected final <M extends BaseModel> M getModel(Class<M> clazz) {
        ModelWrapper<M> modelWrapper = (ModelWrapper<M>) mModelMap.get(clazz);
        if (modelWrapper != null) {
            return modelWrapper.mModel;
        } else {
            throw new IllegalArgumentException("This model type has not been registered");
        }
    }

    @SuppressWarnings("unchecked")
    protected final List<? extends BaseModel> getModels() {
        List<ModelWrapper<?>> modelWrappers = new ArrayList<>(mModelMap.values());
        List modelList = new ArrayList<>();
        for (ModelWrapper<?> modelWrapper : modelWrappers) {
            modelList.add(modelWrapper.mModel);
        }
        return modelList;
    }

    protected final <M extends BaseModel> void unregisterModel(Class<M> clazz) {
        if (clazz != null) {
            mModelMap.remove(clazz);
        }
    }

    protected final void unregisterAllModel() {
        mModelMap.clear();
    }

    protected final V getView() {
        return mView;
    }

    private static class ModelWrapper<M extends BaseModel> {

        private final M mModel;

        private ModelWrapper(M model) {
            mModel = model;
        }
    }
}
