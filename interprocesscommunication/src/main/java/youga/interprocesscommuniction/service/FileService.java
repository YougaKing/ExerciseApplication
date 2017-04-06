package youga.interprocesscommuniction.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.google.gson.Gson;

import youga.app.User;

public class FileService extends Service {
    public FileService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(getClass().getSimpleName(), "onCreate(0");
    }

    //@IntDef(value = {Service.START_FLAG_REDELIVERY, Service.START_FLAG_RETRY}, flag = true)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(getClass().getSimpleName(), "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(getClass().getSimpleName(), "onBind()");
        return new FileBinder(this);
    }


    public static class FileBinder extends Binder {

        public FileBinder(Context context) {
//            SharedPreferences preferences = context.getSharedPreferences("FileBinder", Context.MODE_WORLD_READABLE);
//            User book = new User();
//            book.setName("哈哈哈");
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString(User.class.getSimpleName(), new Gson().toJson(book));
//            editor.apply();
        }
    }

}
