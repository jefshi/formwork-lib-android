package com.csp.sample.customview.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * utils about initialization
 * Created by Blankj on 2016/12/08
 * Modified by csp on 2018/03/02 just delete something
 * Modified by csp on 2018/03/10
 *
 * @author Blankj
 * @version 1.0.1
 */
public final class Utils {

    private static final ExecutorService THREAD_POOL;
    private final static Handler UI_HANDLER = new Handler(Looper.getMainLooper());

    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    static {
        THREAD_POOL = new ThreadPoolExecutor(3,
                3,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Init utils.
     * <p>Init it in the class of Application.</p>
     *
     * @param context context
     */
    public static void init(@NonNull final Context context) {
        init((Application) context.getApplicationContext());
    }

    /**
     * Init utils.
     * <p>Init it in the class of Application.</p>
     *
     * @param app application
     */
    public static void init(@NonNull final Application app) {
        if (sApplication == null) {
            sApplication = app;
        }
    }

    /**
     * Return the context of Application object.
     *
     * @return the context of Application object
     */
    public static Application getApp() {
        if (sApplication != null) {
            return sApplication;
        }

        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object at = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(at);
            if (app == null) {
                throw new NullPointerException("u should init first");
            }
            init((Application) app);
            return (Application) app;
        } catch (NoSuchMethodException e) {
            LogCat.printStackTrace(e);
        } catch (IllegalAccessException e) {
            LogCat.printStackTrace(e);
        } catch (InvocationTargetException e) {
            LogCat.printStackTrace(e);
        } catch (ClassNotFoundException e) {
            LogCat.printStackTrace(e);
        }
        throw new NullPointerException("u should init first");
    }

    /**
     * @return {@link #getApp()}, {@link Application#getApplicationContext()}
     */
    public static Context getAppContext() {
        Application app = getApp();
        return app == null ? null : app.getApplicationContext();
    }

    /**
     * 主线程执行任务
     */
    public static void runOnUiThread(final Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            Utils.UI_HANDLER.post(runnable);
        }
    }

    /**
     * 主线程执行任务
     */
    public static void runOnUiThreadDelayed(final Runnable runnable, long delayMillis) {
        Utils.UI_HANDLER.postDelayed(runnable, delayMillis);
    }

    /**
     * 异步线程执行任务
     */
    public static void doAsync(final Runnable runnable) {
        THREAD_POOL.execute(runnable);
    }
}
