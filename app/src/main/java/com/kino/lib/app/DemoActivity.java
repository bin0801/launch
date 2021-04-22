package com.kino.lib.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        Toast.makeText(this, "value : " + getIntent().getStringExtra("key"), Toast.LENGTH_SHORT).show();
        findViewById(R.id.tv_click1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("key", "success");
                setResult(10001, data);
                finish();
            }
        });
    }
}