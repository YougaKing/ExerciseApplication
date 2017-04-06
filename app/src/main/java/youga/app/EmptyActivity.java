package youga.app;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import youga.app.graph.GraphActivity;
import youga.app.ipc.IpcActivity;

public class EmptyActivity extends AppCompatActivity {

    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
    }

    public void ipcClick(View view) {

        Intent intent = new Intent(this, IpcActivity.class);
        startActivity(intent);
    }

    public void graphClick(View view) {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }
}
