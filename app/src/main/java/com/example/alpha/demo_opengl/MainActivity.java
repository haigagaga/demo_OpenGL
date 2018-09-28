package com.example.alpha.demo_opengl;

import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private boolean supportsEs2;
    private GLSurfaceView mGlSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkSupported();


        if (supportsEs2) {
            mGlSurfaceView = new GLSurfaceView(this);
            mGlSurfaceView.setRenderer(new GLRenderer());
            setContentView(mGlSurfaceView);
        } else {
            setContentView(R.layout.activity_main);
            Toast.makeText(this, "当前设备不支持OpenGL ES 2.0!", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkSupported() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        supportsEs2 = configurationInfo.reqGlEsVersion >= 0x2000;

        boolean isEmulator = Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                && (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86"));

        supportsEs2 = supportsEs2 || isEmulator;
    }


    /**
     * 防止我们在切换程序时，OpenGL还在绘制图形导致程序崩溃，因此我们还需要根据Activity的生命周期针对GLSurfaceView做一些处理：
     */

    @Override
    protected void onPause() {
        super.onPause();
        if (mGlSurfaceView != null) {
            mGlSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mGlSurfaceView != null) {
            mGlSurfaceView.onResume();
        }
    }
}
