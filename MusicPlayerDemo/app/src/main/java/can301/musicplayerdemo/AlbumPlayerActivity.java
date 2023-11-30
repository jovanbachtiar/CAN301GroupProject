package can301.musicplayerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class AlbumPlayerActivity extends AppCompatActivity {
    class SeekCheckerThread extends Thread {
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (seekBar == null || player == null || !player.isPlaying()) {
                    continue;
                }
                runOnUiThread(()->seekBar.setProgress(player.getCurrentPosition()));
            }
        }
    }
    SeekCheckerThread seekCheckerThread;
    SeekBar seekBar;
    ImageButton playPauseBtn;
    ImageButton stopBtn;
    private MediaPlayer player;
    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            player = ((MusicPlayService.MusicPlayBinder) service).getPlayer();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            player = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_player);
        Intent albumPlayIntent = getIntent();
        TextView albumNameLabel = findViewById(R.id.album_name_label);
        albumNameLabel.setText(albumPlayIntent.getStringExtra("album_name"));
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // DO NOT USE THIS METHOD
                // Music play will not be smooth if you use it to handle finger-based seeking
                // Everytime the SeekCheckerThread updates the seekbar,
                // this function will be triggered.
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.seekTo(seekBar.getProgress());
            }
        });
        playPauseBtn = findViewById(R.id.play_pause_btn);
        playPauseBtn.setOnClickListener((btn)->{
            if (player == null)
                return;

            // makes sure the seekbar matches current song.
            seekBar.setMax(player.getDuration());

            if (player.isPlaying()) {
                player.pause();
                playPauseBtn.setImageResource(R.drawable.play);
            } else {
                player.start();
                playPauseBtn.setImageResource(R.drawable.pause);
            }
            playPauseBtn.invalidate(); // force redraw the button
        });
        stopBtn = findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener((btn)->{
            if (player == null)
                return;
            playPauseBtn.setImageResource(R.drawable.play);
            playPauseBtn.invalidate();
            player.stop();
            try {
                player.prepare(); // need to prepare before playing again.
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        seekCheckerThread = new SeekCheckerThread();
        seekCheckerThread.start();
        albumPlayIntent.setClass(this, MusicPlayService.class);
        bindService(albumPlayIntent, mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        //seekCheckerThread.interrupt();
        //seekCheckerThread = null;
        player.stop();
        player = null;
        unbindService(mConnection);
        super.onDestroy();
    }
}