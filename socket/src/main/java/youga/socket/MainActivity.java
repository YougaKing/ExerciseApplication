package youga.socket;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText mEditText;
    Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.editText);
    }

    public void onClick(View view) {
        if (mSocket != null) return;
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    mSocket = new Socket("192.168.199.194", 40004);
                    while (true) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(mSocket.getInputStream(), "UTF-8"));
                        Log.i("MainActivity", "服务器数据：" + br.readLine());
                        br.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }.start();


    }

    public void sendClick(View view) {
        String message = mEditText.getText().toString();
        try {
            OutputStream out = mSocket.getOutputStream();
            out.write(message.getBytes("UTF-8"));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
