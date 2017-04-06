package youga.interprocesscommuniction.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import youga.app.User;

public class MessengerService extends Service {

    private static final String TAG = "MessengerService";
    Messenger mMessenger = new Messenger(new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0xf0:
                    Bundle bundle = (Bundle) msg.obj;
                    User book = bundle.getParcelable("book");
                    Toast.makeText(getApplicationContext(), book.getName(), Toast.LENGTH_SHORT).show();
                    Message message = Message.obtain(msg);
                    message.what = msg.what;
                    try {
                        msg.replyTo.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    return true;
            }
            return false;
        }
    }));

    public MessengerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "onCreate()", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onCreate()");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "onBind()", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onBind()");
        return mMessenger.getBinder();
    }
}
