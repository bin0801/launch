package com.kino.lib.launch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.Map;
import java.util.Random;

/**
 * @author: kino
 * @CreateDate: 2021/3/31 15:21
 */
public class LaunchTools {

    public static void launchForResult(Context context, Intent intent, LaunchCallback callback) {
        if (context == null || intent == null || callback == null) {
            throw new IllegalArgumentException("context intent callback 不能为null");
        }
        if (context instanceof LaunchActivity) {
            ((LaunchActivity) context).launchForResult(intent, callback);
        } else {
            IntentChangeTools.intentChange(context, intent, callback);
        }
    }

    private final static Random mRandom = new Random();
    public final static int MAX_REQUEST_CODE = 65535;
    public final static int MIN_REQUEST_CODE = 30000;

    static int generateRandomNumber(Map<Integer, ? extends Object> map) {
        int number = (mRandom.nextInt(MAX_REQUEST_CODE - MIN_REQUEST_CODE)
                + MIN_REQUEST_CODE);
        while (map.containsKey(number)) {
            number = (mRandom.nextInt(MAX_REQUEST_CODE - MIN_REQUEST_CODE)
                    + MIN_REQUEST_CODE);
        }
        return number;
    }

    public static void launch(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
