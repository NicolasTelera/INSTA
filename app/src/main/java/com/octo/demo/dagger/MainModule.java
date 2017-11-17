package com.octo.demo.dagger;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    final static String BACKGROUND = "background";
    final static String FRONT = "front";

    @Provides
    @Singleton
    @Named(BACKGROUND)
    Executor providesBackgroundExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    @Named(FRONT)
    Executor providesFrontExecutor() {
        return new HandlerExecutor(new Handler(Looper.getMainLooper()));
    }

    class HandlerExecutor implements Executor {

        private Handler handler;

        HandlerExecutor(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void execute(@NonNull final Runnable runnable) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    runnable.run();
                }
            });
        }
    }
}
