package youga.app.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import youga.app.User;
import youga.app.R;
import youga.app.UserConnection;

public class AidlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
    }

    private Intent getExplicitIntent(Intent explicitIntent) {
        PackageManager manager = getPackageManager();
        List<ResolveInfo> resolveInfos = manager.queryIntentServices(explicitIntent, 0);
        if (resolveInfos == null || resolveInfos.size() == 0) {
            return null;
        }
        ResolveInfo info = resolveInfos.get(0);
        Log.i(getLocalClassName(), String.format("%s-%s", info.serviceInfo.packageName, info.serviceInfo.name));
        ComponentName componentName = new ComponentName(info.serviceInfo.packageName, info.serviceInfo.name);
        Intent intent = new Intent();
        intent.setComponent(componentName);
        return intent;
    }

    public void bindService(View view) {
        Intent intent = new Intent("interprocesscommuniction.AidlService");
        ComponentName componentName = new ComponentName("youga.interprocesscommuniction",
                "youga.interprocesscommuniction.service.AidlService");
        intent.setComponent(componentName);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private UserConnection mUserConnection;
    private User mUser;
    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(getLocalClassName(), "service connected");
            mUserConnection = UserConnection.Stub.asInterface(service);
            try {
                mUser = mUserConnection.connectUser();
                Log.e(getLocalClassName(), mUser.toString());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
