package com.kino.lib.launch;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * @author: kino
 * @CreateDate: 2021/4/2 11:49
 */
public final class IntentChangeActivity extends AppCompatActivity {

    private IntentChangeBean mIntentChangeBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int requestCode = getIntent().getIntExtra("requestCode", 0);
        if (requestCode == 0) {
            finish();
            return;
        }
        mIntentChangeBean = IntentChangeTools.mIntentMap.get(requestCode);
        IntentChangeTools.mIntentMap.remove(requestCode);
        if (mIntentChangeBean == null) {
            finish();
            return;
        }
        startActivityForResult(mIntentChangeBean.intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mIntentChangeBean == null) {
            finish();
            return;
        }
        if (mIntentChangeBean.requestCode == requestCode) {
            if (mIntentChangeBean.launchCallback != null) {
                mIntentChangeBean.launchCallback.onCallback(resultCode, data);
            }
        }
        finish();
    }
}
