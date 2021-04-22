package com.kino.lib.launch;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: kino
 * @CreateDate: 2021/3/31 15:17
 */
public class LaunchFragment extends Fragment {
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

    @Deprecated
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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

    private final boolean isInitLaunch() {
        return mMap != null;
    }
}
