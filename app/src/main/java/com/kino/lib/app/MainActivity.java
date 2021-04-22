package com.kino.lib.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kino.lib.launch.LaunchActivity;
import com.kino.lib.launch.LaunchCallback;
import com.kino.lib.launch.LaunchTools;

public class MainActivity extends LaunchActivity {
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_click1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, DemoActivity.class);
                intent.putExtra("key", "launchForResult");
                launchForResult(intent, new LaunchCallback() {
                    @Override
                    public void onCallback(int resultCode, Intent intent) {
                        Toast.makeText(mActivity, "resultCode : " + resultCode + " intent : " + intent, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        findViewById(R.id.tv_click2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, DemoActivity.class);
                intent.putExtra("key", "LaunchTools.launchForResult(context = Activity)");
                LaunchTools.launchForResult(mActivity, intent, new LaunchCallback() {
                    @Override
                    public void onCallback(int resultCode, Intent intent) {
                        Toast.makeText(mActivity, "resultCode : " + resultCode + " intent : " + intent, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        findViewById(R.id.tv_click3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = mActivity.getApplicationContext();
                Intent intent = new Intent(context, DemoActivity.class);
                intent.putExtra("key", "LaunchTools.launchForResult(context = application)");
                LaunchTools.launchForResult(context, intent, new LaunchCallback() {
                    @Override
                    public void onCallback(int resultCode, Intent intent) {
                        Toast.makeText(context, "resultCode : " + resultCode + " intent : " + intent, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}