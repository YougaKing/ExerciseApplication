//package youga.app.ipc;
//
//import android.content.ComponentName;
//import android.content.Context;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.content.pm.PackageManager;
//import android.content.pm.ResolveInfo;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.Message;
//import android.os.Messenger;
//import android.os.RemoteException;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import java.util.List;
//
//import youga.app.User;
//import youga.app.R;
//
//public class ProcessActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_process);
//    }
//
//    private Messenger mMessenger;
//
//    Messenger mLocalMessenger = new Messenger(new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0xf0:
//                    Toast.makeText(getApplicationContext(), "服务端收到", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//            return false;
//        }
//    }));
//
//    ServiceConnection mConnection = new ServiceConnection() {
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            mMessenger = new Messenger(service);
//            Toast.makeText(getApplicationContext(), "onServiceConnected()", Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    };
//
//    public void hello(View view) {
//        Message message = Message.obtain(null, 0xf0);
//        try {
//            User book = new User();
//            book.setName("好书");
//            Bundle bundle = new Bundle();
//            bundle.putParcelable("book", book);
//            message.obj = bundle;
//            message.replyTo = mLocalMessenger;
//            mMessenger.send(message);
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void bindService(View view) {
//        Intent intent = new Intent();
//        intent.setAction("interprocesscommuniction.MessengerService");
//        bindService(getExplicitIntent(this, intent), mConnection, Context.BIND_AUTO_CREATE);
//    }
//
//
//    public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
//        // Retrieve all services that can match the given intent
//        PackageManager pm = context.getPackageManager();
//        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
//        // Make sure only one match was found
//        if (resolveInfo == null || resolveInfo.size() != 1) {
//            return null;
//        }
//        // Get component info and create ComponentName
//        ResolveInfo serviceInfo = resolveInfo.get(0);
//        String packageName = serviceInfo.serviceInfo.packageName;
//        String className = serviceInfo.serviceInfo.name;
//        ComponentName component = new ComponentName(packageName, className);
//        // Create a new intent. Use the old one for extras and such reuse
//        Intent explicitIntent = new Intent(implicitIntent);
//        // Set the component to be explicit
//        explicitIntent.setComponent(component);
//        return explicitIntent;
//    }
//}
