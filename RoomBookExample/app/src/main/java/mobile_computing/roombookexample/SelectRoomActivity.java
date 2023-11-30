package mobile_computing.roombookexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class SelectRoomActivity extends AppCompatActivity {

    private int roomCost = 0;
    private int room = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room);
        Button confirmBookBtn = findViewById(R.id.confirmRoomBtn);
        confirmBookBtn.setOnClickListener(view -> {
            Intent toPayment = new Intent(this, PaymentActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("cost", roomCost);
            bundle.putInt("roomNumber", room);
            toPayment.putExtras(bundle);
            startActivity(toPayment);
        });
        Spinner roomSel = findViewById(R.id.roomselector);
        roomSel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        roomCost = 200;
                        room = 2012;
                        break;
                    case 1:
                        roomCost = 300;
                        room = 2008;
                        break;
                    case 2:
                        roomCost = 400;
                        room = 5003;
                        break;
                    case 3:
                        roomCost = 500;
                        room = 7010;
                        break;
                    default:
                        roomCost = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}