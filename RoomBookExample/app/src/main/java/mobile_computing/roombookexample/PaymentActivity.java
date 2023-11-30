package mobile_computing.roombookexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
    final int CONFIRM_BOOK_REQUEST = 10086;
    int payAmount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_page);
        final Intent payRequest = getIntent();
        payAmount = payRequest.getIntExtra("cost", 0);
        int roomNum = payRequest.getIntExtra("roomNumber", 0);
        Button bookNowBtn = findViewById(R.id.bookNowBtn);
        bookNowBtn.setOnClickListener(view -> {
            payRequest.setClass(this, BookConfirmActivity.class);
            startActivityForResult(payRequest, CONFIRM_BOOK_REQUEST);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (payAmount > 0) {
            TextView payAmountEnter = findViewById(R.id.pay_amount_enter);
            payAmountEnter.setText(String.valueOf(payAmount));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != CONFIRM_BOOK_REQUEST) {
            return;
        }
        switch (resultCode) {
            case RESULT_OK:
                Toast.makeText(this, "Book Confirmed", Toast.LENGTH_LONG).show();
                finish();
                break;
            case RESULT_CANCELED:
                Toast.makeText(this, "Book Canceled", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }

    }
}