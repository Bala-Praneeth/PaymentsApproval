package com.example.vitaly.paymentsapproval.other;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private final Executor diskIO;
    private final Executor mainTread;

    public Executor diskIO() {
        return diskIO;
    }

    public Executor mainThread() {
        return mainTread;
    }

    AppExecutors(Executor diskIO, Executor mainTread) {
        this.diskIO = diskIO;
        this.mainTread = mainTread;
    }

    public AppExecutors() {
        this(new DiskIOThreadExecutor(), new MainThreadExecutor());
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable runnable) {
            mainThreadHandler.post(runnable);
        }
    }

    public static class DiskIOThreadExecutor implements Executor {
        private final Executor mDiskIO;

        public DiskIOThreadExecutor() {
            mDiskIO = Executors.newSingleThreadExecutor();
        }

        @Override
        public void execute(@NonNull Runnable runnable) {
            mDiskIO.execute(runnable);
        }
    }
}
