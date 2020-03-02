package com.example.mobilelocker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseSymbols extends AppCompatActivity implements View.OnClickListener {


    public static final String SAVE = "SAVE";
    private Button btnChooseGreek;
    private Button btnChooseChessandcard;

    private TextView tv;

    private int id=0;

    SharedPreferences save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_symbols);

        tv = findViewById(R.id.chooseSymbols_preview);

        save = getSharedPreferences(SAVE,MODE_PRIVATE);

        btnChooseGreek = findViewById(R.id.btnSetGreek);
        btnChooseGreek.setOnClickListener(this);
        btnChooseChessandcard = findViewById(R.id.btnSetChessandcard);
        btnChooseChessandcard.setOnClickListener(this);

    }

    public void btnChooseClick(View view){
        switch (id){
            case 0:
                CurrentSymbols.setCurrentGreek();
                break;
            case 1:
                CurrentSymbols.setCurrentChessandcard();
                break;
        }
        Log.i("LOG","Btn Choose clicked. Current symbold id: " + id);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSetGreek:
                tv.setText(CurrentSymbols.greekSymbols);
                id = 0;
                break;
            case R.id.btnSetChessandcard:
                id=1;
                tv.setText(CurrentSymbols.chessandcardSymbols);
                break;
        }
    }
}
