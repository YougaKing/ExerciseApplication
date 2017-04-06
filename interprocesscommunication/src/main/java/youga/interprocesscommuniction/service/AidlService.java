package youga.interprocesscommuniction.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import youga.app.User;
import youga.app.UserConnection;

public class AidlService extends Service {

    private static final String TAG = "AidlService";
    private User mUser;

    UserConnection.Stub mBinder = new UserConnection.Stub() {
        @Override
        public User connectUser() throws RemoteException {
            synchronized (this) {
                Log.e(TAG, "invoking getBooks() method , now the list is : " + mUser.toString());
                if (mUser == null) return mUser = new User("张三", "18989076789", "陕西省西安市");
            }
            return mUser;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mUser = new User("张三", "18989076789", "陕西省西安市");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(getClass().getSimpleName(), String.format("on bind,intent = %s", intent.toString()));
        return mBinder;
    }
}
