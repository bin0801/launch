package com.kino.lib.launch;

import android.content.Intent;

/**
 * @author: kino
 * @CreateDate: 2021/4/2 11:48
 */
class IntentChangeBean {
    int requestCode;
    Intent intent;
    LaunchCallback launchCallback;

    public IntentChangeBean(int requestCode, Intent intent, LaunchCallback launchCallback) {
        this.requestCode = requestCode;
        this.intent = intent;
        this.launchCallback = launchCallback;
    }
}
