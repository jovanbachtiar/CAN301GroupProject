package can301.musicplayerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;

public class AlbumListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        new Thread(()->{
            while(!InfoManager.loggedIn) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            runOnUiThread(()->{
                TextView statusLabel = findViewById(R.id.status_label);
                statusLabel.setText(String.format("%s's Albums", InfoManager.username));
                LinearLayout album_list_layout = findViewById(R.id.album_list_layout);
                for (int i = 0; i < InfoManager.albums.size(); i++) {
                    // Ideally, we should place the album cover into this layout.
                    TextView albumItem = new TextView(AlbumListActivity.this);
                    albumItem.setPadding(20,20,20,20);
                    albumItem.setBackgroundColor(Color.argb(255,218,98,125));
                    albumItem.setText(InfoManager.albums.get(i));
                    albumItem.setTextColor(Color.WHITE);
                    albumItem.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.rightMargin = lp.leftMargin = lp.topMargin = lp.bottomMargin = 10;
                    album_list_layout.addView(albumItem, lp);
                    albumItem.setOnClickListener((v)->{
                        Intent albumPlayIntent = new Intent(AlbumListActivity.this, AlbumPlayerActivity.class);
                        albumPlayIntent.putExtra("album_name", ((TextView) v).getText());
                        startActivity(albumPlayIntent);
                    });
                }
            });
        }).start();
    }
}