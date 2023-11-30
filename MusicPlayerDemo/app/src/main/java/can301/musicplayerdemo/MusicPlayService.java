package can301.musicplayerdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class MusicPlayService extends Service {
    MediaPlayer player;
    class MusicPlayBinder extends Binder {
        public MediaPlayer getPlayer() {
            return MusicPlayService.this.player;
        }
    }
    private IBinder binder = new MusicPlayBinder();
    public MusicPlayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        String album = intent.getStringExtra("album_name");
        // Ideally, we should play that album.
        // This demo will always play the same song.
        player = MediaPlayer.create(this, R.raw.trololo);
        return binder;
    }
}