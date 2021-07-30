package com.csp.sample.customview.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.StringRes;

/**
 * Toast 相关
 * Created by csp on 2019/08/06
 * Modified by csp on 2021/04/07
 *
 * @author csp
 * @version 1.0.1
 */
public class ToastUtil {

    private static Toast mToast; // 目的是防止多个 Toast 对象连续 show 时，只显示最早的 Toast 内容

    @SuppressLint("ShowToast")
    private static void initToast(boolean isShort) {
        if (mToast == null) {
            Context context = Utils.getAppContext();
            mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        mToast.setDuration(isShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
    }

    /**
     * @see Toast#setGravity(int, int, int)
     */
    public static void setGravity(int gravity, int xOffset, int yOffset) {
        initToast(true);
        mToast.setGravity(gravity, xOffset, yOffset);
    }

    /**
     * Show the toast
     *
     * @param isShort true: use Toast.LENGTH_SHORT, else use Toast.LENGTH_LONG
     */
    private static void showToast(boolean isShort, final CharSequence text) {
        initToast(isShort);
        mToast.setText(text);
        mToast.show();
    }

    /**
     * Show the toast
     *
     * @param isShort true: use Toast.LENGTH_SHORT, else use Toast.LENGTH_LONG
     */
    private static void showToast(boolean isShort, @StringRes final int resId) {
        initToast(isShort);
        mToast.setText(resId);
        mToast.show();
    }

    /**
     * 使用 String#format 来拼接内容
     *
     * @see #showToast(boolean, CharSequence)
     * @see String#format(String, Object...)
     */
    public static void showToast(boolean isShort, @StringRes final int resId, Object... values) {
        Context context = Utils.getAppContext();
        if (context == null)
            return;

        String text = context.getString(resId);
        text = String.format(text, values);
        showToast(isShort, text);
    }

    /**
     * @see #showToast(boolean, int, Object...)
     */
    public static void showToast(@StringRes final int resId, Object... values) {
        showToast(true, resId, values);
    }

    /**
     * @see #showToast(boolean, CharSequence)
     */
    public static void showToast(final CharSequence text) {
        showToast(true, text);
    }

    /**
     * @see #showToast(boolean, int)
     */
    public static void showToast(@StringRes final int resId) {
        showToast(true, resId);
    }

    /**
     * @see #showToast(boolean, CharSequence)
     */
    public static void showShort(final CharSequence text) {
        showToast(true, text);
    }

    /**
     * @see #showToast(boolean, int)
     */
    public static void showShort(@StringRes final int resId) {
        showToast(true, resId);
    }

    /**
     * @see #showToast(boolean, CharSequence)
     */
    public static void showLong(final CharSequence text) {
        showToast(false, text);
    }

    /**
     * @see #showToast(boolean, int)
     */
    public static void showLong(@StringRes final int resId) {
        showToast(false, resId);
    }
}
