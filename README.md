# 简介
替换startActivity，可以在任意地方打开Activity和处理onActivityResult

```java
implementation 'io.github.bin0801:launch:1.0.0'
```
# 使用方式：
## Activity继承LaunchActivity，使用launchForResult打开Activity
```java
public class MainActivity extends LaunchActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_click1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DemoActivity.class);
                intent.putExtra("key", "launchForResult");
                launchForResult(intent, new LaunchCallback() {
                    @Override
                    public void onCallback(int resultCode, Intent intent) {
                        Toast.makeText(MainActivity.this, "resultCode : " + resultCode + " intent : " + intent, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
```

## 工具类打开Activity，Activity可继承可不继承LaunchActivity（强烈建议继承LaunchActivity，性能更优），使用LaunchTools.launchForResult打开Activity（只需要Context）
```java
public class MainActivity extends LaunchActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_click2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DemoActivity.class);
                intent.putExtra("key", "LaunchTools.launchForResult(context = Activity)");
                LaunchTools.launchForResult(MainActivity.this, intent, new LaunchCallback() {
                    @Override
                    public void onCallback(int resultCode, Intent intent) {
                        Toast.makeText(MainActivity.this, "resultCode : " + resultCode + " intent : " + intent, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        findViewById(R.id.tv_click3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = MainActivity.this.getApplicationContext();
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
```
