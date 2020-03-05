package com.example.mobilelocker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class LockerActivity extends AppCompatActivity implements View.OnClickListener{

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

    SharedPreferences save;

    private String inputPassword;
    String currentSymbols;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locker);

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
        inputPassword="";

        save  =getSharedPreferences(SAVE,MODE_PRIVATE);
        String s = save.getString(PASSWORD,"");
        Log.i("LOG","CURRENT PASSWORD: "+s);

        shuffle();
    }

    public void shuffle(){
        currentSymbols = save.getString(SYMBOLS,CurrentSymbols.greekSymbols);
        ArrayList s = new ArrayList();
        for (int i=0;i<currentSymbols.length();i++){
            s.add(currentSymbols.charAt(i)+"");
        }
        Log.i("LOG",s.toString());
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

    public void checkPassword(){
        if (inputPassword.compareTo(save.getString(PASSWORD,""))==0){
            Log.i("LOG", "Correct Password ");
           Intent intent = new Intent(this,MainActivity.class);
           startActivity(intent);
        }
        else{
            Log.i("LOG", "Incorrect Password ");
            clear();

        }
    }
    public void clear(){
        inputPassword="";
        tvInput.setText("");
    }

    @Override
    public void onClick(View v) {
        if (inputPassword.length()<Password.length){
            switch (v.getId()){
                case R.id.btn1:
                    inputPassword +=btn1.getText().toString();
                    break;
                case R.id.btn2:
                    inputPassword +=btn2.getText().toString();
                    break;
                case R.id.btn3:
                    inputPassword +=btn3.getText().toString();
                    break;
                case R.id.btn4:
                    inputPassword +=btn4.getText().toString();
                    break;
                case R.id.btn5:
                    inputPassword +=btn5.getText().toString();
                    break;
                case R.id.btn6:
                    inputPassword +=btn6.getText().toString();
                    break;
                case R.id.btn7:
                    inputPassword +=btn7.getText().toString();
                    break;
                case R.id.btn8:
                    inputPassword +=btn8.getText().toString();
                    break;
                case R.id.btn9:
                    inputPassword +=btn9.getText().toString();
                    break;
            }
        }
        if (inputPassword.length()==Password.length){
            checkPassword();
        }
        tvInput.setText(inputPassword);
    }
}
