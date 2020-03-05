package com.example.mobilelocker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.locks.Lock;

public class MainActivity extends AppCompatActivity {


    //https://coolsymbol.com/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Save Current Symbols sda
        //asdasdasdasdTEst
    }


    public void btnChooseSymbolsClick(View view){
        if (Password.hasPassword==false) {
            Intent intent = new Intent(this, ChooseSymbols.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent();
            startActivity(intent);
        }
    }
    public void lockerClick(View view){
        Intent intent = new Intent(this, LockerActivity.class);
        startActivity(intent);
    }
}
