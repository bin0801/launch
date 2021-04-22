package com.kino.lib.launch;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ConcurrentHashMap;


/**
 * @author: yb
 * @CreateDate: 2021/3/31 14:54
 */
public class LaunchActivity extends AppCompatActivity {
    private ConcurrentHashMap<Integer, LaunchCallback> mMap;

    protected final void launchForResult(Intent intent, LaunchCallback callback) {
        if (intent == null || callback == null) {
            throw new IllegalArgumentException("intent 或 callback 不能为null");
        }
        if (mMap == null) {
            mMap = new ConcurrentHashMap<>();
        }
        int requestCode = LaunchTools.generateRandomNumber(mMap);
        mMap.put(requestCode, callback);
        super.startActivityForResult(intent, requestCode, null);
    }

    @Override
    public void finish() {
        super.finish();
        if (isInitLaunch()) {
            mMap.clear();
        }
    }

    @Deprecated
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (isInitLaunch()) {
            LaunchCallback callback = mMap.get(requestCode);
            if (callback != null) {
                callback.onCallback(resultCode, data);
            }
            mMap.remove(requestCode);
        }
    }


    /**
     * 请使用launchForResult
     *
     * @param intent
     * @param requestCode
     */
    @Deprecated
    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (isInitLaunch()) {
            if (requestCode >= LaunchTools.MIN_REQUEST_CODE) {
                throw new IllegalArgumentException("requestCode必须小于" + LaunchTools.MIN_REQUEST_CODE);
            }
        }
        super.startActivityForResult(intent, requestCode);
    }

    /**
     * 请使用launchForResult
     *
     * @param intent
     * @param requestCode
     */
    @Deprecated
    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        if (isInitLaunch()) {
            if (requestCode >= LaunchTools.MIN_REQUEST_CODE) {
                throw new IllegalArgumentException("requestCode必须小于" + LaunchTools.MIN_REQUEST_CODE);
            }
        }
        super.startActivityForResult(intent, requestCode, options);
    }

    private boolean isInitLaunch() {
        return mMap != null;
    }
}
