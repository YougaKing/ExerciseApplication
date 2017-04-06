package youga.app.ipc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import youga.app.User;
import youga.app.R;

public class FileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
    }


    public void bindService(View view) {
        Intent intent = new Intent("interprocesscommuniction.FileService");
        ComponentName componentName = new ComponentName("youga.interprocesscommuniction",
                "youga.interprocesscommuniction.service.FileService");
        intent.setComponent(componentName);
        bindService(intent, mConnection, Service.BIND_AUTO_CREATE);

    }


    public void getFile(View view) {
        SharedPreferences preferences = getSharedPreferences("FileBinder", Context.MODE_WORLD_READABLE);
        String json = preferences.getString(User.class.getSimpleName(), "");
        Log.i("FileActivity", String.format("json:%s", json));


    }

    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("FileActivity", "onServiceConnected()");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
