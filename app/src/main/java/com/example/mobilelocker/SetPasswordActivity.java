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

public class SetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SAVE = "SAVE";
    public static final String TEMPORARY_SYMBOLS = "TEMPORARY_SYMBOLS";
    public static final String SYMBOLS = "SYMBOLS";
    public static final String CONFIRM_PASSWORD = "CONFIRM_PASSWORD";
    public static final String PASSWORD_SIZE ="PASSWORD_SIZE";
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;

    TextView passwordView;
    String inputPassword;

    Button btnConfirm;
    Button btnClear;

    SharedPreferences save;

    String currentSymbols;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        inputPassword ="";
        passwordView = findViewById(R.id.showPasswordView);

        btnClear = findViewById(R.id.btnClear);
        btnConfirm = findViewById(R.id.btnConfirmPassword);

        save =getSharedPreferences(SAVE,MODE_PRIVATE);

        Log.i("LOG","CURRENT PASSWORD: "+save.getString("PASSWORD",""));

        setBtns();
    }
    public void btnConfirmClick(View view){
        if (inputPassword.length()>=2 && inputPassword.length()<=Password.maxLength){
            Log.i("LOG","Btn Confirm clicked. CurrentPassword: "+inputPassword);
            SharedPreferences.Editor editor = save.edit();
            editor.putInt(PASSWORD_SIZE,inputPassword.length());
            editor.commit();
            Intent intent = new Intent(this,ConfirmPasswordCreation.class);
            intent.putExtra(CONFIRM_PASSWORD,inputPassword);
            startActivity(intent);
        }
        else{
            Log.i("LOG","Btn Confirm clicked. Password not saved. CurrentPassword: " + inputPassword);
            Toast.makeText(this, "Password length is invalid", Toast.LENGTH_SHORT).show();
        }

    }

    public void btnClearClick(View view){

        inputPassword ="";
        passwordView.setText("");
        Log.i("LOG","Btn Clear clicked. CurrentPassword: "+inputPassword);
    }
    public void setBtns(){
        currentSymbols = save.getString(TEMPORARY_SYMBOLS, Symbols.greekSymbols);
        ArrayList s = new ArrayList();
        for (int i=0;i<currentSymbols.length();i++){
            s.add(currentSymbols.charAt(i)+"");
        }
        Log.i("LOG",s.toString());

        btn1.setText(s.get(0).toString());
        btn2.setText(s.get(1).toString());
        btn3.setText(s.get(2).toString());
        btn4.setText(s.get(3).toString());
        btn5.setText(s.get(4).toString());
        btn6.setText(s.get(5).toString());
        btn7.setText(s.get(6).toString());
        btn8.setText(s.get(7).toString());
        btn9.setText(s.get(8).toString());
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (inputPassword.length()<Password.maxLength){
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
        passwordView.setText(inputPassword);
    }
}
