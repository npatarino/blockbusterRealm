package io.keepcoding.blockbusterrealm;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import com.idealista.android.elves.tasks.AndroidExecutors;
import java.util.concurrent.Executor;
import org.jetbrains.annotations.NotNull;

public class MoviesApplication extends Application {

    @Override public void onCreate() {
        super.onCreate();
        AndroidExecutors.UIThreadExecutor.init(executor);
    }

    protected Executor executor = new Executor() {
        private final Handler threadHandlerExecutor = new Handler(Looper.getMainLooper());

        @Override public void execute(@NotNull Runnable runnable) {
            threadHandlerExecutor.post(runnable);
        }
    };

}
