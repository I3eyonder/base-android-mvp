package com.github.hieupt.baseandroidmvp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HieuPT on 5/3/2018.
 */
public abstract class BaseModel {

    protected interface IWorkerTask<T> {

        T doWork();

        void onFinish(T result);
    }

    private interface IWorkerTaskFinishListener {

        void onFinished(WorkerTask<?> workerTask);
    }

    private static final String TAG = "BaseModel";

    private final Context mContext;

    private final List<WorkerTask<?>> mWorkerTaskList;

    public BaseModel(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null");
        }
        mContext = context;
        mWorkerTaskList = new ArrayList<>();
    }

    protected <T> void runOnWorkerThread(IWorkerTask<T> action) {
        WorkerTask<T> workerTask = new WorkerTask<>(action, new IWorkerTaskFinishListener() {

            @Override
            public void onFinished(WorkerTask<?> workerTask) {
                mWorkerTaskList.remove(workerTask);
            }
        });
        mWorkerTaskList.add(workerTask);
        workerTask.execute();
    }

    public void resumeWorkerTask() {
        if (!mWorkerTaskList.isEmpty()) {
            Log.d(TAG, "#resumeWorkerTask");
            WorkerTask<?> tempTask;
            for (int i = 0; i < mWorkerTaskList.size(); i++) {
                tempTask = mWorkerTaskList.get(i);
                if (tempTask != null) {
                    tempTask.cancel(true);
                    mWorkerTaskList.set(i, new WorkerTask<>(tempTask.mAction, tempTask.mFinishListener));
                }
            }
            for (WorkerTask<?> workerTask : mWorkerTaskList) {
                if (workerTask != null) {
                    workerTask.execute();
                }
            }
        }
    }

    public void stopWorkerTask() {
        if (!mWorkerTaskList.isEmpty()) {
            Log.d(TAG, "#stopWorkerTask");
            for (WorkerTask<?> workerTask : mWorkerTaskList) {
                if (workerTask != null) {
                    workerTask.cancel(true);
                }
            }
        }
    }

    public void release() {
        stopWorkerTask();
        mWorkerTaskList.clear();
    }

    public Context getContext() {
        return mContext;
    }

    private static final class WorkerTask<Result> extends AsyncTask<Void, Void, Result> {

        private final IWorkerTask<Result> mAction;

        private final IWorkerTaskFinishListener mFinishListener;

        private WorkerTask(IWorkerTask<Result> action, IWorkerTaskFinishListener listener) {
            mAction = action;
            mFinishListener = listener;
        }

        @Override
        protected Result doInBackground(Void... params) {
            if (mAction != null) {
                return mAction.doWork();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Result result) {
            if (mAction != null) {
                mAction.onFinish(result);
            }
            if (mFinishListener != null) {
                mFinishListener.onFinished(this);
            }
        }
    }

}
