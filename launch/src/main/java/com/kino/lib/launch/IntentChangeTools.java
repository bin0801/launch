package com.kino.lib.launch;

import android.content.Context;
import android.content.Intent;

import java.util.concurrent.ConcurrentHashMap;


/**
 * @author: kino
 * @CreateDate: 2021/4/2 11:47
 */
class IntentChangeTools {
    static final ConcurrentHashMap<Integer, IntentChangeBean> mIntentMap = new ConcurrentHashMap<>();

    static void intentChange(Context context, Intent intent, LaunchCallback launchCallback) {
        int requestCode = LaunchTools.randomRequestCode(mIntentMap);
        IntentChangeBean intentChangeBean = new IntentChangeBean(requestCode, intent, launchCallback);
        mIntentMap.put(requestCode, intentChangeBean);
        Intent changeIntent = new Intent(context, IntentChangeActivity.class);
        changeIntent.putExtra("requestCode", requestCode);
        LaunchTools.launch(context, changeIntent);
    }
}
