package mobile_computing.roombookexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class BookConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_confirm);
        Button bookYesBtn = findViewById(R.id.bookYesBtn);
        bookYesBtn.setOnClickListener((view -> {
            Intent returnContent = new Intent();
            returnContent.putExtra("room_booked", true);
            setResult(RESULT_OK, returnContent);
            finish();
        }));
        Button bookNoBtn = findViewById(R.id.bookNoBtn);
        bookNoBtn.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }

    // finish() will not be properly executed if user taps
    // The Back button. So we need to
    @Override
    public void onBackPressed() {
        //setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        setResult(RESULT_CANCELED);
        super.onDestroy();
    }
}