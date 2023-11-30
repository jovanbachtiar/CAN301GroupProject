package can301.locationservicedemo;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LocationListener listener = new LocationListener() {
        public void onLocationChanged(Location location) {
            TextView tv = (TextView) findViewById(R.id.locationLabel);
            tv.setText("Latitude = " + location.getLatitude() + "\n" +
                    "Longitude = " + location.getLongitude() + "\n" +
                    "Altitude = " + location.getAltitude() + "\n" +
                    "time=" + location.getTime() + "\n" +
                    "Accuracy=" + location.getAccuracy());
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i("LocationTester", "provider status is changed");
        }
        public void onProviderEnabled(String provider) {
            Log.i("LocationTester", "provider is enabled " + provider);
        }
        public void onProviderDisabled(String provider) {
            Log.i("LocationTester", "provider is closed " + provider);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        RadioGroup methodChoice = findViewById(R.id.locationMethod);
        final RadioButton radio1 = findViewById(R.id.nwBasedBtn);
        final RadioButton radio2 = findViewById(R.id.gpsBasedBtn);
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        methodChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == radio1.getId()) {
                    //locationManager.removeUpdates(listener);
                    try {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
                    } catch (SecurityException e) {
                        Toast.makeText(MainActivity.this, "Permission not granted", Toast.LENGTH_SHORT).show();
                    }
                } else if (i == radio2.getId()) {
                    try {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
                    } catch (SecurityException e) {
                        Toast.makeText(MainActivity.this, "Permission not granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    locationManager.removeUpdates(listener);
                    TextView tv = (TextView) findViewById(R.id.locationLabel);
                    tv.setText("Location Service OFF");
                }
            }
        });
    }
}