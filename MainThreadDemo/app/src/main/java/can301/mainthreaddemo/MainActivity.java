package can301.mainthreaddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView message = findViewById(R.id.msg_tv);

        Button routBtn = findViewById(R.id.rout_start);
        routBtn.setOnClickListener(view -> {
            new Thread(() -> {
                for (int i = 1; i <= 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    final String label = "loop " + i;
                    runOnUiThread(() -> message.setText(label));
                }
            }).start();
        });

        Button viewPostBtn = findViewById(R.id.viewpost_start);
        viewPostBtn.setOnClickListener(view -> {
            new Thread(() -> {
                for (int i = 1; i <= 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    final String label = "loop " + i;
                    message.post(() -> message.setText(label));
                }
            }).start();
        });

        handler = new Handler();
        Button handlerBtn = findViewById(R.id.handler_start);
        handlerBtn.setOnClickListener(view -> {
            new Thread(() -> {
                for (int i = 1; i <= 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                    final String label = "loop " + i;
                    handler.post(() -> message.setText(label));
                }
            }).start();
        });
    }
}