package com.example.can301groupproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
public class RegisterActivity extends AppCompatActivity {
    EditText et_username, et_password, et_email, et_confirmation_password;

    Button btn_register;

    TextView btn_goto_login;

    DatabaseHelper databaseHelper;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_username = findViewById(R.id.username);
        et_email = findViewById(R.id.email_address);
        et_password = findViewById(R.id.password);
        et_confirmation_password = findViewById(R.id.confirmation_password);
        btn_register = findViewById(R.id.register_button);
        btn_goto_login = findViewById(R.id.login_page_button);
        databaseHelper = new DatabaseHelper(this);

        //show the register activity when the user does not have an account
        btn_goto_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, email, password, conf_password;
                username = et_username.getText().toString();
                email = et_email.getText().toString();
                password = et_password.getText().toString();
                conf_password = et_confirmation_password.getText().toString();

                if (username.equals("") || email.equals("") || password.equals("") || conf_password.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please fill all details ", Toast.LENGTH_LONG).show();
                }else {
                    if (password.equals(conf_password)){
                        if (databaseHelper.checkUsername(username)) {
                            Toast.makeText(RegisterActivity.this, "User Already Exists", Toast.LENGTH_LONG).show();
                        }
                        // Proceed with registration
                        boolean registeredSuccess = databaseHelper.insertData(username, email, password);
                        if (registeredSuccess){
                            Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(RegisterActivity.this, "Password must contain at least 8 characters, having letter,digit and special symbol", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Password do not match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    public static boolean isValidPassword(String password){
        int hasLetter=0, hasDigit=0, hasSpecialChar=3;

        // Check length of the password
        if(password.length() < 8 || password.length() > 20){
            return false; // Password length should be between 8 and 20 characters
        } else{
            // Check for letters
            for(int i = 0; i < password.length(); i++){
                if(Character.isLetter(password.charAt(i))){
                    hasLetter=1;
                }
            }
            // Check for digits
            for(int i = 0; i < password.length(); i++){
                if(Character.isDigit(password.charAt(i))){
                    hasDigit=1;
                }
            }
            // Check for Special Characters
            for(int i = 0; i < password.length(); i++){
                char c = password.charAt(i);
                if(c>=33 && c<=46 || c==64){
                    hasSpecialChar=1;
                }
            }
        }

        // Check if all required types of characters are present
        if(hasLetter==1 && hasDigit==1 && hasSpecialChar==1){
            return true;
        }
        return false; // Password is not valid
    }
}
