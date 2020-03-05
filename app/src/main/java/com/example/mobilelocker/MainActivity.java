package com.example.mobilelocker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.locks.Lock;

public class MainActivity extends AppCompatActivity {


    Button btnSetPassword;

    //https://coolsymbol.com/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSetPassword = findViewById(R.id.setPasswordBtn);
        //Save Current Symbols sda
        //asdasdasdasdTEst
    }

    public void btnSetPasswordClick(View view){
        Intent intent = new Intent(this, SetPasswordActivity.class);
        startActivity(intent);

    }
    public void btnChooseSymbolsClick(View view){
        Intent intent = new Intent(this,ChooseSymbols.class);
        startActivity(intent);
    }
    public void lockerClick(View view){
        Intent intent = new Intent(this, LockerActivity.class);
        startActivity(intent);
    }
}
