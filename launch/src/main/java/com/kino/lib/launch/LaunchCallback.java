package com.kino.lib.launch;

import android.content.Intent;

/**
 * @author: yb
 * @CreateDate: 2021/3/31 15:20
 */
public interface LaunchCallback {
    void onCallback(int resultCode, Intent intent);
}
