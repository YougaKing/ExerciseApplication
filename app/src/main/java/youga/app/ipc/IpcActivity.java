package youga.app.ipc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import youga.app.R;

public class IpcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc);
    }

    public void aidlClick(View view) {
        Intent intent = new Intent(this, AidlActivity.class);
        startActivity(intent);
    }

    public void messengerClick(View view) {
//        Intent intent = new Intent(this, ProcessActivity.class);
//        startActivity(intent);
    }

    public void fileClick(View view) {
        Intent intent = new Intent(this, FileActivity.class);
        startActivity(intent);
    }
}
