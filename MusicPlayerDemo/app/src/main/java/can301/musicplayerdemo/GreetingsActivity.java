package can301.musicplayerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GreetingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greetings);
        // "logging in" animation
        new Thread(()->{
            TextView loginTV = GreetingsActivity.this.findViewById(R.id.login_status);
            StringBuilder stb = new StringBuilder("Logging in");
            // in a real app, we would really check for account information on remote server.
            for (int i = 0; i < 5; i++) {
                runOnUiThread(()->loginTV.setText(stb.append('.')));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            // account logged in, redirecting the user to the album grid activity.
            runOnUiThread(()->{
                loginTV.setText(stb.append("success!"));
                GreetingsActivity.this.startService(new Intent(GreetingsActivity.this, InitService.class));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                startActivity(new Intent(GreetingsActivity.this, AlbumListActivity.class));
                finish();
            });
        }).start();
    }

}