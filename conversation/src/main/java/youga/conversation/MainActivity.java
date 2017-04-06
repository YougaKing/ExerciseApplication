package youga.conversation;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0xf00:
                    Log.i(TAG, Thread.currentThread().getName());
                    mThreadHandler.sendEmptyMessage(0xf00);
                    break;
            }
            return false;
        }
    });

    Handler mThreadHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler.sendEmptyMessageDelayed(0xf00, 2000);


        new Thread("Thread") {
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                mThreadHandler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {
                        Log.i(TAG, Thread.currentThread().getName());
                        return false;
                    }
                });
                Looper.loop();
            }
        }.start();


    }

}
