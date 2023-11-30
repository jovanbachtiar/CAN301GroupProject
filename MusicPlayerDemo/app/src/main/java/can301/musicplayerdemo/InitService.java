package can301.musicplayerdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class InitService extends Service {
    public InitService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast t = Toast.makeText(this, "Downloading album lists...", Toast.LENGTH_LONG);
        t.show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Get the user and album information from server (fake implementation)
        new Thread(()->{ // use a separate thread, avoid blocking the UI thread.
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            InfoManager.info = "age=23;last_login=2023-09-01";
            InfoManager.albums.add("Album1");
            InfoManager.albums.add("Album2");
            InfoManager.albums.add("Album3");
            InfoManager.albums.add("Album4");
            InfoManager.albums.add("Album5");
            InfoManager.loggedIn = true;
            stopSelf();
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}