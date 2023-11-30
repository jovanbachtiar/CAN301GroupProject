package com.example.datastoragedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Internal
        writeInternalStorage();
        Toast t = Toast.makeText(this, readInternalStorage(), Toast.LENGTH_LONG);
        t.show();

        // external
        writeExternalStorage();
        t = Toast.makeText(this, readExternalStorage(), Toast.LENGTH_LONG);
        t.show();

        // shared preferences
        SharedPreferences usp = getSharedPreferences("User info", Context.MODE_PRIVATE);
        int userID = usp.getInt("user-id", 0);
        String username = usp.getString("username", "empty");
        SharedPreferences.Editor uspEditor = usp.edit();
        uspEditor.putInt("user-id", 654321);
        uspEditor.commit();

        SharedPreferences nsp = getSharedPreferences("Network info", Context.MODE_PRIVATE);
        String ipAddr = nsp.getString("ip-addr", "127.0.0.1");
    }

    private void writeInternalStorage() {
        try (FileOutputStream fos = openFileOutput("myfile", Context.MODE_PRIVATE)) {
            fos.write("Hello internal world!".getBytes());
        } catch (Exception e) { e.printStackTrace(); }
    }

    private String readInternalStorage() {
        try (FileInputStream fis = openFileInput("myfile")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            return br.readLine();
        } catch (Exception e) { e.printStackTrace(); }
        return "Failed to read internal file";
    }

    private void writeExternalStorage() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File f = new File(getExternalFilesDir(null), "myExtFile");
            try (FileOutputStream fos = new FileOutputStream(f, false)) {
                fos.write("Hello External World".getBytes());
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    private String readExternalStorage() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File f = new File(getExternalFilesDir(null), "myExtFile");
            try (FileInputStream fos = new FileInputStream(f)) {
                BufferedReader br = new BufferedReader(new InputStreamReader(fos));
                return br.readLine();
            } catch (Exception e) { e.printStackTrace(); }
        }
        return "Failed to read external file";
    }
}