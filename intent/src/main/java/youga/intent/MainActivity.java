package youga.intent;

import android.content.ClipData;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import youga.intent.launchMode.SingleInstanceActivity;
import youga.intent.launchMode.SingleTaskActivity;
import youga.intent.launchMode.StandardActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Log.i(TAG, "getAction:" + getIntent().getAction());
        switch (getIntent().getAction()) {
            case Intent.ACTION_SEND:
                Intent intent = getIntent();
                ClipData clipData = intent.getClipData();
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    imageView.setImageURI(item.getUri());
                    Log.i(TAG, "item:" + item.getUri());
                }
                Log.i(TAG, "clipData:" + clipData.getItemCount());
                break;
        }
    }

    public void launchMode(View view) {
        Intent intent = new Intent(this, SingleInstanceActivity.class);
        startActivity(intent);
    }
}
