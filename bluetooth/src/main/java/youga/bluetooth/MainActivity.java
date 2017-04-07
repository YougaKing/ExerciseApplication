package youga.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_BT = 0XF0;
    private static final String TAG = "MainActivity";
    private BluetoothAdapter mBluetoothAdapter;
    private RecyclerView mRecyclerView;
    private InnerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new InnerAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter != null) {
            initBluetooth();
        }

    }

    private void initBluetooth() {
        if (!mBluetoothAdapter.isEnabled()) {
            Intent btIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(btIntent, REQ_BT);
        } else {
            resultAdapter();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_BT:
                if (resultCode != Activity.RESULT_OK) return;
                resultAdapter();
                break;
        }
    }

    private void resultAdapter() {
        Set<BluetoothDevice> deviceSet = mBluetoothAdapter.getBondedDevices();
        mAdapter.setBluetoothDevices(deviceSet);
        boolean dis = mBluetoothAdapter.startDiscovery();
        Log.i(TAG, "DIS:" + dis);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.i(TAG, "intent:" + intent.getAction());

            if (BluetoothDevice.ACTION_FOUND.equals(intent.getAction())) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mAdapter.addBluetoothDevices(device);
                Log.i(TAG, device.getName());
            }
        }
    };

    class InnerAdapter extends RecyclerView.Adapter<InnerAdapter.ViewHolder> {

        Set<BluetoothDevice> mBluetoothDevices = new HashSet<>();

        @Override
        public InnerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(parent.getContext(), R.layout.item_bluetooth, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bindPosition(position);
        }

        public void setBluetoothDevices(Set<BluetoothDevice> bluetoothDevices) {
            mBluetoothDevices.clear();
            mBluetoothDevices.addAll(bluetoothDevices);
            notifyDataSetChanged();
        }

        public void addBluetoothDevices(BluetoothDevice device) {
            mBluetoothDevices.add(device);
            notifyItemChanged(mBluetoothDevices.size());
        }

        @Override
        public int getItemCount() {
            return mBluetoothDevices.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView mTvName;

            public ViewHolder(View itemView) {
                super(itemView);
                mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            }

            public void bindPosition(int position) {
                BluetoothDevice device = mBluetoothDevices.iterator().next();
                mTvName.setText(String.format("%s\n%s", device.getName(), device.getAddress()));
            }
        }
    }
}
