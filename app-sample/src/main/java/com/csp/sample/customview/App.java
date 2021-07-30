package com.csp.sample.customview;

import android.app.Application;

import com.csp.sample.customview.util.Utils;

/**
 * @author csp
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
