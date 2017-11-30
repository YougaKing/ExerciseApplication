package youga.keyboard;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Intent intent = new Intent(this, MoveLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent(this, LayoutActivity.class);
                startActivity(intent);
                break;
        }
    }
}
