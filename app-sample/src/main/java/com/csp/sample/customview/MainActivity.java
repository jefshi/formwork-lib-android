package com.csp.sample.customview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author csp
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String versionInfo = String.format(getString(R.string.version_info),
                BuildConfig.ASSEMBLE_TIME,
                BuildConfig.VERSION_CODE + "",
                BuildConfig.VERSION_NAME);

        ((TextView) findViewById(R.id.tv_version)).setText(versionInfo);
    }
}
