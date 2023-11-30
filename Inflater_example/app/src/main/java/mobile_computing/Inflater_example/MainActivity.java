package mobile_computing.Inflater_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button coffeePlsBtn;
    Button teaPlsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coffeePlsBtn = findViewById(R.id.coffee_pls_btn);
        teaPlsBtn = findViewById(R.id.tea_pls_btn);
//        coffeePlsBtn.setOnClickListener((View view) -> {
//            ConstraintLayout mainLayout = findViewById(R.id.main_layout);
//            LinearLayout coffee_list_layout = findViewById(R.id.coffee_list_layout);
//            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            lp.leftToLeft = R.id.main_layout;
//            lp.rightToRight = R.id.main_layout;
//            lp.topToBottom = R.id.divider;
//            lp.bottomToBottom = R.id.main_layout;
//            mainLayout.addView(coffee_list_layout,lp);
//        });
        coffeePlsBtn.setOnClickListener((View view) -> {
            teaPlsBtn.setClickable(false);
            teaPlsBtn.setBackgroundColor(Color.GRAY);
            coffeePlsBtn.setClickable(false);
            coffeePlsBtn.setBackgroundColor(Color.RED);
            LayoutInflater inflater = LayoutInflater.from(this);
            ConstraintLayout mainLayout = findViewById(R.id.main_layout);
            LinearLayout coffee_list_layout = inflater.inflate(
                    R.layout.coffee_options, null, false).findViewById(R.id.coffee_list_layout);
            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.leftToLeft = R.id.main_layout;
            lp.rightToRight = R.id.main_layout;
            lp.topToBottom = R.id.divider;
            lp.bottomToBottom = R.id.main_layout;
            mainLayout.addView(coffee_list_layout,lp);
        });
        teaPlsBtn.setOnClickListener((View view) -> {
            teaPlsBtn.setClickable(false);
            teaPlsBtn.setBackgroundColor(Color.RED);
            coffeePlsBtn.setClickable(false);
            coffeePlsBtn.setBackgroundColor(Color.GRAY);
            LayoutInflater inflater = LayoutInflater.from(this);
            ConstraintLayout mainLayout = findViewById(R.id.main_layout);
            LinearLayout tea_list_layout = inflater.inflate(
                    R.layout.tea_options, null, false).findViewById(R.id.tea_list_layout);
            ConstraintLayout.LayoutParams lp = new ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.leftToLeft = R.id.main_layout;
            lp.rightToRight = R.id.main_layout;
            lp.topToBottom = R.id.divider;
            lp.bottomToBottom = R.id.main_layout;
            mainLayout.addView(tea_list_layout,lp);
        });
    }
}