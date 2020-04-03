package com.example.mobilelocker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.login.LoginException;


public class LockerActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SAVE = "SAVE";
    public static final String PASSWORD = "PASSWORD";
    public static final String SYMBOLS = "SYMBOLS";

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private TextView tvInput;

    private TextView hoursAndMins;
    private TextView monthAndDay;

    SharedPreferences save;

    private String inputPassword;
    String currentSymbols;

    boolean changePassword = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locker);

        //set time
        hoursAndMins = findViewById(R.id.hoursAndMinsTextView);
        monthAndDay = findViewById(R.id.monthAndDayTextView);
        setTime();
        //set time end
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        tvInput = findViewById(R.id.lockerActivityInputTextView);
        inputPassword = "";

        save = getSharedPreferences(SAVE, MODE_PRIVATE);
        String s = save.getString(PASSWORD, "");
        Log.i("LOG", "CURRENT PASSWORD: " + s);


        clear();
        shuffle();

    }

    public void setTime(){
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        getHoursAndMins();
                        getMonthAndDay();
                    }
                }, 1000);
    }
    public void getHoursAndMins(){
        DateFormat df = new SimpleDateFormat("h:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        hoursAndMins.setText(date);

    }
    public void getMonthAndDay(){
        DateFormat df = new SimpleDateFormat("EEE, d MMM");
        String date = df.format(Calendar.getInstance().getTime());
        monthAndDay.setText(date);
    }



    @Override
    protected void onResume() {

        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);
        super.onUserInteraction();

        clear();
        shuffle();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_IMMERSIVE;
        decorView.setSystemUiVisibility(uiOptions);

    }


    public void shuffle() {
        currentSymbols = save.getString(SYMBOLS, CurrentSymbols.greekSymbols);
        ArrayList s = new ArrayList();
        for (int i = 0; i < currentSymbols.length(); i++) {
            s.add(currentSymbols.charAt(i) + "");
        }
        Log.i("LOG", s.toString());
        Collections.shuffle(s);
        Log.i("LOG", s.toString());

        btn1.setText(s.get(0).toString());
        btn2.setText(s.get(1).toString());
        btn3.setText(s.get(2).toString());
        btn4.setText(s.get(3).toString());
        btn5.setText(s.get(4).toString());
        btn6.setText(s.get(5).toString());
        btn7.setText(s.get(6).toString());
        btn8.setText(s.get(7).toString());
        btn9.setText(s.get(8).toString());
    }

    public void checkPassword() {
        if (inputPassword.compareTo(save.getString(PASSWORD, "")) == 0) {
            Log.i("LOG", "Correct Password ");
            if (changePassword) {
                Intent intent = new Intent(this,ChooseSymbols.class);
                startActivity(intent);
            } else {

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        } else {
            Log.i("LOG", "Incorrect Password ");
            clear();

        }
    }

    public void clear() {
        inputPassword = "";
        tvInput.setText("");
    }

    @Override
    public void onClick(View v) {
        if (inputPassword.length() < Password.length) {
            switch (v.getId()) {
                case R.id.btn1:
                    inputPassword += btn1.getText().toString();
                    break;
                case R.id.btn2:
                    inputPassword += btn2.getText().toString();
                    break;
                case R.id.btn3:
                    inputPassword += btn3.getText().toString();
                    break;
                case R.id.btn4:
                    inputPassword += btn4.getText().toString();
                    break;
                case R.id.btn5:
                    inputPassword += btn5.getText().toString();
                    break;
                case R.id.btn6:
                    inputPassword += btn6.getText().toString();
                    break;
                case R.id.btn7:
                    inputPassword += btn7.getText().toString();
                    break;
                case R.id.btn8:
                    inputPassword += btn8.getText().toString();
                    break;
                case R.id.btn9:
                    inputPassword += btn9.getText().toString();
                    break;
            }
        }
        tvInput.append("●");
        if (inputPassword.length() == Password.length) {
            checkPassword();
        }


    }
}

